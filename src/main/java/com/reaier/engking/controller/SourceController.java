package com.reaier.engking.controller;

import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceType;
import com.reaier.engking.controller.exception.APIException;
import com.reaier.engking.controller.exception.SourceException;
import com.reaier.engking.controller.request.SourceAddVO;
import com.reaier.engking.controller.request.SourcePageVO;
import com.reaier.engking.controller.response.SourceDetailVO;
import com.reaier.engking.controller.status.ApiStatus;
import com.reaier.engking.domain.Source;
import com.reaier.engking.service.SourceService;
import com.reaier.engking.utils.Copier;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;


@Log4j2
@Tag(name = "用户上传待翻译的资料")
@RestController
@RequestMapping("/source")
public class SourceController extends AbstractController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SourceService sourceService;

    private SourceDetailVO convertDomainVO(Source source) {
        return Copier.from(source).ignoreNullValue().to(SourceDetailVO.builder().build());
    }

    private Page pagebar(Page<Source> page) {
        return new PageImpl<>(page.getContent().parallelStream().map(item -> convertDomainVO(item)).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    @Operation(
            summary = "上传待翻译的资料"
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
    public SourceDetailVO add(@Validated @RequestBody SourceAddVO params) {
        String md5 = DigestUtils.md5DigestAsHex(params.getContent().getBytes(StandardCharsets.UTF_8));
        Source source = sourceService.findByToken(md5);

        if (Objects.isNull(source)) {
            source = sourceService.update(Copier.from(params).to(Source.builder().token(md5).type(SourceType.TEXT).processStatus(SourceProcess.WAIT).build()));
        }

        return convertDomainVO(source);
    }

    @Operation(
            summary = "按主键返回源资料系列详情",
            description = "返回源资料系列详情"
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
    @GetMapping("/detail/{id:\\d+}")
    public SourceDetailVO detail(@PathVariable(value = "id") @Min(value = 1, message = ApiStatus.PARAMETER_VALIDATE_ERROR_MESSAGE) Integer id) {
        Source source = sourceService.findById(id);
        if (null == source) {
            throw new APIException(SourceException.THE_SOURCE_HAS_NOT_EXIST);
        }

        return convertDomainVO(source);
    }

    @Operation(
            summary = "按页返回源资料列表"
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
    @PostMapping("/list")
    public Page list(@Validated @RequestBody SourcePageVO params) {
        return pagebar(sourceService.findAllByPage(params.getPage(), params.getSize()));
    }
}

