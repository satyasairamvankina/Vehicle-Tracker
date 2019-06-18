package com.saivankina.services;

import com.saivankina.Repository.VehicleRepository;
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
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> findAl() {

        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Override
    public Vehicle findById(String id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(!vehicle.isPresent()){
            throw  new ResourceNotFoundException("Vehicle with "+id +" not Found");
        }
        return vehicle.get();
    }

    @Transactional
    public Vehicle createVehicle(Vehicle vehicle) {

        Vehicle vehi = (Vehicle) vehicleRepository.save(vehicle);
        if(vehi == null){
            throw  new ResourceBadRequest("Vehicle is  "+ vehicle +" can not be created");
        }
        return vehi ;
    }

    @Transactional
    public Vehicle updateVehicle(String id, Vehicle vehicle) {
        Vehicle vehi = (Vehicle) vehicleRepository.save(vehicle);
        Optional<Vehicle> existing = vehicleRepository.findById(id);
        if (!existing.isPresent()) {
            throw new ResourceNotFoundException("Employee with id " + id + " doesn't exist.");
        }
        return (Vehicle) vehicleRepository.save(vehicle);
    }

    @Transactional
    public void deleteVehicle(String id) {
        Vehicle vehicle = this.findById(id);
        vehicleRepository.delete(vehicle);
    }
}
