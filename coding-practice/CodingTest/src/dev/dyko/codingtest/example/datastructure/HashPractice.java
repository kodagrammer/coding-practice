package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.HashSet;

public class HashPractice {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 8};
        int target1 = 6;
        boolean output1 = true;
        boolean result1 = solution(arr1, target1);
        Assertions.assertEquals(output1, result1);

        int[] arr2 = {2, 3, 5, 9};
        int target2 = 10;
        boolean output2 = false;
        boolean result2 = solution(arr2, target2);
        Assertions.assertEquals(output2, result2);
    }

    /**
     * 문제: 두 개의 수로 특정값 만들기
     *      n개의 양의 정수로 이루어진 배열 arr와 정수 target이 주어졌을 때, 이중에서 합이 target인 두 수가 arr에 있는지 찾고
     *      있으면 true, 없으면 false를 반환하는 solution() 함수를 작성하시오
     * 제약조건:
     *   - n은 2 이상 10,000 이하의 자연수
     *   - arr의 각 원소는 1 이상 10,000 이하의 자연수
     *   - arr의 원소 중 중복되는 원소는 없음
     *   - target은 1 이상 20,000 이하의 자연수
     * 입출력 예시:
     *   - 입: arr = [1, 2, 3, 4, 8], target = 6 / 출력 true
     *   - 입: arr = [2, 3, 5, 9], target = 10 / 출력 false
     */
    public static boolean solution(int[] arr, int target) {
        // arr를 순회하며 찾을 시, O(N^2) --> 효용성이 떨어짐
        // Hash를 사용해 시간복잡도 O(N)으로 개선
        HashSet<Integer> hashSet = new HashSet<>();

        // O(N)
        for(int i : arr){
            if(hashSet.contains(target - i)) {
                return true;
            }
            hashSet.add(i);
        }

        return false;
    }
}
