package org.zam.dao;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.zam.models.*;

import java.io.IOException;
import java.util.*;

@Component
public class RequestDAO {
    private Map<Integer, Set<String>> contestMap = new HashMap<>();
    private List<Contest> contestList = new ArrayList<>();
    private ResponseContest tempResp = null;
    private RestTemplate template;
    {
        template = new RestTemplate();
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }
    public Map<Integer, Set<String>> index() {
        contestMap.clear();
        return contestMap;
    }
    public List<Contest> order() {
        contestList.clear();
        return contestList;
    }
    public void find(Request request) throws IOException, InterruptedException {
        String url = "https://codeforces.com/api/contest.list";
        ResponseContest response = new ResponseContest();
        if(tempResp == null)
            response = template.getForObject(url, ResponseContest.class);
        else
            response = tempResp;
        tempResp = response;
        for(int i = 0; contestMap.size() < request.getCount() && response.getContests()[i].getId() > 0; ++i) {
            Contest contest = response.getContests()[i];
            if(isValidContest(contest)) {
                contestMap.put(contest.getId(), new HashSet<>());
                contestList.add(contest);
            }
        }
        countVerdictOk(request);
    }
    private void countVerdictOk(Request request) {
        String url = "https://codeforces.com/api/user.status?handle={0}&from=1&count=10000";
        ResponseSubmissions responseSubmissions;
        try {
            responseSubmissions = template.getForObject(url, ResponseSubmissions.class,  request.getHandle());
        } catch (Exception e) {
            return;
        }
        Submission[] submissions = responseSubmissions.getSubmissions();
        for(Submission submission : submissions) {
            if(isValidSubmission(submission)) {
                contestMap.get(submission.getContestId()).add(submission.getProblem().getIndex());
            }
        }
        int total = 0;
        for(Map.Entry<Integer, Set<String>> entry : contestMap.entrySet()) {
            total += entry.getValue().size();
        }
        request.setTotal(total);
    }
    private boolean isValidSubmission(Submission sub) {
        if(sub.getVerdict() == Verdict.OK && contestMap.containsKey(sub.getContestId()) &&
            sub.getParty().getPartType() == PartType.CONTESTANT)
            return true;
        return false;
    }
    private boolean isValidContest(Contest contest) {
        if(contest.getPhase() == "FINISHED" &&
                contest.getName().contains("Codeforces") &&
                (contest.getName().contains("Div. 2") ||
                        contest.getName().contains("Div. 3"))) {
            return true;
        }
        return false;
    }
}
