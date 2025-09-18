package dev.dyko.codingtest.example.datastructure;

import org.junit.Assert;

import java.util.*;

public class QueuePractice {
    public static void main(String[] args) {
        // 요세푸스 문제
        int N1 = 5;
        int K1 = 2;
        int output1 = 3;
        int result1 = josephusSolution(N1, K1);
        Assert.assertEquals(output1, result1);

        int N2 = 10;
        int K2 = 4;
        int output2 = 5;
        int result2 = josephusSolution(N2, K2);
        Assert.assertEquals(output2, result2);

        // 기능개발 문제
        int[] processes1 = {95, 90, 99, 99, 80, 99};
        int[] speeds1 = {1, 1, 1, 1, 1, 1};
        int[] output3 = {1, 3, 2};
        int[] result3 = devFunc(processes1, speeds1);
        Assert.assertArrayEquals(output3, result3);

        int[] processes2 = {70, 50, 90, 98};
        int[] speeds2 = {10, 20, 5, 1};
        int[] output4 = {4};
        int[] result4 = devFunc(processes2, speeds2);
        Assert.assertArrayEquals(output4, result4);
    }

    /**
     * 문제: 요세푸스 문제
     *      N명의 사람이 원 형태로 서 있습니다. 각 사람은 1부터 N까지 번호표를 갖고 있습니다.
     *      임의의 숫자 K가 주어졌을 때 다음과 같이 사람을 없앱니다.
     *        - 1번 번호표를 가진 사람을 기준으로 K번째 사람을 없앱니다.
     *        - 없앤 사람 다음 사람을 기준으로 하고 다시 K번째 사람을 없앱니다.
     *      N과 K가 주어질 때 마지막에 살아있는 사람의 번호를 반환하는 solution() 함수를 구현하시오.
     * 제약조건:
     *   - N과 K는 1,000이하의 자연수
     * 입출력 예시:
     *   - 입: N=5, K=2 / 출: 3
     */
    public static int josephusSolution(int n, int k){
        // 시간복잡도 : O(N) + O(NK) = O(NK) --> 연산 100만번, 가용범위 내
        Queue<Integer> queue = new ArrayDeque<>();

        // 번호포 배부 : O(N)
        for(int i = 1; i < n+1; i++){
            queue.offer(i);
        }

        // 번호표 제거 : O(NK)
        while(queue.size() > 1) {
            for(int i = 0; i < k; i++){
                int item = queue.poll();
                if(i == k - 1){
                    System.out.println("제거: " + item);
                } else {
                    queue.offer(item);
                }
            }
        }

        return queue.poll();
    }

    /**
     * 문제: 기능개발
     *      프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.
     *      각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.
     *      먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때
     *      각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.
     * 제약조건:
     *   - 작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
     *   - 작업 진도는 100 미만의 자연수입니다.
     *   - 작업 속도는 100 이하의 자연수입니다.
     *   - 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다.
     *     예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
     * 입출력 예시:
     *   - 입: processes=[93, 30, 55], speeds=[1, 30, 5] / 출: [2, 1]
     *   - 입: processes=[95, 90, 99, 99, 80, 99], speeds=[1, 1, 1, 1, 1, 1] / 출: [1, 3, 2]
     */
    public static int[] devFunc(int[] progresses, int[] speeds){
        // 시간복잡도: O(N) + O(N) + O(N) = O(N) --> 최대 300번 연산, HashMap 접근할 때 최악의 경우인 O(NloN) 일지라도 가용범위 통과
        // 진행완료에 필요한 시간 계산 : O(N)
        int[] periods = new int[progresses.length];
        for(int i = 0; i < progresses.length; i++){
            periods[i] = (int)(Math.ceil((100.0 - progresses[i]) / speeds[i]));
        }

        // 회별 배포 기능 개수 체크 : O(N)
        Queue<Integer> queue = new ArrayDeque<>();
        int funcCnt = 0;
        int prefunPeriod = periods[0];
        for(int i = 0; i < periods.length; i++){
            if(periods[i] <= prefunPeriod){
                funcCnt++;
            } else {
                queue.add(funcCnt);
                prefunPeriod = periods[i];
                funcCnt = 1;
            }
        }
        // 마지막 카운트 추가
        queue.add(funcCnt);

        return queue.stream().mapToInt(i -> i).toArray();   // O(N)
    }
}
