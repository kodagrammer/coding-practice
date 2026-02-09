package dev.dyko.codingtest.programmers.level3;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 단어 변환(https://school.programmers.co.kr/learn/courses/30/lessons/43163)
 *      변환 규칙
 *        1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
 *        2. words에 있는 단어로만 변환할 수 있습니다.
 *
 *      예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면
 *      "hit" -> "hot" -> "dot" -> "dog" -> "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.
 *
 *      두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때,
 *      최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.
 * 제약 사항:
 *   - 각 단어는 알파벳 소문자로만 이루어져 있습니다.
 *   - 각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
 *   - words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
 *   - begin과 target은 같지 않습니다.
 *   - 변환할 수 없는 경우에는 0를 return 합니다.
 */
public class TransferWord {
    public static void main(String[] args) {
        TransferWord transferWord = new TransferWord();

        String begin1 = "hit";
        String target1 = "cog";
        String[] words1 = {"hot", "dot", "dog", "lot", "log", "cog"};
        int output1 = 4;
        int result1 = transferWord.solution(begin1, target1, words1);
        Assertions.assertEquals(output1, result1);

        String begin2 = "hit";
        String target2 = "cog";
        String[] words2 = {"hot", "dot", "dog", "lot", "log"};
        int output2 = 0;
        int result2 = transferWord.solution(begin2, target2, words2);
        Assertions.assertEquals(output2, result2);

        String begin3 = "dotia";
        String target3 = "brait";
        String[] words3 = {"lotia", "botia", "brait", "doaia", "batia", "baria", "draia", "braia"};
        int output3 = 4;
        int result3 = transferWord.solution(begin3, target3, words3);
        Assertions.assertEquals(output3, result3);
    }

    // words 배열의 길이: N, 변경할 문자열 길이: M
    public int solution(String begin, String target, String[] words) {
        // Target이 배열에 없다면, 종료 : O(N)
        if(!containsTarget(words, target)) { return 0; }

        // BFS, 시작지점 설정
        Queue<Node> queue  = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(new Node(0, begin));
        visited.add(begin);

        while (!queue.isEmpty()) {
            Node currNode = queue.poll();

            // 목표 도달 (최단 거리)
            if(target.equals(currNode.word)) {
                return currNode.stepCnt;
            }

            // 다음 단어 탐색 : O(NM) = O(500)
            for(String nextWord : words) {
                if(!visited.contains(nextWord) && currNode.canChange(nextWord)) {
                    queue.offer(new Node(currNode.stepCnt + 1, nextWord));
                    visited.add(nextWord);
                }
            }
        }

        return 0;
    }

    private boolean containsTarget(String[] words, String target) {
        for (String word : words) {
            if(word.equals(target)) return true;
        }
        return false;
    }
    
    private static class Node {
        private final int stepCnt;
        private final String word;

        public Node(int stepCnt, String word) {
            this.stepCnt = stepCnt;
            this.word = word;
        }

        // O(M)
        public boolean canChange(String nextWord) {
            int diffChar = 0;

            for(int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != nextWord.charAt(i)) {
                    // 2개 이상의 다른 알파벳이 있으면, 변경 불가
                    if (++diffChar > 1) return false;
                }
            }

            return diffChar == 1;
        }
    }
}
