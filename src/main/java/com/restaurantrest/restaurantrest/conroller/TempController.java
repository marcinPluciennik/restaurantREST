package com.restaurantrest.restaurantrest.conroller;

import com.restaurantrest.restaurantrest.client.WeatherClient;
import com.restaurantrest.restaurantrest.domain.TempDto;
import com.restaurantrest.restaurantrest.facade.TempFacade;
import com.restaurantrest.restaurantrest.mapper.TempMapper;
import com.restaurantrest.restaurantrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/temps")
public class TempController {
    @Autowired
    private TempMapper tempMapper;

    @Autowired
    private DbService service;

    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private TempFacade tempFacade;

    @Scheduled(cron = "0 0 9 * * *")
    @RequestMapping(method = RequestMethod.POST, value = "saveTemp", consumes = APPLICATION_JSON_VALUE)
    public void saveTemp(){
        service.saveTemp(
                LocalDate.parse(tempFacade.getConsolidatedWeather().getApplicableDate()),
                tempFacade.getConsolidatedWeather().getTheTemp());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTemps")
    public List<TempDto> getTemps(){
        return tempMapper.mapToTempDtoList(service.getTemps());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeTemp/{tempId}")
    public boolean removeTempById(@PathVariable Long tempId) {
        return service.removeTempById(tempId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "editTemp")
    public void editTempById(@RequestBody TempDto tempDto){
        service.updateTemp(tempMapper.mapToTemp(tempDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTemp/{date}")
    public TempDto getTempByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws TempNotFoundException{
        return tempMapper.mapToTempDto(service.findTempByDate(date));
    }
}
