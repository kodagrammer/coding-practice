package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;

/**
 * 문제: 다단계 칫솔판매 (https://school.programmers.co.kr/learn/courses/30/lessons/77486)
 *      민호는 다단계 조직을 이용하여 칫솔을 판매하고 있습니다. 판매원이 칫솔을 판매하면 그 이익이 피라미드 조직을 타고 조금씩 분배되는 형태의 판매망입니다.
 *      어느정도 판매가 이루어진 후, 조직을 운영하던 민호는 조직 내 누가 얼마만큼의 이득을 가져갔는지가 궁금해졌습니다.
 *      ... 이하 문제 링크 참고
 * 제약사항
 *   - enroll의 길이는 1 이상 10,000 이하입니다.
 *      - enroll에 민호의 이름은 없습니다. 따라서 enroll의 길이는 민호를 제외한 조직 구성원의 총 수입니다.
 *   - referral의 길이는 enroll의 길이와 같습니다.
 *     - referral 내에서 i 번째에 있는 이름은 배열 enroll 내에서 i 번째에 있는 판매원을 조직에 참여시킨 사람의 이름입니다.
 *     - 어느 누구의 추천도 없이 조직에 참여한 사람에 대해서는 referral 배열 내에 추천인의 이름이 기입되지 않고 “-“ 가 기입됩니다. 위 예제에서는 john 과 mary 가 이러한 예에 해당합니다.
 *     - enroll 에 등장하는 이름은 조직에 참여한 순서에 따릅니다.
 *     - 즉, 어느 판매원의 이름이 enroll 의 i 번째에 등장한다면, 이 판매원을 조직에 참여시킨 사람의 이름, 즉 referral 의 i 번째 원소는 이미 배열 enroll 의 j 번째 (j < i) 에 등장했음이 보장됩니다.
 *   - seller의 길이는 1 이상 100,000 이하입니다.
 *      - seller 내의 i 번째에 있는 이름은 i 번째 판매 집계 데이터가 어느 판매원에 의한 것인지를 나타냅니다.
 *      - seller 에는 같은 이름이 중복해서 들어있을 수 있습니다.
 *   - amount의 길이는 seller의 길이와 같습니다.
 *      - amount 내의 i 번째에 있는 수는 i 번째 판매 집계 데이터의 판매량을 나타냅니다.
 *      - 판매량의 범위, 즉 amount 의 원소들의 범위는 1 이상 100 이하인 자연수입니다.
 *   - 칫솔 한 개를 판매하여 얻어지는 이익은 100 원으로 정해져 있습니다.
 *   - 모든 조직 구성원들의 이름은 10 글자 이내의 영문 알파벳 소문자들로만 이루어져 있습니다.
 */
public class TreeExample2 {

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount){
        HashMap<String, String> referralMap = new HashMap<>();
        for(int i = 0; i < enroll.length; i++){
            referralMap.put(enroll[i], referral[i]);
        }

        HashMap<String, Integer> total = new HashMap<>();
        for(int i = 0; i < seller.length; i++){
            String curName = seller[i];
            int money = amount[i] * 100;

            while(money > 0 && !"-".equals(curName)){
                total.put(curName, total.getOrDefault(curName, 0) + money - (money/10));
                curName = referralMap.get(curName);
                money /= 10;
            }
        }

        int[] result = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++){
            result[i] = total.getOrDefault(enroll[i], 0);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeExample2 example = new TreeExample2();

        String[] enroll1 = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral1 = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller1 = {"young", "john", "tod", "emily", "mary"};
        int[] amount1 = {12, 4, 2, 5, 10};
        int[] output1 = {360, 958, 108, 0, 450, 18, 180, 1080};
        int[] result1 = example.solution(enroll1, referral1, seller1, amount1);
        Assertions.assertArrayEquals(output1, result1);

        String[] enroll2 = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral2 = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller2 = {"sam", "emily", "jaimie", "edward"};
        int[] amount2 = {2, 3, 5, 4};
        int[] output2 = {0, 110, 378, 180, 270, 450, 0, 0};
        int[] result2 = example.solution(enroll2, referral2, seller2, amount2);
        Assertions.assertArrayEquals(output2, result2);
    }
}
