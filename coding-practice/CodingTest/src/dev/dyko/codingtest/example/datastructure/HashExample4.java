package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 문제: 베스트 앨범 (https://school.programmers.co.kr/learn/courses/30/lessons/42579)
 *      스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
 *        - 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
 *        - 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
 *        - 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
 *      노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때,
 *      베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
 * 제약사항:
 *   - genres[i]는 고유번호가 i인 노래의 장르입니다.
 *   - plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
 *   - genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
 *   - 장르 종류는 100개 미만입니다.
 *   - 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
 *   - 모든 장르는 재생된 횟수가 다릅니다.
 */
public class HashExample4 {
    public int[] solution(String[] genres, int[] plays) {
        // 장르별 재생횟수 계산 : O(N) == 10,000
        Map<String, Map<Integer, Integer>> albumsByGenre = new HashMap<>();
        Map<String, Integer> playsByGenres = new HashMap<>();
        for(int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            playsByGenres.put(genre, playsByGenres.getOrDefault(genre, 0) + plays[i]);

            Map<Integer, Integer> albumByGenre = albumsByGenre.getOrDefault(genre, new HashMap<>());
            albumByGenre.put(i, plays[i]);
            albumsByGenre.put(genre, albumByGenre);
        }

        // 앨범 수록곡 추출 : O(MlogM)
        // M - 장르개수
        List<Integer> result = new ArrayList<>();
        playsByGenres.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))  // 재생횟수 큰 장르부터 정렬
                .forEach(entry -> {
                    Map<Integer, Integer> albumByGenre = albumsByGenre.get(entry.getKey());
                    albumByGenre.entrySet().stream()
                                            .sorted(Comparator.comparing(Map.Entry<Integer, Integer>::getValue).reversed()
                                                    .thenComparing(Map.Entry::getKey))  // 플레이수로 역정렬 후 앨범 고유번호로 정렬
                                            .map(Map.Entry::getKey)
                                            .limit(2)
                                            .forEach(result::add);
                });

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        HashExample4 hashExample4 = new HashExample4();

        String[] genres1 = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays1 = {500, 600, 150, 800, 2500};
        int[] output1 = {4, 1, 3, 0};
        int[] result1 = hashExample4.solution(genres1, plays1);
        Assertions.assertArrayEquals(output1, result1);

        String[] genres2 = {"kpop", "pop", "jpop", "classic", "pop", "pop"};
        int[] plays2 = {100, 100, 200, 50, 100, 50};
        int[] output2 = {1, 4, 2, 0, 3};
        int[] result2 = hashExample4.solution(genres2, plays2);
        Assertions.assertArrayEquals(output2, result2);
    }
}
