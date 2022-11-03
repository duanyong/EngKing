package com.reaier.engking.controller;

import com.reaier.engking.ApplicationTest;
import com.reaier.engking.controller.request.SourceAddVO;
import com.reaier.engking.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class SourceControllerTest extends ApplicationTest {
    private static final String API = "/source";

    @Resource
    private MockMvc mvc;

    private MvcResult mvcResult;


    @Test
    void add() {
        final String api = API + "/add";
        SourceAddVO params = SourceAddVO.builder()
                .content("Apps may be able to access head pose information when playing spatialized audio.")
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


    @Test
    public void detail() {
        final String api = API + "/detail/1";

        try {
            mvcResult = mvc.perform(
                    get(api)
                            .contentType(MediaType.APPLICATION_JSON)
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


    @Test
    void list() {
    }
}