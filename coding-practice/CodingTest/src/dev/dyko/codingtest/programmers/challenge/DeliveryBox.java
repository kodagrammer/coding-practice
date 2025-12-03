package dev.dyko.codingtest.programmers.challenge;

import org.junit.jupiter.api.Assertions;

/**
 * 문제: 택배박스 꺼내기 (https://school.programmers.co.kr/learn/courses/30/lessons/389478)
 * 제한사항:
 *   - 2 ≤ n ≤ 100
 *   - 1 ≤ w ≤ 10
 *   - 1 ≤ num ≤ n
 */
public class DeliveryBox {
    public static void main(String[] args) {
        DeliveryBox deliveryBox = new DeliveryBox();

        int n1 = 22;
        int w1 = 6;
        int num1 = 8;
        int output1 = 3;
        int result1 = deliveryBox.solution(n1, w1, num1);
        Assertions.assertEquals(output1, result1);

        int n2 = 13;
        int w2 = 3;
        int num2 = 6;
        int output2 = 4;
        int result2 = deliveryBox.solution(n2, w2, num2);
        Assertions.assertEquals(output2, result2);

        int n3 = 7;
        int w3 = 1;
        int num3 = 3;
        int output3 = 5;
        int result3 = deliveryBox.solution(n3, w3, num3);
        Assertions.assertEquals(output3, result3);
    }

    // 시간복잡도 : O(N - num)
    // 최악의 경우 : n = 100, w = 1, num = 1 --> 99회
    public int solution(int n, int w, int num) {
        // num 박스가 쌓이는 스택의 열 추적 : O(1)
        int stackLine = getStackLine(num, w);

        // num 박스 위로 쌓인 박스 개수 추적 : O(N - num)
        int stackedOn = 0;

        for(int i = num + 1; i <= n; i++) {
            if(stackLine == getStackLine(i, w)) {
                stackedOn++;
            }
        }

        return stackedOn + 1;   // 타겟 박스도 포함한 박스 수 반환
    }

    private int getStackLine(int num, int w) {
        return ((num - 1)/w) % 2 == 0
                ? (num + w - 1) % w              // 홀수 행일 때 쌓이는 스택 열 번호
                : w - ((num + w - 1) % w) - 1;   // 짝수 행일 때 쌓이는 스택 열 번호
    }
}
