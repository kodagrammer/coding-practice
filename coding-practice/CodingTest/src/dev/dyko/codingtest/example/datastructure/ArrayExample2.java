package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 문제: 모의고사
 *      수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다. 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.
 *       - 1번 수포자가 찍는 방식 : 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
 *       - 2번 수포자가 찍는 방식 : 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
 *       - 3번 수포자가 찍는 방식 : 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
 *      1번 문제부터 마지막 문제까지의 정답이 순서대로 저장된 배열 answers가 주어졌을 때 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 반환하도록 solution( ) 함수를 작성하세요.
 * 제약사항:
 *   - 시험은 최대 10,000 문제로 구성되어 있습니다.
 *   - 문제의 정답은 1, 2, 3, 4, 5 중 하나입니다.
 *   - 가장 높은 점수를 받은 사람이 여럿이라면 반환하는 값을 오름차순으로 정렬하세요.
 */
public class ArrayExample2 {
    public static int[] solution(int[] answers) {
        // 시간복잡도: O(N)
        // - N: 시험 문제 개수

        int[][] patterns = { {1, 2, 3, 4, 5}
                           , {2, 1, 2, 3, 2, 4, 2, 5}
                           , {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};

        // 수포자별 점수 계산
        int[] scores = new int[3];

        for(int i = 0 ; i < answers.length ; i++) { // O(N)
            int answer = answers[i];
            for(int j = 0; j < patterns.length ; j++) { // pattern의 길이가 3 상수, O(1)
                int[] pattern = patterns[j];
                if(answer == pattern[i % pattern.length]) { scores[j]++; }
            }
        }

        // 가장 높은 점수 도출 : O(1)
        int maxScore = Arrays.stream(scores).max().getAsInt();

        // 높은 점수 도달한 수포자 도출 : O(1)
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < scores.length ; i++) {
            if(scores[i] == maxScore) {
                list.add(i + 1);
            }
        }

        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] input1 = {1, 2, 3, 4, 5};
        int[] output1 = {1};
        int[] result1 = solution(input1);
        Assertions.assertArrayEquals(output1, result1);

        int[] input2 = {1, 3, 2, 4, 2};
        int[] output2 = {1, 2, 3};
        int[] result2 = solution(input2);
        Assertions.assertArrayEquals(output2, result2);

        int[] input3 = {3, 3, 2, 1, 5};
        int[] output3 = {3};
        int[] result3 = solution(input3);
        Assertions.assertArrayEquals(output3, result3);

        int[] input4 = {2, 5, 3, 4, 2};
        int[] output4 = {1, 2};
        int[] result4 = solution(input4);
        Assertions.assertArrayEquals(output4, result4);
    }
}
