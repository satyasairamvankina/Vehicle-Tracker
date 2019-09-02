package com.saivankina.services;


import com.saivankina.Repository.ReadingsRepository;
import com.saivankina.Repository.TiresRepository;
import com.saivankina.entity.Readings;
import com.saivankina.entity.Tires;
import com.saivankina.entity.Vehicle;
import com.saivankina.exceptions.ResourceBadRequest;
import com.saivankina.exceptions.ResourceNotFoundException;
import io.swagger.models.auth.In;
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

@RunWith(SpringRunner.class)
public class ReadingServiceImplTest {

    @TestConfiguration
    static class ReadingServiceTestConfiguration{
        @Bean
        public ReadingService readingService(){
            return new ReadingServiceImpl();
        }
    }

    @Autowired
    private ReadingService readingService;

    @MockBean
    private TiresRepository tiresRepository;

    @MockBean
    private ReadingsRepository readingsRepository;

    @MockBean
    private AlertService alertService;

    @MockBean
    private  VehicleService vehicleService;

    List<Readings> readingsList;

    @Before
    public void startup(){


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


        Mockito.when(readingsRepository.findAll()).thenReturn(readingsList);
        Mockito.when(readingsRepository.findById(readings.getVin())).thenReturn(java.util.Optional.of(readings));
        Mockito.when(readingsRepository.save(readings)).thenReturn(readings);

    }

    @After
    public  void cleanup(){

    }


    @Test
    public void findAll() {
        List<Readings> readingsList1 = readingService.findAll();
        Assert.assertEquals("vehicles are equal",readingsList,readingsList1);
    }

    @Test
    public void findById() {
        Readings readings = readingService.findById(readingsList.get(0).getVin());
        Assert.assertEquals("vehicle with Id are same ",readings,readingsList.get(0));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findById404() {
        Readings readings = readingService.findById("dackdsm");
    }

    @Test
    public void createReading() {
        Readings readings = readingService.createReading(readingsList.get(0));
        Assert.assertEquals("vehicle with created ", readingsList.get(0),readings);
    }
    @Test(expected = ResourceBadRequest.class)
    public void createReading404() {
//        Readings read = readingsList.get(0);
//        read.setVin(null);
//        Readings readings = readingService.createReading(read);
        Readings readings = readingService.createReading(null);
    }
    @Test
    public void updateReading() {
        Readings readings = readingService.updateReading(readingsList.get(0).getVin(),readingsList.get(0));
        readings.setEngineHp(2019);
        Assert.assertEquals("vehicle is updated",readingsList.get(0).getEngineHp(),readings.getEngineHp());
    }
    @Test(expected = ResourceNotFoundException.class)
    public void updateReading404() {
        Readings readings = readingService.updateReading("hb guy b",readingsList.get(0));
    }

    @Test
    public void deleteReading() {
        readingService.deleteReading(readingsList.get(0).getVin());
//        VerifyAccess.isClassAccessible();
    }
}
