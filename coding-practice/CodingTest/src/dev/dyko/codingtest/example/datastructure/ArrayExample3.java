package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

/**
 * 문제: 행렬의 곱셈
 *      2차원 행렬 arr1과 arr2를 입력받아 arr1에 arr2를 곱한 결과를 반환하는 solution( ) 함수를 완성하세요.
 * 제약사항:
 *   - 행렬 arr1, arr2의 행과 열의 길이는 2 이상 100 이하입니다.
 *   - 행렬 arr1, arr2의 데이터는 -10 이상 20 이하인 자연수입니다.
 *   - 곱할 수 있는 배열만 주어집니다.
 */
public class ArrayExample3 {
    public static int[][] solution(int[][] arr1, int[][] arr2) {
        // 시간복잡도: O(N^3)
        // N: arr1, arr2의 배열 길이
        // arr1의 열의 개수 == arr2의 행의 개수
        // arr1의 행의 개수 == arr2의 열의 개수

        int[][] result = new int[arr1.length][arr2[0].length];

        // 배열 곱셈
        for (int i = 0; i < arr1.length; i++) {
            for(int j = 0; j < arr2[0].length; j++) {
                for(int k = 0; k < arr1[i].length; k++) {
                    result[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arr1Input1 = {{1, 4},{3, 2}, {4, 1}};
        int[][] arr2Input1 = {{3, 3}, {3, 3}};
        int[][] output1 = {{15, 15}, {15, 15}, {15, 15}};
        int[][] result1 = solution(arr1Input1, arr2Input1);
        Assertions.assertArrayEquals(output1, result1);

        int[][] arr1Input2 = {{2, 3, 2},{4, 2, 4}, {3, 1, 4}};
        int[][] arr2Input2 = {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};
        int[][] output2 = {{22, 22, 11}, {36, 28, 18}, {29, 20, 14}};
        int[][] result2 = solution(arr1Input2, arr2Input2);
        Assertions.assertArrayEquals(output2, result2);

        int[][] arr1Input3 = {{-10, 2, 12, -4}, {-1, 0, 5, 7}, {9, 3, -7, 11}};
        int[][] arr2Input3 = {{1, 7, 9}, {-1, 5, 7}, {0, 4, -5}, {9, -2, 2}};
        int[][] output3 = {{-48,  -4, -144}, {62,  -1,  -20}, {105,  28,  159}};
        int[][] result3 = solution(arr1Input3, arr2Input3);
        Assertions.assertArrayEquals(output3, result3);
    }
}
