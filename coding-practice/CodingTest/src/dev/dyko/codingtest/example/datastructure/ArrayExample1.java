package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;
import java.util.HashSet;

/**
 * 문제: 두개 뽑아서 더하기
 *      정수배열 numbers에서 서로 다른 인덱스에 있는 2개의 수를 뽑아 더해 만들 수 있는 모든 수를 배열에 오름차순으로 담아 반환하는 solution( ) 함수를 완성하세요.
 * 제약사항:
 *   - numbers의 길이는 2 이상 100 이하입니다.
 *   - numbers의 모든 수는 0 이상 100 이하입니다.
 */
public class ArrayExample1 {
    public static int[] solution(int[] arr) {
        // 평균 시간복잡도: O(N^2 + M log M)
        // 최악 시간복잡도: 최악의 경우 M=N^2 --> O(N^2 log(N^2))
        // - N: 입력 배열 크기
        // - M: HashSet에 저장된 유니크한 합의 개수 (최대 N^2)

        HashSet<Integer> set = new HashSet<>();
        // 두 수의 합을 구해 HashSet에 추가
        // 반복문: O(N^2), HashSet 삽입: 평균 O(1)
        // 총 시간복잡도: O(N^2)
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = i + 1; j < arr.length; j++){
                set.add(arr[i] + arr[j]);
            }
        }

        // HashSet → Stream 변환 후 정렬
        // 정렬: O(M log M), 변환(mapToInt, toArray): O(M)
        // 총 시간복잡도: O(M log M)
        return set.stream()
                .sorted()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public static void main(String[] args) {
        int[] input1 = {2, 1, 3, 4, 1};
        int[] output1 = {2, 3, 4, 5, 6, 7};
        int[] result1 = solution(input1);
        Assertions.assertArrayEquals(output1, result1);

        int[] input2 = {5, 0, 2, 7};
        int[] output2 = {2, 5, 7, 9, 12};
        int[] result2 = solution(input2);
        Assertions.assertArrayEquals(output2, result2);

        int[] input3 = {1, 2, 3, 4, 5};
        int[] output3 = {3, 4, 5, 6, 7, 8, 9};
        int[] result3 = solution(input3);
        Assertions.assertArrayEquals(output3, result3);
    }
}
