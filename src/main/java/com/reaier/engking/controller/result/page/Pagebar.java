package com.reaier.engking.controller.result.page;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@Builder
public class Pagebar {
    //是否第一页
    boolean first;
    //是否最后一页
    boolean last;

    //每页个数
    int size;
    //当前页
    int current;
    //当前页对应的页码
    int nums[];
    //总数
    long total;

    long totalPage;

    public static Pagebar fromPageable(Page page) {
        return Pagebar.builder()
                .current(page.getNumber() + 1)
                .size(page.getSize())
                .total(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}
