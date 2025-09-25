package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 양과 늑대 (https://school.programmers.co.kr/learn/courses/30/lessons/92343)
 *      2진 트리 모양 초원의 각 노드에 늑대와 양이 한 마리씩 놓여 있습니다. 이 초원의 루트 노드에서 출발하여 각 노드를 돌아다니며 양을 모으려 합니다.
 *      각 노드를 방문할 때 마다 해당 노드에 있던 양과 늑대가 당신을 따라오게 됩니다.
 *      이때, 늑대는 양을 잡아먹을 기회를 노리고 있으며, 당신이 모은 양의 수보다 늑대의 수가 같거나 더 많아지면 바로 모든 양을 잡아먹어 버립니다.
 *      당신은 중간에 양이 늑대에게 잡아먹히지 않도록 하면서 최대한 많은 수의 양을 모아서 다시 루트 노드로 돌아오려 합니다.
 *
 *      예를 들어, 위 그림의 경우(루트 노드에는 항상 양이 있습니다) 0번 노드(루트 노드)에서 출발하면 양을 한마리 모을 수 있습니다.
 *      다음으로 1번 노드로 이동하면 당신이 모은 양은 두 마리가 됩니다. 이때, 바로 4번 노드로 이동하면 늑대 한 마리가 당신을 따라오게 됩니다.
 *      아직은 양 2마리, 늑대 1마리로 양이 잡아먹히지 않지만, 이후에 갈 수 있는 아직 방문하지 않은 모든 노드(2, 3, 6, 8번)에는 늑대가 있습니다.
 *      이어서 늑대가 있는 노드로 이동한다면(예를 들어 바로 6번 노드로 이동한다면) 양 2마리, 늑대 2마리가 되어 양이 모두 잡아먹힙니다.
 *      여기서는 0번, 1번 노드를 방문하여 양을 2마리 모은 후, 8번 노드로 이동한 후(양 2마리 늑대 1마리) 이어서 7번, 9번 노드를 방문하면 양 4마리 늑대 1마리가 됩니다.
 *      이제 4번, 6번 노드로 이동하면 양 4마리, 늑대 3마리가 되며, 이제 5번 노드로 이동할 수 있게 됩니다. 따라서 양을 최대 5마리 모을 수 있습니다.
 *
 *      각 노드에 있는 양 또는 늑대에 대한 정보가 담긴 배열 info, 2진 트리의 각 노드들의 연결 관계를 담은 2차원 배열 edges가 매개변수로 주어질 때,
 *      문제에 제시된 조건에 따라 각 노드를 방문하면서 모을 수 있는 양은 최대 몇 마리인지 return 하도록 solution 함수를 완성해주세요.
 * 제약조건:
 *   - 2 ≤ info의 길이 ≤ 17
 *      - info의 원소는 0 또는 1 입니다.
 *      - info[i]는 i번 노드에 있는 양 또는 늑대를 나타냅니다.
 *      - 0은 양, 1은 늑대를 의미합니다.
 *      - info[0]의 값은 항상 0입니다. 즉, 0번 노드(루트 노드)에는 항상 양이 있습니다.
 *   - edges의 세로(행) 길이 = info의 길이 - 1
 *      - edges의 가로(열) 길이 = 2
 *      - edges의 각 행은 [부모 노드 번호, 자식 노드 번호] 형태로, 서로 연결된 두 노드를 나타냅니다.
 *      - 동일한 간선에 대한 정보가 중복해서 주어지지 않습니다.
 *      - 항상 하나의 이진 트리 형태로 입력이 주어지며, 잘못된 데이터가 주어지는 경우는 없습니다.
 *      - 0번 노드는 항상 루트 노드입니다.
 */
public class TreeExample3 {
    // 각 노드 별 자식 노드를 리스트 배열을 활용하여 구성
    private ArrayList<Integer>[] tree;

    private void buildTree(int[] info, int[][] edges) {
        tree = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }
    }

    private class Info{
        int node, sheepCnt, wolfCnt;
        HashSet<Integer> visitedNodes;

        public Info(int node, int sheepCnt, int wolfCnt, HashSet<Integer> visitedNodes) {
            this.node = node;
            this.sheepCnt = sheepCnt;
            this.wolfCnt = wolfCnt;
            this.visitedNodes = visitedNodes;
        }
    }

    public int solution(int[] info, int[][] edges){
        // 너비우선 탐색 : 트리의 레벨을 모두 방문하여 양이 잡아먹히지 않는 최적해 찾기
        buildTree(info, edges);

        // 탐색을 위한 큐 생성 및 초기 상태 설정
        ArrayDeque<Info> queue = new ArrayDeque<>();
        queue.add(new Info(0, 1, 0, new HashSet<>()));

        int answer = 0;

        while(!queue.isEmpty()){
            Info now = queue.poll();
            answer = Math.max(answer, now.sheepCnt);     // 최대 양의 수 업데이트
            now.visitedNodes.addAll(tree[now.node]);    // 방문한 노드 집합에 현재 노드에서 탐색해야 할 인접한 이웃 노드 추가

            // 방문한 노드들을 기준으로 다음에 방문할 수 있는 노드 순회
            for(int next : now.visitedNodes) {
                // 다음 상태에서의 탐색을 위해, 현재 방문한 정점을 셋에서 제거
                HashSet<Integer> set = new HashSet<>(now.visitedNodes);
                set.remove(next);

                if(info[next] == 1) {   // 늑대일 때, 늑대의 수가 양보다 같거나 크지 않는 경우에만 탐색할 노드에 추가
                    if(now.sheepCnt > now.wolfCnt + 1) {
                        queue.add(new Info(next, now.sheepCnt, now.wolfCnt + 1, set));
                    }
                } else {
                    queue.add(new Info(next, now.sheepCnt + 1, now.wolfCnt, set));
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        TreeExample3 example = new TreeExample3();

        int[] info1 = {0,0,1,1,1,0,1,0,1,0,1,1};
        int[][] edges1 = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
        int output1 = 5;
        int result1 = example.solution(info1, edges1);
        Assertions.assertEquals(output1, result1);
    }
}
