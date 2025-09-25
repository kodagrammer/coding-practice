package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

public class TreePractice {
    /*
     * 이진 탐색 트리 시간복잡도는 트리 균형에 따라 다름
     *   - 균형잡힌 경우, O(logN)
     *   - 균형이 맞지 않는 경우, O(N)
     * 트리를 표현하는 방법
     *   - 배열
     *   - 포인터
     *   - 인접리스트
     */
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        String[] output1 = {"1 2 4 5 3 6 7", "4 2 5 1 6 3 7", "4 5 2 6 7 3 1"};
        String[] result1 = solution(arr1);
        Assertions.assertArrayEquals(output1, result1);
    }

    /**
     * 문제: 트리 순회
     *      이진트리를 표현한 배열 nodes를 인자로 받아, 해당 이진 트리에 대하여 전위 순회, 중위 순회, 후위 순회 결과를 반환하는 solution() 함수를 구현하시오.
     * 제약조건:
     *   - 입력 노드값의 개수는 1개 이상 1,000개 이하
     *   - 노드값은 정수형이며, 중복되지 않는다.
     * 입출력 예:
     *   - 입력: [1, 2, 4, 5, 6, 7] / 출력: ["1 2 4 5 3 6 7", "4 2 5 1 6 3 7", "4 5 2 6 7 3 1"]
     */
    public static String[] solution(int[] nodes){
        String[] answer = new String[3];
        answer[0] = preorder(nodes, 0).trim();
        answer[1] = inorder(nodes, 0).trim();
        answer[2] = postorder(nodes, 0).trim();

        return answer;
    }

    // 루트 노드 -> 왼쪽 서브트리 -> 오른쪽 서브트리 순으로 재귀호출
    public static String preorder(int[] nodes, int idx){
        if(idx >= nodes.length) return "";

        return nodes[idx] + " "
                + preorder(nodes, 2 * idx + 1)
                + preorder(nodes, 2 * idx + 2);
    }

    // 왼쪽 서브트리 -> 루트 노드 -> 오른쪽 서브트리 순으로 재귀호출
    public static String inorder(int[] nodes, int idx){
        if(idx >= nodes.length) return "";

        return inorder(nodes, 2 * idx + 1)
                + nodes[idx] + " "
                + inorder(nodes, 2 * idx + 2);
    }

    // 왼쪽 서브트리 -> 오른쪽 서브트리 -> 루트노드 순으로 재귀호출
    public static String postorder(int[] nodes, int idx){
        if(idx >= nodes.length) return "";

        return postorder(nodes, 2 * idx + 1)
                + postorder(nodes, 2 * idx + 2)
                + nodes[idx] + " ";
    }
}
