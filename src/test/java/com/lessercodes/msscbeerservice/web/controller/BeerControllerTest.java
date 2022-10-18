package com.lessercodes.msscbeerservice.web.controller;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lessercodes.msscbeerservice.web.model.BeerDto;

import lombok.SneakyThrows;
import lombok.val;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void getBeer() {
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void saveNewBeer() {
        val dto = BeerDto.builder().build();
        val dtoJson = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post("/api/v1/beer").contentType(MediaType.APPLICATION_JSON)
                .content(dtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    void updateBeer() {
        val dto = BeerDto.builder().build();
        val dtoJson = objectMapper.writeValueAsString(dto);
        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoJson))
                .andExpect(status().isNoContent());
    }

}
