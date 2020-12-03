package com.restaurantrest.restaurantrest.mapper;

import com.restaurantrest.restaurantrest.domain.Temp;
import com.restaurantrest.restaurantrest.domain.TempDto;
import org.springframework.stereotype.Component;

@Component
public class TempMapper {

    public Temp mapToTemp(final TempDto tempDto){
        return new Temp(
                tempDto.getTempId(),
                tempDto.getDate(),
                tempDto.getTemp()
        );
    }

    public TempDto mapToTempDto(final Temp temp){
        return new TempDto(
                temp.getTempId(),
                temp.getDate(),
                temp.getTemp()
        );
    }
}
