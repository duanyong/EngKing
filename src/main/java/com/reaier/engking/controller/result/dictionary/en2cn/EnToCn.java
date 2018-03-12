package com.reaier.engking.controller.result.dictionary.en2cn;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnToCn {
    private static final long serialVersionUID = 1L;

    //词性
    @SerializedName("part")
    String part;

    //单词解释，相同词性之间用|分隔
    @SerializedName("means")
    String means;
}
