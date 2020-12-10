package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Temp;
import com.restaurantrest.restaurantrest.domain.TempDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TempMapper {

    public Temp mapToTemp(final TempDto tempDto){
        return new Temp(
                tempDto.getTempId(),
                tempDto.getDate(),
                tempDto.getTemp());
    }

    public TempDto mapToTempDto(final Temp temp){
        return new TempDto(
                temp.getTempId(),
                temp.getDate(),
                temp.getTemp());
    }

    public List<TempDto> mapToTempDtoList(final List<Temp> tempList){
        return tempList.stream()
                .map(t -> new TempDto(t.getTempId(), t.getDate(), t.getTemp()))
                .collect(Collectors.toList());
    }
}
