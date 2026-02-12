package dev.dyko.codingtest.programmers.level2;

import org.junit.jupiter.api.Assertions;

/**
 * 문제: 카펫(https://school.programmers.co.kr/learn/courses/30/lessons/428420
 *      Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.
 *      Leo는 집으로 돌아와서 아까 본 카펫의 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.
 *      Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때
 *      카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.
 * 제한사항:
 *   - 갈색 격자의 수 brown은 8 이상 5,000 이하인 자연수입니다.
 *   - 노란색 격자의 수 yellow는 1 이상 2,000,000 이하인 자연수입니다.
 *   - 카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.
 */
public class Carpet {
    public static void main(String[] args) {
        Carpet carpet = new Carpet();

        int brown1 = 10;
        int yellow1 = 2;
        int[] output1 = {4, 3};
        int[] result1 = carpet.solution(brown1, yellow1);
        Assertions.assertArrayEquals(output1, result1);

        // 최소 크기
        int brown2 = 8;
        int yellow2 = 1;
        int[] output2 = {3, 3};
        int[] result2 = carpet.solution(brown2, yellow2);
        Assertions.assertArrayEquals(output2, result2);

        int brown3 = 24;
        int yellow3 = 24;
        int[] output3 = {8, 6};
        int[] result3 = carpet.solution(brown3, yellow3);
        Assertions.assertArrayEquals(output3, result3);
    }

    public int[] solution(int brown, int yellow) {
        // 가로, 세로 연관식
        // width * height = brown + yellow
        // (width + height - 2) * 2 = brown
        int area = brown + yellow;
        int perimeter = brown/2 + 2;

        // 최소 세로 길이(3) ~ 정사각형 기준까지 탐색 : O(√area)
        for(int height = 3; height <= Math.sqrt(area); height++) {
            if(area % height == 0) {
                int width = area / height;
                if(width + height == perimeter) {
                    return new int[]{width, height};
                }
            }
        }

        return new int[] {};
    }
}
