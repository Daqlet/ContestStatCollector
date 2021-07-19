package org.zam.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ResponseSubmissions implements Serializable {
    @JsonProperty("status") String status;
    @JsonProperty("result") Submission[] submissions;

    public Submission[] getSubmissions() {
        return submissions;
    }
    public String getStatus() { return status; }
}
