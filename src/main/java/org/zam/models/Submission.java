package org.zam.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Submission {
    @JsonProperty("problem") Problem problem;
    @JsonProperty("verdict") Verdict verdict;
    @JsonProperty("author") Party party;
    @JsonProperty("contestId") int contestId;

    public Problem getProblem() {
        return problem;
    }

    public Verdict getVerdict() {
        return verdict;
    }

    public Party getParty() {
        return party;
    }

    public int getContestId() { return contestId; }

}
