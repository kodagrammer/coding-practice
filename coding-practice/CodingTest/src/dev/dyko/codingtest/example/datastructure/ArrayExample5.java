package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 방문길이
 *      게임 캐릭터를 4가지 명령어를 통해 움직이려 합니다. 명령어는 다음과 같습니다.
 *        - U : 위쪽으로 한 칸 가기
 *        - D : 아래쪽으로 한 칸 가기
 *        - R : 오른쪽으로 한 칸 가기
 *        - L : 왼쪽으로 한 칸 가기
 *      캐릭터는 좌표평면의 (0, 0) 위치에서 시작합니다. 좌표평면의 경계는 왼쪽 위(-5, 5), 왼쪽 아래(-5, -5), 오른쪽 위(5, 5), 오른쪽 아래(5, -5)로 구성합니다.
 *      이때 우리는 게임 캐릭터가 지나간 길 중 캐릭터가 처음 걸어본 길의 길이를 구하려고 합니다. 그리고 좌표평면의 경계를 넘어가는 명령어는 무시합니다.
 *      명령어가 매개변수 dirs로 주어질 때 게임 캐릭터가 처음 걸어본 길의 길이를 구해 반환하는 solution( ) 함수를 완성하세요.
 * 제약사항:
 *   - dirs는 string형으로 주어지며, ‘U’, ‘D’, ‘R’, ‘L’ 이외의 문자는 주어지지 않습니다.
 *   - dirs의 길이는 500 이하의 자연수입니다.
 */
public class ArrayExample5 {
    public static final Map<Character, int[]> location = Map.of(
              'U', new int[]{0, 1}
            , 'D', new int[]{0, -1}
            , 'R', new int[]{1, 0}
            , 'L', new int[]{-1, 0});

    public static boolean canMove(int nx, int ny) {
        return nx <=5 && nx >= -5 && ny <=5 && ny >= -5;
    }

    public static int solution(String dirs) {
        // 시간복잡도: O(N) + O(1) == O(N)
        // N - dirs의 길이
        int x = 0, y = 0;

        // 좌표 이동 방향 저장 : O(N)
        Set<String> moves = new HashSet<>(); // 중복 제거
        for(char dir : dirs.toCharArray()) {
            int nx = x + location.get(dir)[0];
            int ny = y + location.get(dir)[1];

            if(canMove(nx, ny)) {
                // 좌표간 이동 방향성을 등록할 수 없으므로 양방향으로 모두 등록
                moves.add("[" + x + ", " + y + "] [" + nx + ", " + ny + "]");
                moves.add("[" + nx + ", " + ny + "] [" + x + ", " + y + "]");
                x = nx;
                y = ny;
            }
        }

        // 양방향 등록했으니 나누기 2
        return moves.size() / 2;
    }

    public static void main(String[] args) {
        String dirs1 = "ULURRDLLU";
        int output1 = 7;
        int result1 = solution(dirs1);
        Assertions.assertEquals(output1, result1);

        String dirs2 = "LULLLLLLU";
        int output2 = 7;
        int result2 = solution(dirs2);
        Assertions.assertEquals(output2, result2);

        String dirs3 = "DDRRURLDRLUUL";
        int output3 = 9;
        int result3 = solution(dirs3);
        Assertions.assertEquals(output3, result3);
    }
}
