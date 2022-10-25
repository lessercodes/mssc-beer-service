package com.lessercodes.msscbeerservice.web.mapper;

import com.lessercodes.msscbeerservice.domain.Beer;
import com.lessercodes.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {DateMapper.class}
)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beer);

}
