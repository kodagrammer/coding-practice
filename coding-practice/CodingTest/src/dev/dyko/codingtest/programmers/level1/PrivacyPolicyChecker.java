package dev.dyko.codingtest.programmers.level1;

import java.util.*;

/*################################
 * 플랫폼: 프로그래머스
 * 레벨: Level 1
 * 문제유형: 2023 KAKAO BLIND RECRUITMENT
 * URL: https://school.programmers.co.kr/learn/courses/30/lessons/150370
 * ################################
 */
public class PrivacyPolicyChecker {

    public static void main (String[] args){
        int[] answer1 = new PrivacyPolicyChecker().solution("2022.05.19", new String[] {"A 6", "B 12", "C 3"}, new String [] {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"});
        int[] answer2 = new PrivacyPolicyChecker().solution("2020.01.01", new String[] {"Z 3", "D 5"}, new String [] {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"});

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
            String personalPrivacy = privacies[personNum - 1];
            String acceptanceDate = personalPrivacy.split(" ")[0];
            String policyType = personalPrivacy.split(" ")[1];
            PersonalPrivacyInfo info = new PersonalPrivacyInfo(acceptanceDate, policyMap.get(policyType));

            if (info.isUnvalid(today)) {
                deleteTarget.add(personNum);
            }
        }

        return deleteTarget.stream().mapToInt(i -> i).toArray();
    }

    private class PersonalPrivacyInfo {
        private DateBy28Day acceptanceDate;
        private DateBy28Day validDate;

        public PersonalPrivacyInfo(String acceptanceDate, int policyTerm) {
            this.acceptanceDate = new DateBy28Day(acceptanceDate);

            DateBy28Day validDate = new DateBy28Day(this.acceptanceDate);
            validDate.addMonth(policyTerm);
            validDate.addDay(-1);
            this.validDate = validDate;
        }

        public boolean isUnvalid(String today){
            return this.validDate.compare(new DateBy28Day(today)) < 0;
        }
    }

    public class DateBy28Day {
        private final static int DAY_OF_MONTH = 28;
        private final static int MONTH_OF_YEAR = 12;
        private int year;
        private int month;
        private int day;

        public DateBy28Day(String dateStr) {
            String[] dateArr = dateStr.split("\\.");
            this.year = Integer.parseInt(dateArr[0]);
            this.month = Integer.parseInt(dateArr[1]);
            this.day = Integer.parseInt(dateArr[2]);
        }

        public DateBy28Day (DateBy28Day date){
            this.year = date.year;
            this.month = date.month;
            this.day = date.day;
        }

        public void addMonth(int addMonthValue){
            this.year += addMonthValue/MONTH_OF_YEAR;
            this.month += addMonthValue%MONTH_OF_YEAR;

            if(this.month < 1) {
                this.month += MONTH_OF_YEAR;
                this.year--;
            } else if (this.month > MONTH_OF_YEAR){
                this.month -= MONTH_OF_YEAR;
                this.year++;
            }
        }

        public void addDay(int addDayValue){
            this.addMonth(addDayValue/DAY_OF_MONTH);
            this.day += addDayValue%DAY_OF_MONTH;

            if(this.day < 1){
                this.day += DAY_OF_MONTH;
                this.month--;
            } else if (this.day > DAY_OF_MONTH){
                this.day -= DAY_OF_MONTH;
                this.month++;
            }
        }

        public int compare (DateBy28Day compareDate) {
            if(this.year == compareDate.year && this.month == compareDate.month && this.day == compareDate.day){
                return 0;
            } else if(this.year < compareDate.year) {
                return -1;
            } else if(this.year > compareDate.year) {
                return 1;
            } else {
                if(this.month < compareDate.month) {
                    return -1;
                } else if (this.month > compareDate.month) {
                    return 1;
                } else {
                    return this.day < compareDate.day ? -1 : 1;
                }
            }
        }
    }
}
