package com.restaurantrest.restaurantrest.conroller;

import com.restaurantrest.restaurantrest.domain.Temp;
import com.restaurantrest.restaurantrest.domain.TempDto;
import com.restaurantrest.restaurantrest.mapper.TempMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/temps")
public class TempController {
    @Autowired
    private TempMapper tempMapper;

    @Autowired
    private DbService service;

    @RequestMapping(method = RequestMethod.POST, value = "createTemp", consumes = APPLICATION_JSON_VALUE)
    public TempDto createTemp(@RequestBody TempDto tempDto){
        return tempMapper.mapToTempDto(service.saveTemp(tempMapper.mapToTemp(tempDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTemps")
    public List<TempDto> getTemps(){
        List<Temp> temps = service.getTemps();
        return tempMapper.mapToTempDtoList(temps);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTemp/{tempId}")
    public TempDto getTempById(@PathVariable Long tempId) throws TempNotFoundException{
        return tempMapper.mapToTempDto(service.findTempById(tempId).orElseThrow(TempNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeTemp/{tempId}")
    public void removeTempById(@PathVariable Long tempId) {
        Optional<Temp> tempById = service.getTemps().stream()
                .filter(temp-> temp.getTempId() == tempId)
                .findFirst();
        if (tempById.isPresent()) {
            service.removeTempById(tempId);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "editTemp")
    public void editTempById(@RequestBody TempDto tempDto){
        Optional<Temp> tempById = service.getTemps().stream()
                .filter(temp -> temp.getTempId() == tempDto.getTempId())
                .findFirst();
        if (tempById.isPresent()) {
            service.removeTempById(tempDto.getTempId());
            service.saveTemp(tempMapper.mapToTemp(tempDto));
        }
    }
}
