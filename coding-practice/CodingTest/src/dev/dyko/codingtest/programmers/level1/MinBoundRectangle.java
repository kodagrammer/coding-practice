package dev.dyko.codingtest.programmers.level1;

import org.junit.jupiter.api.Assertions;

/**
 * 문제: 최소직사각형 (https://school.programmers.co.kr/learn/courses/30/lessons/86491)
 *      모든 명함의 가로 길이와 세로 길이를 나타내는 2차원 배열 sizes가 매개변수로 주어집니다.
 *      모든 명함을 수납할 수 있는 가장 작은 지갑을 만들 때,
 *      지갑의 크기를 return 하도록 solution 함수를 완성해주세요.
 * 제약사항:
 *   - sizes의 길이는 1 이상 10,000 이하입니다.
 *      - sizes의 원소는 [w, h] 형식입니다.
 *      - w는 명함의 가로 길이를 나타냅니다.
 *      - h는 명함의 세로 길이를 나타냅니다.
 *      - w와 h는 1 이상 1,000 이하인 자연수입니다.
 */
public class MinBoundRectangle {
    public static void main(String[] args) {
        MinBoundRectangle minBoundRectangle = new MinBoundRectangle();

        int[][] sizes1 = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int output1 = 4000;
        int result1 = minBoundRectangle.solution(sizes1);
        Assertions.assertEquals(output1, result1);

        int[][] sizes2 = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};
        int output2 = 120;
        int result2 = minBoundRectangle.solution(sizes2);
        Assertions.assertEquals(output2, result2);

        int[][] sizes3 = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}};
        int output3 = 133;
        int result3 = minBoundRectangle.solution(sizes3);
        Assertions.assertEquals(output3, result3);
    }

    public int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxHeight = 0;

        // 명함 확인 : O(sizes.length) = O(10,000)
        for(int[] size: sizes) {
            // 명함을 돌려서 끼우는 경우, 처리를 위해 큰 면이 항상 너비가 되도록 처리
            int width = Math.max(size[0], size[1]);
            int height = Math.min(size[0], size[1]);

            maxWidth = Math.max(maxWidth, width);
            maxHeight = Math.max(maxHeight, height);
        }

        return maxWidth * maxHeight;
    }
}
