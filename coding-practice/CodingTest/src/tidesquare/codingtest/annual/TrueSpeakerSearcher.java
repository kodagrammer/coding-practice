package tidesquare.codingtest.annual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrueSpeakerSearcher {
    public static void main(String[] args) {
        // N롱이와 각 발언들 객체화
        List<NRong> NRongList = new ArrayList<>();
        NRongList.add(new NRong("일롱이", "삼롱이", null));
        NRongList.add(new NRong("이롱이", "이롱이", null));
        NRongList.add(new NRong("삼롱이", null, Arrays.asList("사롱이", "오롱이")));
        NRongList.add(new NRong("사롱이", "이롱이", null));
        NRongList.add(new NRong("오롱이", null, Arrays.asList("이롱이")));

        // 토롱이 찾기 호출
        NRong thorong = NRong.getThorong(NRongList);
        System.out.println(String.format("진짜 토롱이는 %s입니다.", thorong.getName()));
    }
}

class NRong {
    private String name;
    private String thorong;
    private List<String> liarList;

    public NRong(String name, String thorong, List<String> liarList) {
        this.name = name;
        this.thorong = thorong;
        this.liarList = liarList;
    }

    public String getName() {
        return name;
    }

    public String getThorong() {
        return thorong;
    }

    public List<String> getLiarList() {
        return liarList;
    }

    public boolean isConflictSpeaking(NRong otherRong) {
        // 지목한 토롱이가 다르면, 둘 중 하나는 거짓
        if(this.thorong != null && !this.thorong.equals(otherRong.thorong)) {
            return true;
        }

        // 지목한 토롱이가 가짜 목록에 있으면, 둘 중 하나는 거짓
        if(this.thorong != null && otherRong.getLiarList() != null && otherRong.getLiarList().contains(this.thorong)) {
            return true;
        }

        // 지목한 거짓말쟁이가 토롱이로 지목되면, 둘 중 하나는 거짓
        if(this.liarList != null && otherRong.getThorong() != null && this.liarList.contains(otherRong.getThorong())) {
            return true;
        }

        return false;
    }

    public static NRong getThorong(List<NRong> NRongList) {
        for(NRong nRong : NRongList) {
            boolean allSpeakingConflict = true;
            for(NRong otherRong : NRongList) {
                if(otherRong.equals(nRong)) {
                    continue;
                }
                // 발언이 충돌나는지 확인
                if(!nRong.isConflictSpeaking(otherRong)) {
                    allSpeakingConflict = false;
                    break;
                }
            }
            // 토롱이를 제외한 나머지는 거짓말이어야 함 -> 즉, 본인의 발언과 다른 사람의 발언이 충돌나는 경우 토롱이임
            if(allSpeakingConflict) {
                return nRong;
            }
        }
        return null;
    }
}