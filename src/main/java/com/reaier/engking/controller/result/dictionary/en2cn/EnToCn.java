package com.reaier.engking.controller.result.dictionary.en2cn;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnToCn {
    private static final long serialVersionUID = 1L;

    //词性
    @JsonProperty("part")
    String part;

    //单词解释，相同词性之间用|分隔
    @JsonProperty("means")
    String means;
}
