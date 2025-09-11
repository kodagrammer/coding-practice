package dev.dyko.codingtest.tsannual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrueSpeakerSearcher {
    public static void main(String[] args) {
        // N롱이와 각 발언들 객체화
        List<NRong> nRongList = new ArrayList<>();
        nRongList.add(new NRong("일롱이", "삼롱이가 토롱이", "삼롱이", null));
        nRongList.add(new NRong("이롱이", "내가 토롱이", "이롱이", null));
        nRongList.add(new NRong("삼롱이", "사롱이와 오롱이는 가짜", null, Arrays.asList("사롱이", "오롱이")));
        nRongList.add(new NRong("사롱이", "이롱이가 토롱이", "이롱이", null));
        nRongList.add(new NRong("오롱이", "이롱이는 가짜", null, Arrays.asList("이롱이")));

        // 토롱이 찾기 호출
        NRong thorong = getThorong(nRongList);
        assert thorong != null;
        System.out.printf("진짜 토롱이는 %s입니다.%n", thorong.getName());
    }

    public static NRong getThorong(List<NRong> nRongList) {
        List<NRong> contradicts = new ArrayList<>();

        // 1. 모순되는 발언을 하는 롱이들 찾기
        for(NRong nRong : nRongList) {
            for(NRong otherRong : nRongList) {
                if(otherRong.equals(nRong)) {
                    continue;
                }

                // 모순찾기 (둘 중 하나는 무조건 참)
                if(nRong.isContradict(otherRong)) {
                    contradicts.add(nRong);
                    if(!contradicts.contains(otherRong)) contradicts.add(otherRong);

                    System.out.printf("%s와 %s의 발언이 모순임%n", nRong.getName(), otherRong.getName());
                    System.out.printf("- %s의 발언: \"%s\"%n", nRong.getName(), nRong.getProposition());
                    System.out.printf("- %s의 발언: \"%s\"%n", otherRong.getName(), otherRong.getProposition());
                }
            }

            if(!contradicts.isEmpty()){
                break;
            }
        }

        // 2. 모순되는 롱이 중 토롱이 찾기
        for(NRong nRong : contradicts) {
            List<NRong> trueSpeakerList = new ArrayList<>();
            for(NRong otherRong : nRongList) {
                if(contradicts.contains(otherRong)) {
                    continue;
                }

                // 토롱이가 맞다면, 다른 롱이는 거짓이어야 함. 참이 겹치는 롱이 확인
                if(nRong.isTrueWhenIThorong(otherRong)) {
                    trueSpeakerList.add(otherRong);
                    }
            }
            // 나홀로 참이라면 그것이 토롱!
            if(trueSpeakerList.isEmpty()) {
                return nRong;
            }
        }

        return null;
    }
}

class NRong {
    private final String name;
    private final String proposition;
    private final String thorong;
    private final List<String> liarList;

    public NRong(String name, String proposition, String thorong, List<String> liarList) {
        this.name = name;
        this.proposition = proposition;
        this.thorong = thorong;
        this.liarList = liarList;
    }

    public String getName() {
        return this.name;
    }

    public String getProposition() {
        return this.proposition;
    }

    public String getThorong() {
        return this.thorong;
    }

    public List<String> getLiarList() {
        return this.liarList;
    }

    public boolean isContradict(NRong otherRong) {
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

    // 내가 토롱일 때, 다른 토롱이의 발언의 참거짓 판단
    public boolean isTrueWhenIThorong(NRong otherRong) {
        // 모순이 발생하면, 이미 충돌
        if(isContradict(otherRong)) {
           return false;
        }

        // 지목한 토롱이가 본인이 아니면 거짓
        if(otherRong.getThorong() != null && !otherRong.getThorong().equals(this.name)) {
            return false;
        }

        // 본인이 누군가의 거짓말쟁이로 지목되면 충돌
        if(otherRong.getLiarList() != null && otherRong.getLiarList().contains(this.name)) {
            return false;
        }

        return true;
    }
}