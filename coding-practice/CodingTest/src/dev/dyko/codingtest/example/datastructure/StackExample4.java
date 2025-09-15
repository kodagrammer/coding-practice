package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.Stack;

/**
 * 문제: 크레인 인형 뽑기 게임
 *      https://programmers.co.kr/learn/courses/30/lessons/64061
 * 제약사항:
 *   - board 배열은 2차원 배열로 크기는 "5 x 5" 이상 "30 x 30" 이하입니다.
 *   - moves 배열의 크기는 1 이상 1,000 이하입니다.
 */
public class StackExample4 {
    public static int solution(int[][] board, int[] moves) {
        // 시간복잡도: O(MN), 1,000 * 30 = 30,000, 가용범위 내 --> But, 더 좋은 코드 있음.
        // N: 보드게임 격자 수 (N*N배열)
        // M: 인형뽑기를 한 횟수

        Stack<Integer> stack = new Stack<>();
        int answer = 0;

        // 인형뽑기 진행: O(M) * O(N) = O(MN)
        for(int move : moves) {
            for(int i = 0; i < board.length; i++) {
                int item = board[i][move - 1];
                if(item != 0) {
                    // 전에 뽑은 인형과 같은 인형이면 폭파, 아니면 쌓기
                    if(!stack.isEmpty() && stack.peek() == item) {
                        stack.pop();
                        answer += 2;
                    } else {
                        stack.push(item);
                    }

                    // 인형을 뽑아서, 빈 공간으로 변경
                    board[i][move - 1] = 0;
                    break;
                }
            }
        }

        return answer;
    }

    // N의 크기가 작기 때문에, 더 효율적인 알고리즘 (정답지)
    public static int solution2(int[][] board, int[] moves) {
        // 시간복잡도: O(N^2 + M), 900 + 1,000 = 1,900
        // N: 보드게임 격자 수 (N*N배열)
        // M: 인형뽑기를 한 횟수

        // 보드판 레일별로 아이템 셋팅 : O(N^2)
        Stack<Integer>[] lanes = new Stack[board.length];
        for(int i = board.length - 1; i >= 0; i--) {
            for(int j = 0; j < board[i].length; j++) {
                if(lanes[j] == null) { lanes[j] = new Stack<>(); }
                if(board[i][j] > 0) {
                    lanes[j].push(board[i][j]);
                }
            }
        }

        // 인형뽑기 : O(M) * O(1)
        Stack<Integer> basket = new Stack<>();
        int answer = 0;
        for(int move : moves) {
            int lane = move - 1;
            if(!lanes[lane].isEmpty()) {
                int item = lanes[lane].pop();
                if(!basket.isEmpty() && basket.peek() == item) {
                    basket.pop();
                    answer += 2;
                } else {
                    basket.push(item);
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] board1 = {{0, 0, 0, 0, 0}
                        , {0, 0, 1, 0, 3}
                        , {0, 2, 5, 0, 1}
                        , {4, 2, 4, 4, 2}
                        , {3, 5, 1, 3, 1}};

        int[] moves1 = {1, 5, 3, 5, 1, 2, 1, 4};
        int output1 = 4;
        int result1 = solution(board1, moves1);
        Assertions.assertEquals(output1, result1);

        int[][] board2 = {{0, 0, 0, 0, 0}
                        , {0, 0, 1, 0, 3}
                        , {0, 2, 5, 0, 1}
                        , {4, 2, 4, 4, 2}
                        , {3, 5, 1, 3, 1}};
        int result2 = solution2(board2, moves1);
        Assertions.assertEquals(output1, result2);
    }
}
