package com.reaier.engking.controller.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.controller.request.page.RequestPageVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SourcePageVO extends RequestPageVO {
    @Length(max = 512, message = "关键字请保持在3到512个字符以内")
    @Schema(description = "模糊搜索时对应的关键字")
    @JsonProperty("key")
    String key;
}
