package dev.dyko.codingtest.programmers.level2;

import org.junit.jupiter.api.Assertions;

/**
 * 문제: 모음사전(https://school.programmers.co.kr/learn/courses/30/lessons/84512)
 *      사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 길이 5 이하의 모든 단어가 수록되어 있습니다.
 *      사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.
 *      단어 하나 word가 매개변수로 주어질 때, 이 단어가 사전에서 몇 번째 단어인지 return 하도록 solution 함수를 완성해주세요.
 * 제한사항:
 *   - word의 길이는 1 이상 5 이하입니다.
 *   - word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.
 */
public class VowelDictionary {
    public static void main(String[] args) {
        VowelDictionary vowelDictionary = new VowelDictionary();

        String word1 = "AAAAE";
        int output1 = 6;
        int result1 = vowelDictionary.solution(word1);
        Assertions.assertEquals(output1, result1);

        String word2 = "AAAE";
        int output2 = 10;
        int result2 = vowelDictionary.solution(word2);
        Assertions.assertEquals(output2, result2);

        String word3 = "I";
        int output3 = 1563;
        int result3 = vowelDictionary.solution(word3);
        Assertions.assertEquals(output3, result3);

        String word4 = "EIO";
        int output4 = 1189;
        int result4 = vowelDictionary.solution(word4);
        Assertions.assertEquals(output4, result4);
    }

    public int solution(String word) {
        // 각 자리의 가중치
        // weights[i] = 해당 자리에서 문자 하나가 바뀔 때 건너뛰는 단어 수
        int[] weights = {781, 156, 31, 6, 1};
        int answer = 0;

        for (int i = 0; i < word.length(); i++) {
            // "AEIOU".indexOf(word.charAt(i)) → 0,1,2,3,4
            int idx = "AEIOU".indexOf(word.charAt(i));
            answer += idx * weights[i] + 1;
        }

        return answer;
    }
}
