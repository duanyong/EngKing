package com.reaier.engking.controller;

import com.reaier.engking.ApplicationTest;
import com.reaier.engking.controller.request.SourceAddVO;
import com.reaier.engking.controller.request.WordAddVO;
import com.reaier.engking.utils.JsonUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class WordControllerTest extends ApplicationTest {
    private static final String API = "/word";

    @Resource
    private MockMvc mvc;

    private MvcResult mvcResult;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void add() {
        final String api = API + "/add";
        WordAddVO params = WordAddVO.builder()
                .sourceId(3)
                .words(Arrays.stream("Apps may be able to access head pose information when playing audio".split(" ")).toList())
                .build();

        try {
            mvcResult = mvc.perform(
                    post(api)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtils.obj2Json(params))
                            .accept(MediaType.APPLICATION_JSON)                 // 接受的格式为JSON
            ).andExpect(
                    status().is2xxSuccessful()
            ).andExpect(
                    MockMvcResultMatchers.jsonPath("code").value(0)
            ).andDo(
                    print()
            ).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}