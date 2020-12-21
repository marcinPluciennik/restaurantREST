package com.restaurantrest.restaurantrest.conroller;

import com.google.gson.Gson;
import com.restaurantrest.restaurantrest.client.WeatherClient;
import com.restaurantrest.restaurantrest.domain.Temp;
import com.restaurantrest.restaurantrest.domain.TempDto;
import com.restaurantrest.restaurantrest.facade.TempFacade;
import com.restaurantrest.restaurantrest.mapper.TempMapper;
import com.restaurantrest.restaurantrest.model.weather.ConsolidatedWeather;
import com.restaurantrest.restaurantrest.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TempController.class)
public class TempControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherClient weatherClient;

    @MockBean
    private DbService service;

    @MockBean
    private TempMapper tempMapper;

    @MockBean
    private TempFacade tempFacade;

    @MockBean
    private ConsolidatedWeather consolidatedWeather;


    @Test
    public void shouldSaveTemp() throws Exception{
        //Given
        Temp temp = new Temp(1L, LocalDate.of(2020,1,21), 10.0);
        ConsolidatedWeather consolidatedWeather1 = new ConsolidatedWeather(1L, "2020-12-21",
                10.0);

        when(tempFacade.getConsolidatedWeather()).thenReturn(consolidatedWeather1);
        when(consolidatedWeather.getApplicableDate()).thenReturn("2020-12-21");
        when(consolidatedWeather.getTheTemp()).thenReturn(10.0);
        doNothing().when(service).saveTemp(temp.getDate(), temp.getTemp());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(temp);

        //When & Then
        mockMvc.perform(post("/temps/saveTemp")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFetchTempList() throws Exception {
        //Given
        List<Temp> tempList = new ArrayList<>();
        Temp temp1 = new Temp(1L, LocalDate.of(2020,12,21), 1.0);
        Temp temp2 = new Temp(2L, LocalDate.of(2020,12,20), 2.0);
        Temp temp3 = new Temp(3L, LocalDate.of(2020,12,19), 3.0);
        tempList.add(temp1);
        tempList.add(temp2);
        tempList.add(temp3);

        List<TempDto> tempDtoList = new ArrayList<>();
        TempDto tempDto1 = new TempDto(1L, LocalDate.of(2020,12,21), 1.0);
        TempDto tempDto2 = new TempDto(2L, LocalDate.of(2020,12,20), 2.0);
        TempDto tempDto3 = new TempDto(3L, LocalDate.of(2020,12,19), 3.0);
        tempDtoList.add(tempDto1);
        tempDtoList.add(tempDto2);
        tempDtoList.add(tempDto3);

        when(service.getTemps()).thenReturn(tempList);
        when(tempMapper.mapToTempDtoList(tempList)).thenReturn(tempDtoList);

        //When & Then
        mockMvc.perform(get("/temps/getTemps")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].tempId", is(1)))
                .andExpect(jsonPath("$[1].tempId", is(2)))
                .andExpect(jsonPath("$[2].tempId", is(3)))
                .andExpect(jsonPath("$[0].date", is("2020-12-21")))
                .andExpect(jsonPath("$[1].date", is("2020-12-20")))
                .andExpect(jsonPath("$[2].date", is("2020-12-19")))
                .andExpect(jsonPath("$[0].temp", is(1.0)))
                .andExpect(jsonPath("$[1].temp", is(2.0)))
                .andExpect(jsonPath("$[2].temp", is(3.0)));
    }

    @Test
    public void shouldDeleteTempById() throws Exception{
        //Given
        Temp temp = new Temp(1L, LocalDate.of(2020,12,21), 1.0);
        Long tempId = temp.getTempId();
        List<Temp> tempList = new ArrayList<>();
        tempList.add(temp);

        when(service.removeTempById(tempId)).thenReturn(true);

        //When & Then
        mockMvc.perform(delete("/temps/removeTemp/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldEditTemp() throws Exception{
        //Given
        TempDto tempDto = new TempDto(1L, 1.0);
        Temp temp = new Temp(1L,1.0);

        when(tempMapper.mapToTemp(tempDto)).thenReturn(temp);
        doNothing().when(service).updateTemp(temp);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(tempDto);

        //When & Then
        mockMvc.perform(put("/temps/editTemp")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

}