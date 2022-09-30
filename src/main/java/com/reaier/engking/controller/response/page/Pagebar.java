package com.reaier.engking.controller.response.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class Pagebar {
    @JsonProperty("page")
    int page;
    @JsonProperty("size")
    int size;

    @JsonProperty("total")
    long total;

    @JsonProperty("page_total")
    long pageTotal;

    public static Pagebar fromPageable(Page page) {
        return Pagebar.builder()
                .page(page.getNumber() + 1)
                .size(page.getSize())
                .total(page.getTotalElements())
                .pageTotal(page.getTotalPages())
                .build();
    }
}
