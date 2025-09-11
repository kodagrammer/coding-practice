package dev.dyko.codingtest.programmers.level0;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*################################
 * 플랫폼: 프로그래머스
 * 레벨: Level 0
 * 문제유형: 기초 트레이닝
 * URL: https://school.programmers.co.kr/learn/courses/30/lessons/181916
 * 비고:
 * ################################
 */
public class DiceGame {
    public int solution(int a, int b, int c, int d) {
        int answer = 0;

        int[] array = {a, b, c, d};
        Map<Integer, Integer> diceMap = new HashMap<>();

        for(int dice : array){
            if(diceMap.containsKey(dice)){
                diceMap.put(dice, diceMap.get(dice) + 1);
            } else {
                diceMap.put(dice, 1);
            }
        }

        if(diceMap.size() == 1){
            answer = 1111 * diceMap.keySet().iterator().next();
        } else if(diceMap.size() == 2) {
            if(diceMap.containsValue(2)) {
                int[] diceValues = diceMap.keySet().stream().mapToInt(i -> i).toArray();
                answer = (diceValues[0] + diceValues[1]) * Math.abs(diceValues[0] - diceValues[1]);
            } else {
                int p = diceMap.entrySet().stream().filter(e -> e.getValue() == 3).mapToInt(Map.Entry::getKey).findFirst().getAsInt();
                int q = diceMap.entrySet().stream().filter(e -> e.getValue() == 1).mapToInt(Map.Entry::getKey).findFirst().getAsInt();
                answer = (int) Math.pow(10 * p + q, 2);
            }
        } else if(diceMap.size() == 3) {
            int[] qrValues = diceMap.entrySet().stream().filter(e -> e.getValue() != 2).mapToInt(Map.Entry::getKey).toArray();
            answer = qrValues[0] * qrValues[1];
        } else {
            answer = Arrays.stream(array).min().getAsInt();
        }

        return answer;
    }
}
