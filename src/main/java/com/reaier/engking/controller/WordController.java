package com.reaier.engking.controller;

import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceProcessStatus;
import com.reaier.engking.constants.SourceType;
import com.reaier.engking.controller.exception.APIException;
import com.reaier.engking.controller.exception.SourceException;
import com.reaier.engking.controller.exception.WordException;
import com.reaier.engking.controller.request.SourceAddVO;
import com.reaier.engking.controller.request.SourcePageVO;
import com.reaier.engking.controller.request.WordAddVO;
import com.reaier.engking.controller.response.SourceDetailVO;
import com.reaier.engking.controller.status.ApiStatus;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.SourceWord;
import com.reaier.engking.domain.Word;
import com.reaier.engking.repository.SourceWordRepository;
import com.reaier.engking.repository.WordRepository;
import com.reaier.engking.sequence.events.SourceEvent;
import com.reaier.engking.service.SourceService;
import com.reaier.engking.utils.Copier;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Min;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Log4j2
@Tag(name = "用户添加单词到自己的词库中")
@RestController
@RequestMapping("/word")
public class WordController extends AbstractController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    SourceService sourceService;

    @Resource
    SourceWordRepository sourceWordRepository;

    @Resource
    WordRepository wordRepository;

    private SourceDetailVO detail(Source source) {
        return Copier.from(source).ignoreNullValue().to(SourceDetailVO.builder().build());
    }

    private Page pagebar(Page<Source> page) {
        return new PageImpl<>(page.getContent().parallelStream().map(item -> detail(item)).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    @Operation(
            summary = "将选中的单词添加对应的小词典中"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SourceDetailVO.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "请求错误", content = {@Content()}),
            @ApiResponse(responseCode = "401", description = "參數錯誤或沒有權限", content = {@Content()}),
    })


    @PostMapping("/add")
    public List add(@Validated WordAddVO params) {
        Source source = sourceService.findById(params.getSourceId());

        if (Objects.isNull(source)) {
            throw new APIException(SourceException.THE_SOURCE_HAS_NOT_EXIST);
        }

        if (CollectionUtils.isEmpty(params.getWords())) {
            throw new APIException(WordException.THE_WORD_IS_EMPTY);
        }

        List<SourceWord> datas = params.getWords().parallelStream().distinct().map(item -> {
            Word word = wordRepository.findByName(item.trim());
            if (Objects.isNull(word)) {
                return null;
            }

            SourceWord data = sourceWordRepository.findBySourceIdAndWordId(source.getId(), word.getId());
            if (Objects.isNull(data)) {
                return null;
            }

            return SourceWord.builder().sourceId(source.getId()).wordId(word.getId()).build();
        }).filter(Objects::nonNull).collect(Collectors.toList());

        sourceWordRepository.saveAll(datas);

        return datas.parallelStream().map(data -> wordRepository.findById(data.getId())).collect(Collectors.toList());
    }
}

