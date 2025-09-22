package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;

/**
 * 문제: 완주하지 못한 선수 (https://school.programmers.co.kr/learn/courses/30/lessons/42576)
 *      수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
 *      마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때,
 *      완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.
 * 제약사항:
 *   - 마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
 *   - completion의 길이는 participant의 길이보다 1 작습니다.
 *   - 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
 *   - 참가자 중에는 동명이인이 있을 수 있습니다.
 */
public class HashExample1 {
    public static String solution(String[] participant, String[] completion){
        // 시간복잡도 : O(N-1) + O(N) = 199,999번 연산 : 가용범위 내
        HashMap<String, Integer> map = new HashMap<>();

        // O(N-1)
        for(String s : completion){
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        // O(N)
        for(String s : participant){
            if(map.getOrDefault(s, 0) == 0){
                return s;
            }
            map.put(s, map.getOrDefault(s, 0) - 1);
        }
        return null;
    }

    public static void main(String[] args) {
        String[] participant1 = {"leo", "kiki", "eden"};
        String[] completion1 = {"eden", "kiki"};
        String output1 = "leo";
        String result1 = solution(participant1, completion1);
        Assertions.assertEquals(output1, result1);

        String[] participant2 = {"mislav", "stanko", "mislav", "ana"};
        String[] completion2 = {"stanko", "ana", "mislav"};
        String output2 = "mislav";
        String result2 = solution(participant2, completion2);
        Assertions.assertEquals(output2, result2);

        String[] participant3 = {"a", "a", "a", "b", "c", "d"};
        String[] completion3 = {"a", "a", "a", "b", "d"};
        String output3 = "c";
        String result3 = solution(participant3, completion3);
        Assertions.assertEquals(output3, result3);
    }
}
