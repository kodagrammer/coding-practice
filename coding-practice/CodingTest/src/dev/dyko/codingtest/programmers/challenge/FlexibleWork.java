package dev.dyko.codingtest.programmers.challenge;

import org.junit.Assert;

import java.time.LocalTime;

/**
 * 문제: 유연근무제(https://school.programmers.co.kr/learn/courses/30/lessons/388351)
 * 제한사항:
 *   - 1 ≤ schedules의 길이 = n ≤ 1,000
 *     - 700 ≤ schedules[i] ≤ 1100
 *   - 1 ≤ timelogs의 길이 = n ≤ 1,000
 *     - timelogs[i]의 길이 = 7
 *     - timelogs[i][j]는 i + 1번째 직원이 이벤트 j + 1일차에 출근한 시각을 의미합니다.
 *     - 600 ≤ timelogs[i][j] ≤ 2359
 *   - 1 ≤ startday ≤ 7
 *     - 1~7 : 월~일
 */
public class FlexibleWork {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        // 시간복잡도 : O(7N) --> N: 직원수 --> 최악연산수 7,000
        // 공간복잡도 개선하려면 객체화X
        for(int i = 0; i < timelogs.length; i++) {
            // 직원의 목표 확인
            ArrivalTimeChecker arrivalTimeChecker = new ArrivalTimeChecker(schedules[i]);

            // 직원 별 성공 여부 확인
            boolean isSuccess = true;
            for(int j = 0; j < timelogs[i].length; j++) {
                // 주말인 경우 출근시간 무시
                int weekday =  (startday + j) % 7;
                if(weekday == 0 || weekday == 6) { continue; }

                if(arrivalTimeChecker.isLate(timelogs[i][j])) {
                    isSuccess = false;
                    break;
                }
            }

            if(isSuccess) { answer++; }
        }

        return answer;
    }

    private class ArrivalTimeChecker {
        private final LocalTime limitTime;

        public ArrivalTimeChecker(int expectedTime) {
            LocalTime temp = LocalTime.of(expectedTime/100, expectedTime%100);
            this.limitTime = temp.plusMinutes(10);
        }

        public boolean isLate(int arrivedTime){
            LocalTime arrived = LocalTime.of(arrivedTime/100, arrivedTime%100);
            return arrived.isAfter(limitTime);
        }
    }

    public static void main(String[] args) {
        FlexibleWork flexibleWork = new FlexibleWork();

        int[] schedules1 = {700, 800, 1100};
        int[][] timelogs1 = {{710, 2359, 1050, 700, 650, 631, 659}
                           , {800, 801, 805, 800, 759, 810, 809}
                           , {1105, 1001, 1002, 600, 1059, 1001, 1100}};
        int startday1 = 5;
        int output1 = 3;
        int result1 = flexibleWork.solution(schedules1, timelogs1, startday1);
        Assert.assertEquals(output1, result1);

        int[] schedules2 = {730, 855, 700, 720};
        int[][] timelogs2 = {{710, 700, 650, 735, 700, 931, 912}
                           , {908, 901, 805, 815, 800, 831, 835}
                           , {705, 701, 702, 705, 710, 710, 711}
                           , {707, 731, 859, 913, 934, 931, 905}};
        int startday2 = 1;
        int output2 = 2;
        int result2 = flexibleWork.solution(schedules2, timelogs2, startday2);
        Assert.assertEquals(output2, result2);
    }
}
