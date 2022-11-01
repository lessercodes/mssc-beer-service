package com.lessercodes.msscbeerservice.web.controller;

import com.lessercodes.msscbeerservice.service.BeerService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId) {
        val beer = beerService.getById(beerId);
        return ResponseEntity.ok(beer);
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<UUID> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        val id = beerService.saveNewBeer(beerDto);
        val location = String.format("/api/v1/beer/%s", id);
        val newBeerUri = new URI(location);
        return ResponseEntity.created(newBeerUri).build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> updateBeer(
            @PathVariable UUID beerId,
            @Valid @RequestBody BeerDto beerDto
    ) {
        beerService.updateBeer(beerId, beerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<Void> deleteBeer(@PathVariable UUID beerId) {;
        beerService.deleteBeer(beerId);
        return ResponseEntity.noContent().build();
    }

}
