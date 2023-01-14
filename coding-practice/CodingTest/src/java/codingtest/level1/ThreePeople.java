package java.codingtest.level1;

/*################################
 * 플랫폼: 프로그래머스
 * 레벨: Level 1
 * 문제유형: 연습문제
 * URL: https://school.programmers.co.kr/learn/courses/30/lessons/131705
 * ################################
 */

public class ThreePeople {
    public int solution(int[] number) {
        int answer = 0;

        for(int firstIdx = 0; firstIdx < number.length -2; firstIdx++){
            for(int secondIdx = firstIdx + 1; secondIdx < number.length - 1; secondIdx++){
                for(int thirdIdx = secondIdx + 1; thirdIdx < number.length; thirdIdx++){
                    if(number[firstIdx] + number[secondIdx] + number[thirdIdx] == 0) answer++;
                }
            }
        }
        return answer;
    }
}
