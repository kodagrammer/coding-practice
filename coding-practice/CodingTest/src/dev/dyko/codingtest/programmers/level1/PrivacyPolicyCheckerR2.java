package dev.dyko.codingtest.programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*################################
 * 플랫폼: 프로그래머스
 * 레벨: Level 1
 * 문제유형: 2023 KAKAO BLIND RECRUITMENT
 * URL: https://school.programmers.co.kr/learn/courses/30/lessons/150370
 * 비고: 개선된 코드
 * ################################
 */
public class PrivacyPolicyCheckerR2 {
    private final static int DAY_OF_MONTH = 28;
    private final static int MONTH_OF_YEAR = 12;

    public static void main (String[] args){
        int[] answer1 = new PrivacyPolicyCheckerR2().solution("2022.05.19", new String[] {"A 6", "B 12", "C 3"}, new String [] {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"});
        int[] answer2 = new PrivacyPolicyCheckerR2().solution("2020.01.01", new String[] {"Z 3", "D 5"}, new String [] {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"});

        System.out.println(Arrays.toString(answer1));
        System.out.println(Arrays.toString(answer2));
    }

    public int[] solution(String today, String[] terms, String[] privacies) {

        HashMap<String, Integer> policyMap = new HashMap<>();
        for(String policyInfo : terms){
            String policyType = policyInfo.split(" ")[0];
            String policyTerm = policyInfo.split(" ")[1];

            policyMap.put(policyType, Integer.parseInt(policyTerm));
        }

        List<Integer> deleteTarget = new ArrayList<>();

        for(int personNum = 1; personNum < privacies.length + 1; personNum++){
            String acceptanceDate = privacies[personNum - 1].split(" ")[0];
            String policyType = privacies[personNum - 1].split(" ")[1];

            if (getDaysByStrDate(today) >= getValidDate(acceptanceDate, policyMap.get(policyType))) {
                deleteTarget.add(personNum);
            }
        }

        return deleteTarget.stream().mapToInt(i -> i).toArray();
    }

    private int getDaysByStrDate(String date){
        String[] dateArr = date.split("\\.");
        return convertDateToDays(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]));
    }

    private int getValidDate(String acceptanceDate, int policyMonth) {
        return  getDaysByStrDate(acceptanceDate) + (policyMonth * DAY_OF_MONTH);
    }

    private int convertDateToDays(int year, int month, int day) {
        return (year * MONTH_OF_YEAR * DAY_OF_MONTH) + (month * DAY_OF_MONTH) + day;
    }
}
