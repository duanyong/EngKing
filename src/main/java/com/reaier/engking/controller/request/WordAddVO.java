package com.reaier.engking.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class WordAddVO extends Auth {
    @Schema(description = "源的主键", defaultValue = "1")
    @NotNull(message = "请指定对应的源文件主键")
    @JsonProperty("source_id")
    Integer sourceId;

    @Size(max = 512, min = 1, message = "单词长度请保持在1到512个长度以内")
    @NotBlank(message = "单词长度请保持在1到100个汉字以内")
    @Schema(description = "单词长度请保持在1到100个汉字以内", defaultValue = "[\"apple\"]")
    @JsonProperty(value = "content", required = true)
    List<String> words;
}