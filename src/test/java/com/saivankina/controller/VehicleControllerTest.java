package com.saivankina.controller;


import com.saivankina.Repository.ReadingsRepository;
import com.saivankina.Repository.VehicleRepository;
import com.saivankina.entity.Vehicle;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class VehicleControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private VehicleRepository vehicleRepository;

    List<Vehicle> vehicleList;
    @Before
    public void setup(){
        Date a = new Date();
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("vin1");
        vehicle.setMake("Honda");
        vehicle.setModel("civic");
        vehicle.setYear(2017);
        vehicle.setRedlineRpm(300);
        vehicle.setMaxFuelVolume(15);
        vehicle.setLastServiceDate(a);

        vehicleList = Collections.singletonList(vehicle);
        vehicleRepository.save(vehicle);

    }

    @After
    public void cleanup(){
        vehicleRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].model",Matchers.equalTo("civic")));
    }

    @Test
    public void findById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/vin1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.vin",Matchers.is("vin1")));
    }
    @Test
    public void findById404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/dsjhsd"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void findMake() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/vin1/make"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.is("Honda")));
    }

    @Test
    public void findMake404() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/vinnj1/make"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void findmodel() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/vin1/model"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.is("civic")));
    }

    @Test
    public void findYear() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/vin1/year"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.is(2017)));
    }

    @Test
    public void findredlineRpm() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/vin1/redlineRpm"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.is(300)));
    }

    @Test
    public void findMaxFuelVolume() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/vin1/maxFuelVolume"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.is(15)));
    }

    @Test
    public void findLastServiceDate() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/vehicles/vin1/lastServiceDate"))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.is(new Timestamp(vehicleList.get(0).getLastServiceDate().getTime()).toString())));
//        vehicleList.get(0).getLastServiceDate().getTime()))
//        new Date().getTime()
    }
}