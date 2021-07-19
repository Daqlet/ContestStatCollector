package org.zam.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Problem {
    @JsonProperty("index") String index;

    public String getIndex() {
        return index;
    }
}

