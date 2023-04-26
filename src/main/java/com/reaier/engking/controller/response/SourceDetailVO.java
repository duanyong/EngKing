package com.reaier.engking.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class SourceDetailVO {
    @Schema(description = "数据主键")
    @JsonProperty("id")
    private Integer id;

    @Schema(description = "系列主键（微博主键）")
    @JsonProperty("content")
    String content;

    @Schema(description = "上传的类型：图片，文字等")
    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    SourceType type;

    @Schema(description = "处理进度：未处理，处理中，已处理")
    @Enumerated(EnumType.STRING)
    @JsonProperty("process_status")
    SourceProcess processStatus;
}
