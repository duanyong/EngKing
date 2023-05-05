package com.reaier.engking.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class AgreementSignVO extends Auth {
    @Schema(description = "协议主键", defaultValue = "1")
    @NotNull(message = "请指定协议的数据主锓")
    @JsonProperty("agreement_id")
    Integer agreementId;
}