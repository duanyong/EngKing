package com.reaier.engking.domain.trsanslate.iciba;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class Mean {
    @JsonProperty("part")
    String part;

    @JsonProperty("means")
    String[] means;

    public String getMeanString() {
        return StringUtils.join(means, "|");
    }
}
