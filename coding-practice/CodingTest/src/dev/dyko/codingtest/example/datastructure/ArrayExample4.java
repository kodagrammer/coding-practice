package dev.dyko.codingtest.example.datastructure;

import org.junit.Assert;

import java.util.*;

/**
 * 문제: 실패율
 *      슈퍼 게임 개발자 오렐리는 큰 고민에 빠졌습니다. 그녀가 만든 프렌즈 오천성이 대성공을 거뒀지만 최근 신규 사용자 수가 급감했기 때문입니다.
 *      원인은 신규 사용자와 기존 사용자 사이에 스테이지 차이가 너무 큰 것이 문제였습니다. 이 문제를 어떻게 할까 고민한 그녀는 동적으로 게임 시간을 늘려서 난이도를 조절하기로 했습니다.
 *      역시 슈퍼 개발자라 대부분의 로직은 쉽게 구현했지만 실패율을 구하는 부분에서 위기에 빠지고 말았습니다. 오렐리를 위해 실패율을 구하는 코드를 완성하세요.
 *          - 실패율 정의 : 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어의 수
 *      전체 스테이지 개수가 N, 게임을 이용하는 사용자가 현재 멈춰 있는 스테이지의 번호가 담긴 배열 stages가 주어질 때 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨 있는 배열을 반환하도록 solution( ) 함수를 완성하세요.
 * 제약사항:
 *   - 스테이지 개수 N은 1 이상 500 이하의 자연수입니다.
 *   - stages의 길이는 1 이상 200,000 이하입니다.
 *   - stages에는 1 이상 N + 1 이하의 자연수가 있습니다.
 *      - 각 자연수는 사용자가 현재 도전 중인 스테이지 번호를 나타냅니다.
 *      - 단, N + 1은 마지막 스테이지, 즉, N까지 클리어한 사용자를 나타냅니다.
 *   - 만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오면 됩니다.
 *   - 스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0으로 정의합니다.
 */
public class ArrayExample4 {
    public static int[] soultion(int n, int[] stages){
        // 시간복잡도: M + N + NlogN + N == O(M+NlogN)
        // N - 스테이지 개수 ( 0 < N < 501)
        // M - 유저 수 ( 0 < M < 200,001)

        // 각 스테이지별 도전중인 플레이어 수 도출 : O(M)
        int[] challengers = new int[n + 2];
        for (int stage : stages) {
            challengers[stage]++;
        }
        
        // 스테이지 별 실패율 계산 : O(N)
        HashMap<Integer, Double> failRates = new HashMap<>();
        double remaining = stages.length;

        for(int stage = 1; stage <= n; stage++){
            failRates.put(stage, challengers[stage] / remaining);
            remaining -= challengers[stage];
        }

        // 실패율 기준으로 내림차순 정렬 : O(N logN)
        List<Map.Entry<Integer, Double>> list = new ArrayList<>(failRates.entrySet());
        list.sort(Comparator.comparing(Map.Entry<Integer, Double>::getValue).reversed().thenComparing(Map.Entry::getKey));

        return list.stream().mapToInt(Map.Entry::getKey).toArray();
    }

    public static void main(String[] args) {
        int n1 = 5;
        int[] stages1 = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] output1 = {3, 4, 2, 1, 5};
        int[] result1 = soultion(n1, stages1);
        Assert.assertArrayEquals(output1, result1);

        int n2 = 4;
        int[] stages2 = {4, 4, 4, 4, 4};
        int[] output2 = {4, 1, 2, 3};
        int[] result2 = soultion(n2, stages2);
        Assert.assertArrayEquals(output2, result2);

        int n3 = 3;
        int[] stages3 = {1, 2, 1, 2, 1, 3, 4};
        int[] output3 = {2, 3, 1};
        int[] result3 = soultion(n3, stages3);
        Assert.assertArrayEquals(output3, result3);
    }
}
