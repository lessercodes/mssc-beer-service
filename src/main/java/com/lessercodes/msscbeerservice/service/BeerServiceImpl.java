package com.lessercodes.msscbeerservice.service;

import com.lessercodes.msscbeerservice.repository.BeerRepository;
import com.lessercodes.msscbeerservice.web.controller.NotFoundException;
import com.lessercodes.msscbeerservice.web.mapper.BeerMapper;
import com.lessercodes.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        val beer = beerRepository.findById(beerId)
                .orElseThrow(NotFoundException::new);
        return beerMapper.beerToBeerDto(beer);
    }

    @Override
    public UUID saveNewBeer(BeerDto beerDto) {
        val beer = beerMapper.beerDtoToBeer(beerDto);
        val savedBeer = beerRepository.save(beer);
        return savedBeer.getId();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        val existingBeer = beerRepository.findById(beerId)
                .orElseThrow(NotFoundException::new);

        existingBeer.setBeerName(beerDto.getBeerName());
        existingBeer.setBeerStyle(beerDto.getBeerStyle().name());
        existingBeer.setPrice(beerDto.getPrice());
        existingBeer.setUpc(beerDto.getUpc());
        beerRepository.save(existingBeer);
    }

    @Override
    public void deleteBeer(UUID beerId) {
        beerRepository.deleteById(beerId);
    }
}
