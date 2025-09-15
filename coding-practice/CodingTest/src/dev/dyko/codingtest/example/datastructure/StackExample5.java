package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 표 편집 (https://school.programmers.co.kr/learn/courses/30/lessons/81303)
 *      업무용 소프트웨어를 개발하는 니니즈웍스의 인턴인 앙몬드는 명령어 기반으로 표의 행을 선택, 삭제, 복구하는 프로그램을 작성하는 과제를 맡았습니다.
 *      파란색으로 칠해진 칸은 현재 선택된 행을 나타냅니다. 단, 한 번에 한 행만 선택할 수 있으며, 표의 범위(0행 ~ 마지막 행)를 벗어날 수 없습니다.
 *      이때, 다음과 같은 명령어를 이용하여 표를 편집합니다.
 *        - "U X": 현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.
 *        - "D X": 현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
 *        - "C" : 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
 *        - "Z" : 가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.
 *      매개변수는 다음과 같습니다.
 *        - 처음 표의 행 개수를 나타내는 정수 n
 *        - 처음에 선택된 행의 위치를 나타내는 정수 k
 *        - 수행한 명령어들이 담긴 문자열 배열 cmd
 *      모든 명령어를 수행한 후 표의 상태와 처음 주어진 표의 상태를 비교하여 삭제되지 않은 행은 O, 삭제된 행은 X로 표시하여 문자열 형태로 return 하도록 solution 함수를 완성해주세요.
 * 제약사항:
 *   - 5 ≤ n ≤ 1,000,000
 *   - 0 ≤ k < n
 *   - 1 ≤ cmd의 원소 개수 ≤ 200,000
 *     cmd의 각 원소는 "U X", "D X", "C", "Z" 중 하나입니다.
 *   - 표의 범위를 벗어나는 이동은 입력으로 주어지지 않습니다.
 *   - cmd에 등장하는 모든 X들의 값을 합친 결과가 1,000,000 이하인 경우만 입력으로 주어집니다.
 *   - 원래대로 복구할 행이 없을 때(즉, 삭제된 행이 없을 때) "Z"가 명령어로 주어지는 경우는 없습니다.
 *   - 정답은 표의 0행부터 n - 1행까지에 해당되는 O, X를 순서대로 이어붙인 문자열 형태로 return 해주세요.
 */
public class StackExample5 {
    public String solution(int n, int k, String[] cmd) {
        // 시간복잡도: O(MNlogN) -- 효율성 탈락
        // N: 표의 행 개수
        // M: cmd의 원소 개수
        // 표의 행 순서를 관리할 리스트와 삭제한 행을 관리할 스택 선언
        LinkedList<Integer> matrix = new LinkedList<>();
        Stack<Integer> deletedRows = new Stack<>();

        // 행 인입
        for(int i = 0; i < n; i++) {
            matrix.add(i);
        }

        // 커맨드 실행 : O(MNlogN)
        for (String s : cmd) {
            String[] cmdArr = s.split(" ");
            if ("Z".equals(cmdArr[0])) {
                k = rollback(k, matrix, deletedRows);   // O(NlogN)
            } else if ("C".equals(cmdArr[0])) {
                k = deleteRow(k, matrix, deletedRows);  // O(N)
            } else { // 단순 이동
                int dis = Integer.parseInt(cmdArr[1]);
                k = "U".equals(cmdArr[0]) ? k - dis : k + dis;  // O(1)
            }
        }

        // 행 삭제여부 체크 : O(N)
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            if(matrix.contains(i)) {
                sb.append("O");
            } else {
                sb.append("X");
            }
        }

        return sb.toString();
    }

    // O(1) + O(N) = O(N)
    private int deleteRow(int k, LinkedList<Integer> matrix, Stack<Integer> deletedRows) {
        deletedRows.push(matrix.get(k));
        matrix.remove(k);
        return k == matrix.size() ? matrix.size() - 1 : k;
    }

    // O(1) + O(NlogN) + O(N) = O(NlogN)
    private int rollback(int k, LinkedList<Integer> matrix, Stack<Integer> deletedRows) {
        int rollbackIdx = deletedRows.pop();

        // 삭제된 행이 들어갈 위치(인덱스) 찾기
        int inputIdx = -(Collections.binarySearch(matrix, rollbackIdx) + 1);
        matrix.add(inputIdx, rollbackIdx);

        // 커서 보다 앞 행에 입력되면 커서 한칸 뒤로 밀림
        return k >= inputIdx ? k + 1 : k;
    }

    // 효율성 보완
    public String solution2(int n, int k, String[] cmd) {
        // 시간복잡도: O(N) + O(M) + O(N) = 1,000,000 + 200,000 + 1,000,000 = 2,200,000
        // N: 표의 행 개수
        // M: cmd의 원소 개수

        // 삭제 행 관리
        Stack<Integer> deleted = new Stack<>();

        // 각 행의 위, 아래 위치한 인덱스 연결
        int[] up = new int[n];
        int[] down = new int[n];
        boolean[] exist = new boolean[n];

        // 초기화 : O(N)
        for (int i = 0; i < n; i++) {
            up[i] = i - 1;
            down[i] = i + 1;
            exist[i] = true;
        }

        // O(M) * O(1) --> 이동에서 최악의 경우 O(N) 이나, cmd에 등장하는 모든 X들의 총합이 1,000,000 이하이므로 최악 O(NM)은 발생하지 않음
        for(String s : cmd) {
            if(s.equals("C")) {
                deleted.push(k);
                exist[k] = false;

                // 행 연결 인덱스 정보 조정
                if(up[k] != -1) { down[up[k]] = down[k]; }
                if(down[k] != n) { up[down[k]] = up[k]; }

                // 마지막 행이 아니면 아랫 행, 마지막 행이면 윗 행 커서 지정
                k = down[k] != n ? down[k] : up[k];
            } else if (s.equals("Z")) {
                int restored = deleted.pop();
                exist[restored] = true;

                // 행 연결 인덱스 정보 조정
                if(up[restored] != -1) { up[restored] = down[up[restored]]; }
                if(down[restored] != n) { down[restored] = up[down[restored]]; }

            } else { // "D", "U"
                int dis = Integer.parseInt(s.split(" ")[1]);
                while(dis-- > 0) {
                    k = s.startsWith("U") ? up[k] : down[k];
                }
            }
        }

        // 각 행 별 존재 확인 : O(N)
        StringBuilder sb = new StringBuilder();
        for(boolean row : exist) {
            sb.append(row ? "O" : "X");
        }

        return sb.toString();
    }

    public static void main(String[] args){
        StackExample5 stackExample5 = new StackExample5();

        int n1 = 8;
        int k1 = 2;
        String[] cmd1 = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        String output1 = "OOOOXOOO";
        String result1 = stackExample5.solution(n1, k1, cmd1);
        String result1_1 = stackExample5.solution2(n1, k1, cmd1);
        Assertions.assertEquals(output1, result1);
        Assertions.assertEquals(output1, result1_1);

        int n2 = 8;
        int k2 = 2;
        String[] cmd2 = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        String output2 = "OOXOXOOO";
        String result2 = stackExample5.solution(n2, k2, cmd2);
        String result2_1 = stackExample5.solution2(n2, k2, cmd2);
        Assertions.assertEquals(output2, result2);
        Assertions.assertEquals(output2, result2_1);

        int n3 = 6;
        int k3 = 1;
        String[] cmd3 = {"C", "U 1", "C", "D 3", "C", "Z", "Z", "U 2"};
        String output3 = "OXOOOO";
        String result3 = stackExample5.solution(n3, k3, cmd3);
        String result3_1 = stackExample5.solution2(n3, k3, cmd3);
        Assertions.assertEquals(output3, result3);
        Assertions.assertEquals(output3, result3_1);
    }
}
