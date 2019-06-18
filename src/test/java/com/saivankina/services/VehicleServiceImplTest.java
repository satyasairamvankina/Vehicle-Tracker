package com.saivankina.services;


import com.saivankina.Repository.VehicleRepository;
import com.saivankina.entity.Vehicle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class VehicleServiceImplTest {


    @TestConfiguration
    static class vehicleServiceTestConfiguration{
        @Bean
        public VehicleService vehicleService(){
            return  new VehicleServiceImpl();
        }
    }

    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private VehicleRepository vehicleRepository;

    List<Vehicle> vehicleList;

    @Before
    public void startup(){

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


        Mockito.when(vehicleRepository.findAll()).thenReturn(vehicleList);
        Mockito.when(vehicleRepository.findById(vehicle.getVin())).thenReturn(java.util.Optional.of(vehicle));
        Mockito.when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

    }

    @After
    public  void cleanup(){

    }

    @Test
    public void findAl() {
        List<Vehicle> vehicleList1 = vehicleService.findAl();
        Assert.assertEquals("vehicles are equal",vehicleList,vehicleList1);
    }

    @Test
    public void findById() {
        Vehicle vehicle = vehicleService.findById(vehicleList.get(0).getVin());
        Assert.assertEquals("vehicle with Id are same ",vehicle,vehicleList.get(0));
    }

    @Test
    public void createVehicle() {
        Vehicle vehicle = vehicleService.createVehicle(vehicleList.get(0));
        Assert.assertEquals("vehicle with created ", vehicleList.get(0),vehicle);
    }

    @Test
    public void updateVehicle() {
        Vehicle vehicle = vehicleService.updateVehicle(vehicleList.get(0).getVin(),vehicleList.get(0));
        vehicle.setYear(2019);
        Assert.assertEquals("vehicle is updated",vehicleList.get(0).getYear(),vehicle.getYear());
    }

    @Test
    public void deleteVehicle() {
        vehicleService.deleteVehicle(vehicleList.get(0).getVin());

    }
}
