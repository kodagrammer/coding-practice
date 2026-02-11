package dev.dyko.codingtest.programmers.level2;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 전화번호 목록(https://school.programmers.co.kr/learn/courses/30/lessons/42577)
 *      전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
 *      전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.
 *        - 구조대 : 119
 *        - 박준영 : 97 674 223
 *        - 지영석 : 11 9552 4421
 *      전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때,
 *      어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.
 * 제약사항:
 *   - phone_book의 길이는 1 이상 1,000,000 이하입니다.
 *     - 각 전화번호의 길이는 1 이상 20 이하입니다.
 *     - 같은 전화번호가 중복해서 들어있지 않습니다.
 */
public class PhoneBook {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        String[] phoneBook1 = {"119", "97674223", "1195524421"};
        boolean output1 = false;
        boolean result1 = phoneBook.solution2(phoneBook1);
        Assertions.assertEquals(output1, result1);

        String[] phoneBook2 = {"123","456","789"};
        boolean output2 = true;
        boolean result2 = phoneBook.solution2(phoneBook2);
        Assertions.assertEquals(output2, result2);

        String[] phoneBook3 = {"12","123","1235","567","88"};
        boolean output3 = false;
        boolean result3 = phoneBook.solution2(phoneBook3);
        Assertions.assertEquals(output3, result3);
    }

    // N: phoneBook.length, M: phoneBook[i].length()
    // 시간복잡도 : O(N logN M)
    public boolean solution(String[] phoneBook) {
        // 정렬 시 접두사 관계의 문자열이 인접하게 배치 : O(NlogN)
        Arrays.sort(phoneBook);

        // 인접하게 배치된 단어끼리 접두사 확인 : O(NM)
        for(int i = 0; i < phoneBook.length - 1; i++) {
            if(phoneBook[i + 1].startsWith(phoneBook[i])) {
                return false;
            }
        }
        return true;
    }

    // Hash를 사용한 풀이법 : O(N M^2) --> 정렬 후 탐색이 더 효율적
    public boolean solution2(String[] phoneBook) {
        // 조회 효율을 위해 HashSet으로 변환 : O(N)
        Set<String> set = new HashSet<>(Arrays.asList(phoneBook));

        // O(N M^2)
        for(String phone : phoneBook) {
            for(int i = 1; i < phone.length(); i++) {   // O(M)
                String prefix = phone.substring(0, i);  // O(M)
                if(set.contains(prefix)) {
                    return false;
                }
            }
        }

        return true;
    }
}
