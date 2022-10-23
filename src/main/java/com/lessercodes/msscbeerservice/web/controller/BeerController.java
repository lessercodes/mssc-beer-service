package com.lessercodes.msscbeerservice.web.controller;

import lombok.SneakyThrows;
import lombok.val;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lessercodes.msscbeerservice.web.model.BeerDto;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId) {
        return ResponseEntity.ok(BeerDto.builder().build());
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<UUID> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        val mockLocation = "/mockLocation/" + beerDto.getId();
        val mockUri = new URI(mockLocation);
        return ResponseEntity.created(mockUri).build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> updateBeer(
            @PathVariable UUID beerId,
            @Valid @RequestBody BeerDto beerDto
    ) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<Void> deleteBeer(@PathVariable UUID beerId) {
        return ResponseEntity.noContent().build();
    }

}
