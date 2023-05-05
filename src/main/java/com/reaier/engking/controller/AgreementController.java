package com.reaier.engking.controller;

import com.reaier.engking.controller.exception.APIException;
import com.reaier.engking.controller.exception.AgreementException;
import com.reaier.engking.controller.exception.SourceException;
import com.reaier.engking.controller.exception.WordException;
import com.reaier.engking.controller.request.AgreementSignVO;
import com.reaier.engking.controller.request.WordAddVO;
import com.reaier.engking.controller.response.ResponseVO;
import com.reaier.engking.controller.response.SourceDetailVO;
import com.reaier.engking.domain.*;
import com.reaier.engking.repository.*;
import com.reaier.engking.service.AgreementService;
import com.reaier.engking.service.SourceService;
import com.reaier.engking.utils.Copier;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Log4j2
@Tag(name = "APP对应的协议相关处理类")
@RestController
@RequestMapping("/agreement")
public class AgreementController extends AbstractController {
    @Resource
    AgreementRepository agreementRepository;

    @Resource
    UserAgreementRepository userAgreementRepository;

    @Resource
    AgreementService agreementService;


    @Operation(
            summary = "获取对应的网站条款"
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
    @GetMapping("/last")
    public Agreement last() {
        return agreementService.findByLast();
    }

    @Operation(
            summary = "用户同意对应的网站条款"
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
    @GetMapping("/sign/{id:\\+d}")
    public ResponseVO sign(@PathVariable(value = "id") Integer id) {
        Agreement agreement = agreementRepository.findById(id).orElse(null);

        if (Objects.isNull(agreement)) {
            throw new APIException(AgreementException.THE_DATA_HAS_NOT_EXIST);
        }

        UserAgreement has = userAgreementRepository.findByAgreementIdAndUserId(id, getAuth().getId());
        if (Objects.isNull(has)) {
            // 用户已经签过了
            has = userAgreementRepository.save(UserAgreement.builder().agreementId(id).userId(getAuth().getId()).build());

            if (Objects.isNull(has)) {
                throw new APIException(AgreementException.THE_DATA_CREATED_EXCEPTION);
            }
        }

        return ResponseVO.isOK();
    }
}

