package dev.dyko.codingtest.programmers.level0;

/*################################
 * 플랫폼: 프로그래머스
 * 레벨: Level 0
 * 문제유형: 기초 트레이닝
 * URL: https://school.programmers.co.kr/learn/courses/30/lessons/181884
 * 비고:
 * ################################
*/
public class AddNumberUnderN {
    public int solution(int[] numbers, int n) {
        if(numbers.length < 1 || numbers.length > 100) return 0;

        int sum = 0;
        for(int number : numbers){
            if(sum > n) break;
            sum += number;
        }
        return sum;
    }

    public static void main(String[] args) {
        AddNumberUnderN addNumberUnderN = new AddNumberUnderN();

        // test 1
        int[] numbers1 = {34, 5, 71, 29, 100, 34};
        int n1 = 123;
        int result1 = addNumberUnderN.solution(numbers1, n1);
        boolean testResult1 = result1 == 139;
        System.out.printf("첫번째 테스트 결과 : 결과값 %d, 테스트 통과여부 %s%n", result1, testResult1);

        // test 1
        int[] numbers2 = {58, 44, 27, 10, 100};
        int n2 = 139;
        int result2 = addNumberUnderN.solution(numbers2, n2);
        boolean testResult2 = result2 == 239;
        System.out.printf("두번째 테스트 결과 : 결과값 %d, 테스트 통과여부 %s%n", result2, testResult2);
    }
}
