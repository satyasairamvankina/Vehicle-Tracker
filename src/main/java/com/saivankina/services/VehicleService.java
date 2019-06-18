package com.saivankina.services;
import com.saivankina.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface VehicleService {

     List<Vehicle> findAl();
     Vehicle findById(String id);
     Vehicle createVehicle(Vehicle vehicle);// use put
     Vehicle updateVehicle(String id, Vehicle vehicle);
     void deleteVehicle(String id);

}
