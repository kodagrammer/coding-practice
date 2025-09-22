package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 할인 행사 (https://school.programmers.co.kr/learn/courses/30/lessons/131127)
 *      XYZ 마트는 일정한 금액을 지불하면 10일 동안 회원 자격을 부여합니다.
 *      XYZ 마트에서는 회원을 대상으로 매일 한 가지 제품을 할인하는 행사를 합니다. 할인하는 제품은 하루에 하나씩만 구매할 수 있습니다.
 *      알뜰한 정현이는 자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속으로 일치할 경우에 맞춰서 회원가입을 하려 합니다.
 *
 *      예를 들어, 정현이가 원하는 제품이 바나나 3개, 사과 2개, 쌀 2개, 돼지고기 2개, 냄비 1개이며,
 *      XYZ 마트에서 14일간 회원을 대상으로 할인하는 제품이 날짜 순서대로 치킨, 사과, 사과, 바나나, 쌀, 사과, 돼지고기, 바나나, 돼지고기, 쌀, 냄비, 바나나, 사과, 바나나인 경우에 대해 알아봅시다.
 *        - 첫째 날부터 열흘 간에는 냄비가 할인하지 않기 때문에 첫째 날에는 회원가입을 하지 않습니다.
 *        - 둘째 날부터 열흘 간에는 바나나를 원하는 만큼 할인구매할 수 없기 때문에 둘째 날에도 회원가입을 하지 않습니다.
 *        - 셋째 날, 넷째 날, 다섯째 날부터 각각 열흘은 원하는 제품과 수량이 일치하기 때문에 셋 중 하루에 회원가입을 하려 합니다.
 *
 *      정현이가 원하는 제품을 나타내는 문자열 배열 want와 정현이가 원하는 제품의 수량을 나타내는 정수 배열 number, XYZ 마트에서 할인하는 제품을 나타내는 문자열 배열 discount가 주어졌을 때,
 *      회원등록시 정현이가 원하는 제품을 모두 할인 받을 수 있는 회원등록 날짜의 총 일수를 return 하는 solution 함수를 완성하시오.
 *      가능한 날이 없으면 0을 return 합니다.
 * 제약사항:
 *   - 1 ≤ want의 길이 = number의 길이 ≤ 10
 *      - 1 ≤ number의 원소 ≤ 10
 *      - number[i]는 want[i]의 수량을 의미하며, number의 원소의 합은 10입니다.
 *   - 10 ≤ discount의 길이 ≤ 100,000
 *   - want와 discount의 원소들은 알파벳 소문자로 이루어진 문자열입니다.
 *      - 1 ≤ want의 원소의 길이, discount의 원소의 길이 ≤ 12
 */
public class HashExample2 {
    public static int solution(String[] want, int[] number, String[] discount){
        // 시간복잡도 : O(M+10N) == O(N)
        // N - discount 배열 길이, M - want 배열 길이

        // 구매희망 목록 맵 생성 : O(M)
        Map<String, Integer> wants = new HashMap<>();
        for (int i = 0; i < want.length; i++){
            wants.put(want[i], wants.getOrDefault(want[i], 0) + number[i]);
        }

        // 구매 가능 일수 계산 : O(10N)
        int result = 0;
        for(int i = 0; i < discount.length - 9; i++){
            HashMap<String, Integer> discounts10Days = new HashMap<>();
            for(int j = i; j < i + 10; j++){
                discounts10Days.put(discount[j], discounts10Days.getOrDefault(discount[j], 0) + 1);
            }

            if(wants.equals(discounts10Days)){
                result++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] want1 = {"banana", "apple", "rice", "pork", "pot"};
        int[] number1 = {3, 2, 2, 2, 1};
        String[] discount1 = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        int output1 = 3;
        int result1 = solution(want1, number1, discount1);
        Assertions.assertEquals(output1, result1);

        String[] want2 = {"apple"};
        int[] number2 = {10};
        String[] discount2 = {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"};
        int output2 = 0;
        int result2 = solution(want2, number2, discount2);
        Assertions.assertEquals(output2, result2);
    }
}
