package com.reaier.engking.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.SourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SourceAddVO {
    @Size(max = 10240, min = 2, message = "内容（图片地址）长度请保持在2到10240个汉字以内")
    @NotBlank(message = "内容（图片地址）长度请保持在2到10240个汉字以内")
    @Schema(description = "处理的内容或图片地址", defaultValue = "Apps may be able to access head pose information when playing spatialized audio.")
    @JsonProperty(value = "content", required = true)
    String content;

    @Schema(description = "上传的类型：图片，文字等，根据规则可自动推断", defaultValue = "TEXT", nullable = true)
    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    SourceType type;
}
