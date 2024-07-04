package com.vetsmart.service.mapper;

import com.vetsmart.dto.MovieSpaceShipsDto;
import com.vetsmart.repository.MovieSpaceShip;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = {MovieSpaceShip.class, MovieSpaceShipsDto.class})
public interface SpaceShipMapper {
    SpaceShipMapper MAPPER = Mappers.getMapper(SpaceShipMapper.class);

    MovieSpaceShipsDto mapToDto(MovieSpaceShip entity);

    MovieSpaceShip mapToEntity(MovieSpaceShipsDto mallDto);

}
