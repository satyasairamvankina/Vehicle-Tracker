package com.saivankina.services;

import com.saivankina.Repository.ReadingsRepository;
import com.saivankina.Repository.TiresRepository;
import com.saivankina.entity.Readings;
import com.saivankina.entity.Tires;
import com.saivankina.entity.Vehicle;
import com.saivankina.exceptions.ResourceBadRequest;
import com.saivankina.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReadingServiceImpl implements  ReadingService {

    @Autowired
    private ReadingsRepository readingsRepository;

    @Autowired
    private TiresRepository tiresRepository;

    @Autowired
    private AlertService alertService;

    @Autowired
    private  VehicleService vehicleService;

    @Override
    public List<Readings> findAll() {
        return (List<Readings>) readingsRepository.findAll();
    }

    @Override
    public Readings findById(String id) {
        Optional<Readings> readings = readingsRepository.findById(id);
        if(!readings.isPresent()){
            throw  new ResourceNotFoundException("Readins with "+id +" not Found");
        }
        return readings.get();
    }

    @Override
    public Readings createReading(Readings readings) {
        if(readings == null){
            throw  new ResourceBadRequest("Vehicle is  "+ readings +" can not be created");
        }
        System.out.println("reading are "+readings);
        Tires tire = tiresRepository.save(readings.getTires());
        Readings readings1 = readingsRepository.save(readings);
        System.out.println("*****************reading 1 are "+readings1);
        if(readings1 == null ){
            throw  new ResourceBadRequest("Vehicle is  "+ readings1 +" can not be created");
        }
        Vehicle vehicle = vehicleService.findById(readings1.getVin());

        //checking alerts here
        alertService.checkAlerts(readings,tire,vehicle);
        return readings1 ;
    }

    @Override
    public Readings updateReading(String id, Readings reading) {
        Optional<Readings> existing = readingsRepository.findById(id);
        if (!existing.isPresent()) {
            throw new ResourceNotFoundException("Employee with id " + id + " doesn't exist.");
        }
        return (Readings) readingsRepository.save(reading);
    }

    @Override
    public void deleteReading(String id) {

    }
}
