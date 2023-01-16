package com.reaier.engking.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.Language;
import com.reaier.engking.constants.SourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class SourceAddVO {
    @NotNull(message = "从哪种语言翻译到目标语言：哪种语言")
    @Schema(description = "从哪种语言翻译到目标语言：哪种语言")
    @Enumerated(EnumType.STRING)
    @JsonProperty("origin")
    Language origin;

    @NotNull(message = "从哪种语言翻译到目标语言：目标语言")
    @Schema(description = "从哪种语言翻译到目标语言：目标语言")
    @Enumerated(EnumType.STRING)
    @JsonProperty("target")
    Language target;

    @Size(max = 10240, min = 2, message = "内容长度请保持在2到10240个汉字以内")
    @NotBlank(message = "内容长度请保持在2到10240个汉字以内")
    @Schema(description = "处理的内容", defaultValue = "Apps may be able to access head pose information when playing spatialized audio.")
    @JsonProperty(value = "content", required = true)
    String content;

    @Schema(description = "上传的类型：图片，文字等，根据规则可自动推断", defaultValue = "TEXT", nullable = true)
    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    SourceType type;
}
