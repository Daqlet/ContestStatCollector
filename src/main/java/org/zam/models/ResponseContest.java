package org.zam.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ResponseContest implements Serializable {
    @JsonProperty("status") String status;
    @JsonProperty("result") Contest[] contests;

    public Contest[] getContests() {
        return contests;
    }
}
