package dev.dyko.codingtest.programmers.level1;

import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 문제: 포켓몬 (https://school.programmers.co.kr/learn/courses/30/lessons/1845)
 *      N마리 폰켓몬의 종류 번호가 담긴 배열 nums가 매개변수로 주어질 때,
 *      N/2마리의 폰켓몬을 선택하는 방법 중, 가장 많은 종류의 폰켓몬을 선택하는 방법을 찾아,
 *      그때의 폰켓몬 종류 번호의 개수를 return 하도록 solution 함수를 완성해주세요.
 * 제한사항:
 *   - nums는 폰켓몬의 종류 번호가 담긴 1차원 배열입니다.
 *   - nums의 길이(N)는 1 이상 10,000 이하의 자연수이며, 항상 짝수로 주어집니다.
 *   - 폰켓몬의 종류 번호는 1 이상 200,000 이하의 자연수로 나타냅니다.
 *   - 가장 많은 종류의 폰켓몬을 선택하는 방법이 여러 가지인 경우에도, 선택할 수 있는 폰켓몬 종류 개수의 최댓값 하나만 return 하면 됩니다.
 */
public class Pokemon {
    public static void main(String[] args) {
        Pokemon pokemon = new Pokemon();

        int[] nums1 = {3,1,2,3};
        int output1 = 2;
        int result1 = pokemon.solution2(nums1);
        Assertions.assertEquals(output1, result1);

        int[] nums2 = {3,3,3,2,2,4};
        int output2 = 3;
        int result2 = pokemon.solution2(nums2);
        Assertions.assertEquals(output2, result2);

        int[] nums3 = {3,3,3,2,2,2};
        int output3 = 2;
        int result3 = pokemon.solution2(nums3);
        Assertions.assertEquals(output3, result3);
    }

    // 시간복잡도 : O(N)
    public int solution(int[] nums) {
        Map<Integer, Integer> pokemap = new HashMap<>();

        // 포켓몬 유형 종류 구분 : O(nums.length) = O(10,000)
        for(int num : nums) {
            pokemap.put(num, pokemap.getOrDefault(num, 0) + 1);
        }

        // 가지고 갈 수 있는 포켓몬 수 : O(1), 항상 짝수니 별도 예외 처리X
        int getNum = nums.length / 2;

        // 종류가 가져갈 수 있는 수보다 높으면, 가능한 한도로 1마리씩 가져갈 수 있음
        // 종류가 가져갈 수 있는 수보다 적으면, 종류 1가지씩과 그 외 중복되는 수를 가져갈 수 있음
        return Math.min(pokemap.size(), getNum);
    }

    // Big-O는 같지만, 불필요한 카운팅을 제거한 최적화 버전
    public int solution2(int[] nums) {
        // 포켓몬 수 중복 제거 : O(N)
        Set<Integer> pokeset = new HashSet<>();

        for (int num : nums) {
            pokeset.add(num);
        }

        // N의 수가 작은 경우, Stream도 사용할 수 있으나 실무에서 N의 크기가 큰 경우 비효율적
        // Stream 내부적으로 Iterator 등의 추가 객체를 생성하고, boxing 하며 일반 for문보다 2배의 객체를 생성하게 됨
        // Set<Integer> pokeset = Arrays.stream(nums).boxed().collect(Collectors.toSet());

        return Math.min(pokeset.size(), nums.length / 2);
    }
}
