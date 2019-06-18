package com.saivankina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saivankina.Repository.ReadingsRepository;
import com.saivankina.Repository.TiresRepository;
import com.saivankina.Repository.VehicleRepository;
import com.saivankina.entity.Readings;
import com.saivankina.entity.Tires;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class ReadingControllerTest {

    @Autowired
    private MockMvc mvc;


    @Autowired
    private ReadingsRepository readingsRepository;

    @Autowired
    private TiresRepository tiresRepository;

    List<Readings> readingsList;
    @Before
    public void setup(){
        Date a = new Date();
        Readings readings = new Readings();
        readings.setVin("vin1");
        readings.setTimestamp("23-34-34 12:32");
        readings.setSpeed(230);
        readings.setLongitude(23.43434);
        readings.setLatitude(34.9099343);
        readings.setCruiseControlOn(true);
        readings.setEngineCoolantLow(true);
        readings.setFuelVolume(45.0);
        readings.setEngineRpm(234);
        Tires tires = new Tires();
        tires.setFrontLeft(23);
        tires.setFrontRight(43);
        tires.setRearLeft(32);
        tires.setRearRight(43);
        readings.setTires(tires);
        readings.setEngineHp(778);

        readingsList = Collections.singletonList(readings);
        tiresRepository.save(tires);
        readingsRepository.save(readings);

    }

    @After
    public void cleanup(){
        readingsRepository.deleteAll();
    }
    @Test
    public void create() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Readings readings =  readingsList.get(0);
        readings.setVin("vin6");
        Date a = new Date();
        Readings read = new Readings();
        read.setVin("vin09");
        read.setTimestamp("23-34-34 99 12:32");
        read.setSpeed(230);
        read.setLongitude(23.43434);
        read.setLatitude(34.9099343);
        read.setCruiseControlOn(true);
        read.setEngineCoolantLow(true);
        read.setFuelVolume(45.0);
        read.setEngineRpm(234);
        Tires tires = new Tires();
        tires.setFrontLeft(33);
        tires.setFrontRight(35);
        tires.setRearLeft(32);
        tires.setRearRight(37);
//        read.getTires().setRearRight(43);
//        read.getTires().setRearLeft(41);
//        read.getTires().setFrontRight(44);
//        read.getTires().setFrontLeft(43);
        read.setTires(tires);
        read.setEngineHp(778);
        mvc.perform(MockMvcRequestBuilders.post("/readings")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsString(read)));
//                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.vin", Matchers.is("vin09")));


    }
}