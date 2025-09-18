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
}
