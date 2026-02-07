package dev.dyko.codingtest.programmers.level1;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 모의고사 (https://school.programmers.co.kr/learn/courses/30/lessons/42840)
 *      1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때,
 *      가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.
 *        - 1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
 *        - 2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
 *        - 3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
 * 제약사항:
 *    - 시험은 최대 10,000 문제로 구성되어있습니다.
 *    - 문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
 *    - 가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.
 */
public class MockExam {
    public static void main(String[] args) {
        MockExam exam = new MockExam();

        int[] answer1 = {1,2,3,4,5};
        int[] output1 = {1};
        int[] result1 = exam.solution(answer1);
        Assertions.assertArrayEquals(output1, result1);

        int[] answer2 = {1,3,2,4,2};
        int[] output2 = {1,2,3};
        int[] result2 = exam.solution(answer2);
        Assertions.assertArrayEquals(output2, result2);
    }

    public int[] solution(int[] answers) {
        // 답변 패턴
        int[][] patterns = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        int[] scores = new int[patterns.length];

        // 답별로 답안 체크 : O(N) = O (10,000)
        for(int answerIdx = 0; answerIdx < answers.length; answerIdx++) {
            for(int manIdx = 0; manIdx < patterns.length; manIdx++) {
                int[] pattern = patterns[manIdx];
                int pAnswer = pattern[answerIdx % pattern.length];

                if(answers[answerIdx] == pAnswer) {
                    scores[manIdx]++;
                }
            }
        }

        // 최다 득점자 확인 : O(1)
        List<Integer> result = new ArrayList<>();
        int max = Arrays.stream(scores).max().getAsInt();
        for(int i = 0; i < scores.length; i++) {
            if(scores[i] == max) {
                result.add(i + 1);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();  // O(1)
    }
}
