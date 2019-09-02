package com.saivankina.controller;


import com.saivankina.entity.Alert;
import com.saivankina.entity.Vehicle;
import com.saivankina.services.AlertService;
import com.saivankina.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private AlertService alertService;

    @GetMapping
    public List<Vehicle> findAll(){
       return vehicleService.findAl();
    }

    @GetMapping({"{id}"})
    public Vehicle findById(@PathVariable("id")  String id){
        return  vehicleService.findById(id);
    }
    @GetMapping("{id}/make")
    public String findMake(@PathVariable("id")  String id){
        return  findById(id).getMake();
    }

    @GetMapping("{id}/model")
    public String findmodel(@PathVariable("id")  String id){
        return  findById(id).getModel();
    }

    @GetMapping("{id}/year")
    public Integer findYear(@PathVariable("id")  String id){
        return  findById(id).getYear();
    }

    @GetMapping("{id}/redlineRpm")
    public Integer findredlineRpm(@PathVariable("id")  String id){
        return  findById(id).getRedlineRpm();
    }

    @GetMapping("{id}/maxFuelVolume")
    public Integer findMaxFuelVolume(@PathVariable("id")  String id){
        return  findById(id).getMaxFuelVolume();
    }

    @GetMapping("{id}/lastServiceDate")
    public Date findLastServiceDate(@PathVariable("id")  String id){
        return  findById(id).getLastServiceDate();
    }
    @PostMapping
    public Vehicle create(@RequestBody  Vehicle vehicle){
        return vehicleService.createVehicle(vehicle);
    }


    @PutMapping()
    public  List<Vehicle> updateAll(@RequestBody Vehicle vehicles[]){
        List<Vehicle> vehicleList= new ArrayList<>();

        for(Vehicle veh: vehicles) {
            String id = veh.getVin();
             Vehicle vehicle = vehicleService.updateVehicle(id, veh);
            vehicleList.add(vehicle);
        }
    return vehicleList;
    }


    @GetMapping("{id}/alerts")
    public List<Alert> fetchAllalertsOfVehicle(@PathVariable("id")  String id){
        return alertService.fetchAllalertsOfVehicle(id);
    }

}
