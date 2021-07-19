package org.zam.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Contest implements Serializable {
    @JsonProperty("id") int id;
    @JsonProperty("name") String name;
    @JsonProperty("phase") Phase phase;
    public String getName() {
        return name;
    }

    public String getPhase() {
        return phase.name();
    }

    public int getId() {
        return id;
    }
}

enum Phase {
    BEFORE,
    CODING,
    PENDING_SYSTEM_TEST,
    SYSTEM_TEST,
    FINISHED
}
