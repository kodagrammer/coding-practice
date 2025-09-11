package dev.dyko.codingtest.programmers.level1;

import java.util.*;
import java.util.stream.Collectors;

/*################################
 * 플랫폼: 프로그래머스
 * 레벨: Level 1
 * 문제유형: 2022 KAKAO BLIND RECRUITMENT
 * URL: https://school.programmers.co.kr/learn/courses/30/lessons/92334
 * ################################
 */
public class ReportResult {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, ReportInfo> reportedInfo = getReportInfo(report);
        List<String> idList = java.util.Arrays.asList(id_list);

        for(ReportInfo info : reportedInfo.values()) {
            if (info.getReportedCnt() >= k){
                List<String> reportingUser = info.getReportingUser();

                for (String reportingUserId : reportingUser){
                    int idIdx = idList.indexOf(reportingUserId);
                    answer[idIdx]++;
                }
            }
        }

        return answer;
    }

    private Map<String, ReportInfo> getReportInfo(String[] report) {
        Map<String, ReportInfo> reportInfoMap = new HashMap<>();

        for(String item : Arrays.stream(report).distinct().collect(Collectors.toList())) {
            String reportingUser = item.split(" ")[0];
            String reportedUser = item.split(" ")[1];

            if (!reportInfoMap.containsKey(reportedUser)){
                ReportInfo reportInfo = new ReportInfo(reportedUser);
                reportInfo.reported(reportingUser);

                reportInfoMap.put(reportedUser, reportInfo);
            } else {
                reportInfoMap.get(reportedUser).reported(reportingUser);
            }
        }

        return reportInfoMap;
    }


    private class ReportInfo {
        private String id;
        private List<String> reportingUser;

        public ReportInfo (String id) {
            this.id = id;
            this.reportingUser = new ArrayList<>();
        }

        public String getId() { return this.id; }
        public int getReportedCnt() { return this.reportingUser.size(); }
        public List<String> getReportingUser() { return this.reportingUser; }

        public void reported(String reportingUserId){
            this.reportingUser.add(reportingUserId);
        }
    }
}
