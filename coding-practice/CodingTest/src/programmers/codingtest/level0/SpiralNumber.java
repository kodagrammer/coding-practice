package programmers.codingtest.level0;

/*################################
 * 플랫폼: 프로그래머스
 * 레벨: Level 0
 * 문제유형: 기초 트레이닝
 * URL: https://school.programmers.co.kr/learn/courses/30/lessons/181832
 * 비고:
 * ################################
 */
public class SpiralNumber {
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        int num = 1;
        int x = 0, y = 0;
        int dx[] = {0, 1, 0, -1};
        int dy[] = {1, 0, -1, 0};
        int direction = 0;

        while (num <= n * n) {
            answer[x][y] = num++;

            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n || answer[nx][ny] != 0) {
                direction = (direction + 1) % 4; //범위 밖에 나갔을 때 방향전환
                nx = x + dx[direction];
                ny = y + dy[direction];
            }
            x = nx;
            y = ny;
        }

        return answer;
    }
}
