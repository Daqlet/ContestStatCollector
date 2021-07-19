package org.zam.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Party {
    @JsonProperty("participantType") PartType partType;

    public PartType getPartType() {
        return partType;
    }
}
