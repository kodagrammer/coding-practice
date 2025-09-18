package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 문제: 카드뭉치
 *      영어 단어가 적힌 카드 뭉치 2개를 가지고 다음과 같은 규칙으로 카드에 적힌 단어들을 사용해 원하는 선서의 단어 배열을 만들 수 있는지 알고 싶습니다.
 *        - 원하는 카드 뭉치에서 카드를 순서대로 한 장씩 사용합니다.
 *        - 한 번 사용한 카드는 다시 사용할 수 없습니다.
 *        - 카드를 사용하지 않고 다음 카드로 넘어갈 수 없습니다.
 *        - 기존에 주어진 카드 뭉치의 단어 순서는 바꿀 수 없습니다.
 *      문자열로 이루어진 배열 cards1, cards2와 원하는 단어 배열 goal이 매개변수로 주어질 때,
 *      cards1과 cards2에 적힌 단어들로 goal을 만들 수 있다면 "Yes", 만들 수 없다면 "No"를 반환하는 solution() 함수를 완성하시오
 * 제약사항:
 *   - cards1, cards2 배열의 크기 : 1 ~ 10
 *     - 배열 내 문자열의 길이 : 1 ~ 10
 *     - 두 배열 내에는 서로 다른 단어만 존재
 *   - goal의 길이 : 2 ~ cards1.length + cards2.length
 *     - goal의 원소는 cards1, cards2의 원소들로만 이뤄짐
 *   - cards1, cards2, goal의 원소들은 모두 알파멧 소문자만 존재
 */
public class QueueExample2 {
    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        // 시간 복잡도 : O(2K) == O(K), 최대 40번
        // N: cards1의 길이, M: cards2의 길이, K: goal의 길이

        // 카드더미 셋팅 : O(N) + O(M) == O(K)
        Queue<String> cardQueue1 = new ArrayDeque<>(Arrays.asList(cards1));
        Queue<String> cardQueue2 = new ArrayDeque<>(Arrays.asList(cards2));

        // 만들 수 있는 문자열인지 확인 : O(K)
        for(String word : goal) {
            if(word.equals(cardQueue1.peek())) {
                cardQueue1.poll();
            } else if(word.equals(cardQueue2.peek())) {
                cardQueue2.poll();
            } else {
                return "No";
            }
        }
        return "Yes";
    }

    // 해설집
    public static String solution2(String[] cards1, String[] cards2, String[] goal) {
        ArrayDeque<String> cardQueue1 = new ArrayDeque<>(Arrays.asList(cards1));
        ArrayDeque<String> cardQueue2 = new ArrayDeque<>(Arrays.asList(cards2));
        ArrayDeque<String> goalQueue = new ArrayDeque<>(Arrays.asList(goal));

        while(!goalQueue.isEmpty()) {
            if(!cardQueue1.isEmpty() && cardQueue1.peekFirst().equals(goalQueue.peekFirst())) {
                cardQueue1.pollFirst();
                goalQueue.pollFirst();
            } else if(!cardQueue2.isEmpty() && cardQueue2.peekFirst().equals(goalQueue.peekFirst())) {
                cardQueue2.pollFirst();
                goalQueue.pollFirst();
            } else {
                break;
            }
        }

        return goalQueue.isEmpty() ? "Yes" : "No";
    }

    public static void main(String[] args) {
        String[] input1Cards1 = {"i", "drink", "water"};
        String[] input1Cards2 = {"want", "to"};
        String[] goal1 = {"i", "want", "to", "drink", "water"};
        String output1 = "Yes";
        String result1 = solution(input1Cards1, input1Cards2, goal1);
        Assertions.assertEquals(output1, result1);
        Assertions.assertEquals(output1, solution2(input1Cards1, input1Cards2, goal1));

        String[] input2Cards1 = {"i", "water", "drink"};
        String[] input2Cards2 = {"want", "to"};
        String[] goal2 = {"i", "want", "to", "drink", "water"};
        String output2 = "No";
        String result2 = solution(input2Cards1, input2Cards2, goal2);
        Assertions.assertEquals(output2, result2);
        Assertions.assertEquals(output2, solution2(input2Cards1, input2Cards2, goal2));

        String[] input3Cards1 = {"hello", "world", "test"};
        String[] input3Cards2 = {"java", "for", "code"};
        String[] goal3 = {"hello", "java", "world", "for", "code", "test"};
        String output3 = "Yes";
        String result3 = solution(input3Cards1, input3Cards2, goal3);
        Assertions.assertEquals(output3, result3);
        Assertions.assertEquals(output3, solution2(input3Cards1, input3Cards2, goal3));
    }
}
