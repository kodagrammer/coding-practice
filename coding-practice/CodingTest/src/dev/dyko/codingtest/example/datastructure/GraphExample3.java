package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 문제: 미로탈출 (https://school.programmers.co.kr/learn/courses/30/lessons/159993)
 *      1 x 1 크기의 칸들로 이루어진 직사각형 격자 형태의 미로에서 탈출하려고 합니다.
 *      각 칸은 통로 또는 벽으로 구성되어 있으며, 벽으로 된 칸은 지나갈 수 없고 통로로 된 칸으로만 이동할 수 있습니다.
 *      통로들 중 한 칸에는 미로를 빠져나가는 문이 있는데, 이 문은 레버를 당겨서만 열 수 있습니다. 레버 또한 통로들 중 한 칸에 있습니다.
 *      따라서, 출발 지점에서 먼저 레버가 있는 칸으로 이동하여 레버를 당긴 후 미로를 빠져나가는 문이 있는 칸으로 이동하면 됩니다.
 *      이때 아직 레버를 당기지 않았더라도 출구가 있는 칸을 지나갈 수 있습니다.
 *      미로에서 한 칸을 이동하는데 1초가 걸린다고 할 때, 최대한 빠르게 미로를 빠져나가는데 걸리는 시간을 구하려 합니다.
 *
 *      미로를 나타낸 문자열 배열 maps가 매개변수로 주어질 때, 미로를 탈출하는데 필요한 최소 시간을 return 하는 solution 함수를 완성해주세요.
 *      만약, 탈출할 수 없다면 -1을 return 해주세요.
 * 제약사항:
 *   - 5 ≤ maps의 길이 ≤ 100
 *       - 5 ≤ maps[i]의 길이 ≤ 100
 *       - maps[i]는 다음 5개의 문자들로만 이루어져 있습니다.
 *          - S : 시작 지점
 *          - E : 출구
 *          - L : 레버
 *          - O : 통로
 *          - X : 벽
 *       - 시작 지점과 출구, 레버는 항상 다른 곳에 존재하며 한 개씩만 존재합니다.
 *       - 출구는 레버가 당겨지지 않아도 지나갈 수 있으며, 모든 통로, 출구, 레버, 시작점은 여러 번 지나갈 수 있습니다.
 */
public class GraphExample3 {
    // 위, 아래, 왼쪽, 오른쪽 이동방향
    private final int[] dx = {0, 0, -1, 1};
    private final int[] dy = {1, -1, 0, 0};

    // 위치 정보 클래스
    private class Point {
        int nx, ny;
        public Point(int nx, int ny) {
            this.nx = nx;
            this.ny = ny;
        }
    }

    public int solution(String[] maps){
        // 최단 거리 --> 너비우선 탐색
        int n = maps.length;
        int m = maps[0].length();

        // 맵 정보 저장 : O(NM)
        Point start = null, lever = null, end = null;

        char[][] mapArr = new char[n][m];
        for(int y = 0; y < n; y++){
            char[] chars = maps[y].toCharArray();
            for(int x = 0; x < chars.length; x++){
                mapArr[y][x] = chars[x];

                switch(chars[x]){
                    case 'S':
                        start = new Point(x, y);
                        break;
                    case 'L':
                        lever = new Point(x, y);
                        break;
                    case 'E':
                        end = new Point(x, y);
                        break;
                }
            }
        }

        // 시작지점 -> 레버까지의 최단거리 : O(NM)
        int startLever = bfs(mapArr, start, lever);
        if(startLever == -1) { return -1; } // 레버까지 못가면 -1 반환

        // 레버 -> 종료지점까지의 최단거리 : O(NM)
        int leverEnd = bfs(mapArr, lever, end);
        if(leverEnd == -1) { return -1; }   // 종료지점까지 못가면 -1 반환

        return startLever + leverEnd;
    }

    private int bfs(char[][] maps, Point start, Point end){
        // 너비우선 기초 셋팅
        int n = maps.length;
        int m = maps[0].length;

        int[][] dist = new int[n][m];
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        dist[start.ny][start.nx] = 1;

        // 시간복잡도 최악의 경우, O(NM) --> 모든 칸 확인
        while(!queue.isEmpty()){
            Point cur = queue.poll();

            // 사방위 체크
            for(int i = 0; i < 4; i++){
                int nextX = cur.nx + dx[i];
                int nextY = cur.ny + dy[i];

                // 맵을 벗어나면 예외처리
                if(nextX < 0 || nextY < 0 || nextX >= m || nextY >= n){
                    continue;
                }

                // 이미 방문했거나 해당 지점이 벽이면 예외처리
                if(dist[nextY][nextX] > 0 || maps[nextY][nextX] == 'X'){
                    continue;
                }

                // 거리 증가
                dist[nextY][nextX] = dist[cur.ny][cur.nx] + 1;
                queue.add(new Point(nextX, nextY));

                // 종료 지점이면 최단거리 반환
                if(nextX == end.nx && nextY == end.ny){
                    return dist[nextY][nextX] - 1;  // 시작점 거리가 1부터 시작하므로, 반환시에는 -1 처리
                }
            }
        }

        // 전부 탐색했음에도 end 지점에 도달하지 못하면 -1 반환
        return -1;
    }

    public static void main(String[] args) {
        GraphExample3 graphExample = new GraphExample3();

        String[] maps1 = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
        int output1 = 16;
        int result1 = graphExample.solution(maps1);
        Assertions.assertEquals(output1, result1);

        String[] maps2 = {"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"};
        int output2 = -1;
        int result2 = graphExample.solution(maps2);
        Assertions.assertEquals(output2, result2);

        String[] maps3 = {"XOSOOXX", "OOXXLXX", "OXXXXXX", "OOOOOOX", "XXXXXOO", "EOOOOOO"};
        int output3 = 23;
        int result3 = graphExample.solution(maps3);
        Assertions.assertEquals(output3, result3);
    }
}
