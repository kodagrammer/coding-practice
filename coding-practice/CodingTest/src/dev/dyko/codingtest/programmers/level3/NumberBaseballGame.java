package dev.dyko.codingtest.programmers.level3;

import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.function.Function;

/**
 * 문제: 숫자야구 (https://school.programmers.co.kr/learn/courses/30/lessons/451808)
 *      숫자 야구란 1 ~ 9 사이의 서로 다른 숫자 4개로 이루어진 비밀번호를 맞히는 게임입니다.
 *      당신은 1000 이상 9999 이하의 정수를 제출할 수 있는 기회가 총 n번 있으며, 수를 제출할 때마다 비밀번호에 관한 단서가 주어집니다.
 *      이때 제출한 수의 각 자릿수에 대해 아래와 같이 판정합니다.
 *
 *      숫자가 비밀번호에 포함되어 있지 않다면 : OUT
 *      숫자가 비밀번호에 포함되어 있지만, 위치가 틀렸다면 : BALL
 *      숫자가 비밀번호에 포함되어 있고, 위치까지 정확하다면 : STRIKE
 *
 *      위와 같이 STRIKE, BALL으로 판정한 숫자의 개수를 세어, STRIKE가 x개 / BALL이 y개라면 "xS yB" 형식으로 단서가 주어집니다.
 *      숫자 야구의 최대 제출 횟수를 나타내는 정수 n과, 수를 제출하기 위한 submit 함수가 주어집니다.
 *      submit 함수를 호출해 비밀번호를 알아내, 숫자 야구의 비밀번호를 return 하도록 solution 함수를 완성해 주세요.
 * 제한사항:
 *  - 6 ≤ n ≤ 3,024
 *  - submit 함수는 1000 이상 9999 이하의 정수를 전달받아, 단서를 "xS yB" 형식의 문자열로 return 합니다.
 *      - 1000 ~ 9999 사이의 정수가 아닌 값을 전달하는 경우 오답으로 판정합니다.
 *      - submit 함수 사용 예시가 초기 코드로 주어집니다. 해당 코드는 1000 ~ 9999를 순서대로 제출해, 단서가 "4S 0B"인 경우 해당 정수를 return 하는 코드입니다.
 */
public class NumberBaseballGame {
    public static void main(String[] args) {
        // 예시 정답
        int answer = 1375;

        // submit 함수 정의
        Function<Integer, String> submit = guess -> {
            int strike = 0;
            int ball = 0;

            int[] answerDigits = toDigits(answer);
            int[] guessDigits = toDigits(guess);

            for (int i = 0; i < 4; i++) {
                if (guessDigits[i] == answerDigits[i]) {
                    strike++;
                } else if (contains(answerDigits, guessDigits[i])) {
                    ball++;
                }
            }

            return strike + "S " + ball + "B";
        };

        // 테스트
        Assertions.assertEquals("1S 1B", submit.apply(7865));  // 출력 예: Guess: 1243 -> 2S 2B

        // solution 함수 테스트
        int n1 = 15;
        int n2 = 6;

        NumberBaseballGame game = new NumberBaseballGame();
        // 1차 답안
//        int result1 = game.solution(n1, submit);
//        Assertions.assertEquals(answer, result1);
        // 2차 답안
        int result12 = game.solution2(n1, submit);
        Assertions.assertEquals(answer, result12);

        // 1차 답안
//        int result2 = game.solution(n2, submit);
//        Assertions.assertEquals(answer, result2);
        // 2차 답안
        int result22 = game.solution2(n2, submit);
        Assertions.assertEquals(answer, result22);
    }

    // 숫자를 배열로 변환
    private static int[] toDigits(int num) {
        return new int[] { num / 1000, (num / 100) % 10, (num / 10) % 10, num % 10 };
    }

    // 배열에 값이 포함되어 있는지 확인
    private static boolean contains(int[] arr, int val) {
        for (int a : arr) {
            if (a == val) return true;
        }
        return false;
    }

    /**************************** 1차 답안 로직 **********************************/

    // 1차 답안 --> 시간복잡도 최대 O(15), n < 15 에선 모두 Fail
    public int solution(int n, Function<Integer, String> submit) {
        int[] result = new int[4];
        boolean[] checked = new boolean[4];

        Queue<Integer> queue = new ArrayDeque<>();
        int cnt = 0;

        // 확인할 숫자 체크
        for (int i = 1; i <= 9; i++) {
            queue.offer(i);
        }

        // 비밀번호 확인
        while(!queue.isEmpty()) {
            if(cnt > n) { return 0; } // 순환이 주어진 제한횟수(n)를 넘어가면 강제 종료

            int number = queue.poll();
            int index = 0;

            // 위치 찾아낸 숫자가 있다면, 그 다음 순번에 넣기 위해 결과 확인
            for(int i = 0; i < checked.length; i++ ) {
                if(!checked[i]) break;
                index++;
            }

            // submit 함수에서 확인할 번호 생성
            int checkNumber = number * (int) Math.pow(10, result.length - index - 1);
            for(int i = 0; i < index; i ++){
                checkNumber += result[i] * (int) Math.pow(10, result.length - i - 1);
            }

            // submit 결과 확인
            String applied = submit.apply(checkNumber);
            System.out.println("현재 submit 실행 횟수 : " + ++cnt + " 확인한 숫자 : " + checkNumber);

            int strike = Integer.parseInt(applied.split("S")[0]);
            int ball = Integer.parseInt(applied.split("S")[1].replace("B","").trim());

            if(strike > index) {
                // 해당 숫자가 스트라이크인 경우
                result[index] = number;
                checked[index] = true;
            } else if(index == strike && ball == 1) {
                // 위치는 안맞았지만 포함된 경우
                queue.offer(number);
            }
        }

        return result[0] * 1000 + result[1] * 100 + result[2] * 10 + result[3];
    }

    /**************************** 2차 답안 로직 **********************************/

    // 2차 답안 --> Mastermind 최적 전략
    // 후보집단 P(9, 4) = 3024 --> 1~9, 4자리 상수로 고정되어 있어 O(1)
    public int solution2(int n, Function<Integer, String> submit) {
        // 가능한 모든 후보군 추출
        List<int[]> candidates = generateCandidates();

        int cnt = 0;

        // 후보가 잔존하면 계속 시도
        while (candidates.size() > 1) {
            if (cnt >= n) return 0;  // 제한 초과하면 실패 처리

            // 확인할 숫자
            int[] guess = candidates.get(0);
            int number = toInt(guess);

            // submit 결과 확인
            String applied = submit.apply(number);
            System.out.println("현재 submit 실행 횟수 : " + ++cnt + " 확인한 숫자 : " + number);

            int strike = Integer.parseInt(applied.split("S")[0].trim());
            int ball = Integer.parseInt(applied.split("S")[1].replace("B","").trim());

            // 후보군 제거
            List<int[]> filtered = new ArrayList<>();
            for(int[] candidate : candidates) {
                // 스트라이크와 볼이 일치하는 후보군만 남김
                if(matched(guess, candidate, strike, ball)) {
                    filtered.add(candidate);
                }
            }
            candidates = filtered;
        }

        if (candidates.isEmpty()) return 0;
        return toInt(candidates.get(0));
    }

    private int toInt(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i] * (int)Math.pow(10, arr.length - i - 1);
        }
        return sum;
    }

    private List<int[]> generateCandidates() {
        List<int[]> candidates = new ArrayList<>();

        for (int a = 1; a <= 9; a++) {
            for (int b = 1; b <= 9; b++) {
                if (b == a) continue;
                for (int c = 1; c <= 9; c++) {
                    if (c == a || c == b) continue;
                    for (int d = 1; d <= 9; d++) {
                        if (d == a || d == b || d == c) continue;

                        candidates.add(new int[]{a, b, c, d});
                    }
                }
            }
        }

        return candidates;
    }

    private boolean matched(int[] guess, int[] candidate, int strike, int ball) {
        int s = 0, b = 0;

        for(int i = 0; i < 4; i++) {
            if(guess[i] == candidate[i]) {
                s++;
            } else {
                for(int j : candidate) {
                    if(j == guess[i]) {
                        b++;
                        break;
                    }
                }
            }
        }

        return s == strike && b == ball;
    }
}
