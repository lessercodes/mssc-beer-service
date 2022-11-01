package com.lessercodes.msscbeerservice.web.controller;

import java.math.BigDecimal;
import java.util.UUID;

import com.lessercodes.msscbeerservice.service.BeerService;
import com.lessercodes.msscbeerservice.web.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BeerService beerService;

    @Test
    @SneakyThrows
    void getBeer() {
        Mockito.when(beerService.getById(Mockito.any())).thenReturn(createValidBeerDto());
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void saveNewBeer() {
        Mockito.when(beerService.saveNewBeer(Mockito.any())).thenReturn(UUID.randomUUID());
        val dto = createValidBeerDto();
        val dtoJson = objectMapper.writeValueAsString(dto);
        mockMvc.perform(post("/api/v1/beer").contentType(MediaType.APPLICATION_JSON)
                .content(dtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    void updateBeer() {
        Mockito.doNothing().when(beerService).updateBeer(Mockito.any(), Mockito.any());
        val dto = createValidBeerDto();
        val dtoJson = objectMapper.writeValueAsString(dto);
        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoJson))
                .andExpect(status().isNoContent());
    }

    private BeerDto createValidBeerDto() {
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyle.ALE)
                .price(new BigDecimal("2.99"))
                .upc(123456789L)
                .build();
    }

}
