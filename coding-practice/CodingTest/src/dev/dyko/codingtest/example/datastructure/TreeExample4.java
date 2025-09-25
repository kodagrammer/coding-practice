package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 길 찾기 게임 (https://school.programmers.co.kr/learn/courses/30/lessons/42892)
 *      전무로 승진한 라이언은 기분이 너무 좋아 프렌즈를 이끌고 특별 휴가를 가기로 했다.
 *      내친김에 여행 계획까지 구상하던 라이언은 재미있는 게임을 생각해냈고 역시 전무로 승진할만한 인재라고 스스로에게 감탄했다.
 *      라이언이 구상한(그리고 아마도 라이언만 즐거울만한) 게임은, 카카오 프렌즈를 두 팀으로 나누고, 각 팀이 같은 곳을 다른 순서로 방문하도록 해서 먼저 순회를 마친 팀이 승리하는 것이다.
 *      그냥 지도를 주고 게임을 시작하면 재미가 덜해지므로, 라이언은 방문할 곳의 2차원 좌표 값을 구하고 각 장소를 이진트리의 노드가 되도록 구성한 후,
 *      순회 방법을 힌트로 주어 각 팀이 스스로 경로를 찾도록 할 계획이다.
 *
 *      라이언은 아래와 같은 특별한 규칙으로 트리 노드들을 구성한다.
 *        - 트리를 구성하는 모든 노드의 x, y 좌표 값은 정수이다.
 *        - 모든 노드는 서로 다른 x값을 가진다.
 *        - 같은 레벨(level)에 있는 노드는 같은 y 좌표를 가진다.
 *        - 자식 노드의 y 값은 항상 부모 노드보다 작다.
 *        - 임의의 노드 V의 왼쪽 서브 트리(left subtree)에 있는 모든 노드의 x값은 V의 x값보다 작다.
 *        - 임의의 노드 V의 오른쪽 서브 트리(right subtree)에 있는 모든 노드의 x값은 V의 x값보다 크다.
 *      아래 예시를 확인해보자.
 *                      7
 *                4             2
 *            6       1             3
 *              9       8
 *                    5
 *      위 이진트리에서 전위 순회(preorder), 후위 순회(postorder)를 한 결과는 다음과 같고, 이것은 각 팀이 방문해야 할 순서를 의미한다.
 *        - 전위 순회 : 7, 4, 6, 9, 1, 8, 5, 2, 3
 *        - 후위 순회 : 9, 6, 5, 8, 1, 4, 3, 2, 7
 *
 *      그러나 여전히 문제는 남아있다. 노드의 수가 예시처럼 적다면 쉽게 해결할 수 있겠지만, 예상대로 라이언은 그렇게 할 생각이 전혀 없었다.
 *      곤경에 빠진 카카오 프렌즈를 위해 이진트리를 구성하는 노드들의 좌표가 담긴 배열 nodeinfo가 매개변수로 주어질 때,
 *      노드들로 구성된 이진트리를 전위 순회, 후위 순회한 결과를 2차원 배열에 순서대로 담아 return 하도록 solution 함수를 완성하자.
 * 제약조건:
 *   - nodeinfo는 이진트리를 구성하는 각 노드의 좌표가 1번 노드부터 순서대로 들어있는 2차원 배열이다.
 *      - nodeinfo의 길이는 1 이상 10,000 이하이다.
 *      - nodeinfo[i] 는 i + 1번 노드의 좌표이며, [x축 좌표, y축 좌표] 순으로 들어있다.
 *      - 모든 노드의 좌표 값은 0 이상 100,000 이하인 정수이다.
 *      - 트리의 깊이가 1,000 이하인 경우만 입력으로 주어진다.
 *      - 모든 노드의 좌표는 문제에 주어진 규칙을 따르며, 잘못된 노드 위치가 주어지는 경우는 없다.
 */
public class TreeExample4 {

    public static void main(String[] args) {
        TreeExample4 example = new TreeExample4();

        int[][] nodeinfo1 = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
        int[][] output1 = {{7,4,6,9,1,8,5,2,3},{9,6,5,8,1,4,3,2,7}};
        int[][] result1 = example.solution(nodeinfo1);
        Assertions.assertArrayEquals(output1, result1);
    }

    public int[][] solution(int[][] nodeinfo) {
        // 시간복잡도: O(NlogN) + O(N) + O(N) --> O(NlogN)

        // O(2N + NlogN) --> O(NlogN)
        Node[] nodes = makeTree(nodeinfo);

        ArrayList<Integer> preorder = new ArrayList<>();
        ArrayList<Integer> postorder = new ArrayList<>();

        // O(2N) --> O(N)
        preorder(preorder, nodes[0]);
        postorder(postorder, nodes[0]);

        // O(2N) --> O(N)
        int[][] result = new int[2][nodes.length];
        result[0] = preorder.stream().mapToInt(Integer::intValue).toArray();
        result[1] = postorder.stream().mapToInt(Integer::intValue).toArray();

        return result;
    }

    // 전위 순회
    private void preorder(ArrayList<Integer> orders, Node node) {
        orders.add(node.num);
        if (node.left != null) {
            preorder(orders, node.left);
        }
        if (node.right != null) {
            preorder(orders, node.right);
        }
    }

    // 후위 순회
    private void postorder(ArrayList<Integer> orders, Node node) {
        if (node.left != null) {
            postorder(orders, node.left);
        }
        if (node.right != null) {
            postorder(orders, node.right);
        }
        orders.add(node.num);
    }

    private Node[] makeTree(int[][] nodeinfo) {
        // Node 정보 저장 : O(N)
        Node[] nodes = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        }

        // y 내림차순, x 오름차순 정렬 : O(NlogN)
        Arrays.sort(nodes, (o1, o2) ->{
            if(o1.y == o2.y) {
                return Integer.compare(o1.x, o2.x);
            }
            return Integer.compare(o2.y, o1.y);
        });

        Node root = nodes[0];

        // 노드 별 left, right 정보 입력 : O(N)
        for(int i = 1; i < nodes.length; i++) {
            Node parent = root;
            while(true){
                if(nodes[i].x < parent.x) {         // 부모의 x보다 자식의 x 값이 작음
                    if(parent.left == null) {       // 이미 왼쪽 자식 노드가 있다면, 자식 노드 밑으로 노드 배치
                        parent.left = nodes[i];
                        break;
                    } else {
                        parent = parent.left;
                    }
                } else {
                    if(parent.right == null) {
                        parent.right = nodes[i];
                        break;
                    } else {
                        parent = parent.right;
                    }
                }
            }
        }

        return nodes;
    }

    private class Node{
        int num, x, y;
        Node left, right;

        public Node(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
}
