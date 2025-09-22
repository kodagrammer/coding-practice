package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 오픈 채팅방 (https://school.programmers.co.kr/learn/courses/30/lessons/42888)
 * 제약사항:
 *   - record는 다음과 같은 문자열이 담긴 배열이며, 길이는 1 이상 100,000 이하이다.
 *   - 다음은 record에 담긴 문자열에 대한 설명이다.
 *      - 모든 유저는 [유저 아이디]로 구분한다.
 *      - [유저 아이디] 사용자가 [닉네임]으로 채팅방에 입장 - "Enter [유저 아이디] [닉네임]" (ex. "Enter uid1234 Muzi")
 *      - [유저 아이디] 사용자가 채팅방에서 퇴장 - "Leave [유저 아이디]" (ex. "Leave uid1234")
 *      - [유저 아이디] 사용자가 닉네임을 [닉네임]으로 변경 - "Change [유저 아이디] [닉네임]" (ex. "Change uid1234 Muzi")
 *      - 첫 단어는 Enter, Leave, Change 중 하나이다.
 *      - 각 단어는 공백으로 구분되어 있으며, 알파벳 대문자, 소문자, 숫자로만 이루어져있다.
 *      - 유저 아이디와 닉네임은 알파벳 대문자, 소문자를 구별한다.
 *      - 유저 아이디와 닉네임의 길이는 1 이상 10 이하이다.
 *      - 채팅방에서 나간 유저가 닉네임을 변경하는 등 잘못 된 입력은 주어지지 않는다.
 */
public class HashExample3 {
    private final Map<String, String> _cmdPrintMap = Map.of("Enter", "님이 들어왔습니다.", "Leave", "님이 나갔습니다.");

    public String[] solution(String[] record){
        // 시간복잡도 : O(2N) == 200,000

        // 유저-닉네임 최종 매칭 : O(N)
        Map<String, String> nicknameMap = new HashMap<>();
        int actionCnt = 0;
        for(String cmd : record){
            String[] cmdParts = cmd.split(" ");
            // Enter/Change일 때 닉네임 저장
            if(!"Leave".equals(cmdParts[0])){
                nicknameMap.put(cmdParts[1], cmdParts[2]);
            }
            actionCnt += "Change".equals(cmdParts[0]) ? 0 : 1;
        }

        // 명령어 결과 출력 : O(N)
        String[] result = new String[actionCnt];
        int i = 0;
        for(String cmd : record) {
            String[] cmdParts = cmd.split(" ");
            if(!"Change".equals(cmdParts[0])){
                result[i++] = nicknameMap.get(cmdParts[1]) + _cmdPrintMap.get(cmdParts[0]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        HashExample3 example = new HashExample3();

        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        String[] output = {"Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."};
        String[] result = example.solution(record);
        Assertions.assertArrayEquals(output, result);
    }
}
