package dev.dyko.codingtest.example.datastructure;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class ArrayExample {
    // 크기를 정확히 알 때 -> 배열, 정확하지 않을 때(동적) -> ArrayList

    public static void main(String[] args) {
        // 배열 정렬문제
        int[] array1 = {10000, -10452, -42, 3, 1345, 6022, -4322, 14342, 100000, -100234};              // 입력
        int[] predictedResult1 = {-100234, -10452, -4322, -42, 3, 1345, 6022, 10000, 14342, 100000};    // 예상출력

        int[] result1 = sortArray(array1);
        System.out.println("예상결과가 "
                        + (Arrays.equals(result1, predictedResult1) ? "맞았습니다." : "틀렸습니다.")
                        + "\n" + Arrays.toString(result1));

        // 배열 중복제거 후 정렬 문제
        int[] array2 = {2, 5, 2, 6, 7, 8, 6, -1, 3, -2, -1};
        int[] predictedResult2 = {-1, -2, 2, 3, 5, 6, 7, 8};

        int[] result2 = controlArray(array2);
        System.out.println("예상결과가 "
                + (Arrays.equals(result2, predictedResult2) ? "맞았습니다." : "틀렸습니다.")
                + "\n" + Arrays.toString(result2));
    }

    /**
     * 문제: 정수 배열을 정렬해서 반환하는 함수를 완성하시오
     * 제약조건:
     *   - 정수 배열의 길이는 2~10^5
     *   - 배열의 각 데이터 값은 -100,000~100,000
     * 입출력 예시:
     *   - 입: [1, -5, 2, 4, 3] / 출: [-5, 1, 2, 3, 4]
     */
    public static int[] sortArray(int[] array) {
        // 직접 구현(버블정렬) 시, 시간 복잡도 O(N^2)
        // Arrays 콜렉션의 sort 함수는 "분할정복" 알고리즘 사용, 시간복잡도 O(NlogN)
        int[] result = array.clone();
        Arrays.sort(result);
        return result;
    }

    /**
     * 문제: 정수 배열의 중복값을 제거하고 배열 데이터를 내림차순으로 정렬해서 반환하는 함수를 구현하세요.
     * 제약조건:
     *   - 배열 길이는 2 이상 1,000 이하
     *   - 각 배열의 데이터 값은 -100,000 이상 100,000 이하
     * 입출력 예시:
     *   - 입: [4, 2, 2, 1, 3, 4] / 출: [4, 3, 2, 1]
     */
    public static int[] controlArray(int[] array) {
        // 직접 구현 시, 중복제거 시간복잡도 O(N^2), 정렬 시간복잡도 O(N^2) --> O(N^2)
        // Array 콜렉션 및 스트림 활용, 중복제거 시간복잡도 O(N), 정렬 시간복잡도 O(NlogN) --> O(NlogN)
        Integer[] distinctArray = Arrays.stream(array).boxed().distinct().toArray(Integer[]::new);
        Arrays.sort(distinctArray, Collections.reverseOrder());
        return Arrays.stream(distinctArray).mapToInt(Integer::intValue).toArray();
    }

    // 스트림 없이 구현 시, TreeSet 활용
    public static int[] controlArray2(int[] array) {
        TreeSet<Integer> treeSet = new TreeSet<>(Collections.reverseOrder());   // default 오름차순이므로 선언 시 내림차순으로 설정

        // 총 시간 복잡도 O(NlogN)
        for(int i : array) {
            treeSet.add(i); // 균형 이진 탐색 트리 활용 : O(log N)
        }

        int[] result = new int[treeSet.size()];
        for(int index = 0; index < result.length; index++) {
               result[index] = treeSet.pollFirst();
        }
        return result;
    }
}
