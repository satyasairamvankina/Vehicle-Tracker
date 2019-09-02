package com.saivankina.services;

import com.saivankina.Location;
import com.saivankina.Priority;
import com.saivankina.Repository.AlertRepository;
import com.saivankina.entity.Alert;
import com.saivankina.entity.Readings;
import com.saivankina.entity.Tires;
import com.saivankina.entity.Vehicle;
import com.saivankina.exceptions.ResourceBadRequest;
import com.saivankina.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlertServiceImpl implements AlertService{

    @Autowired
    private AlertRepository alertRepository;

    @Override
    public Alert findById(String id) {
       Optional<Alert> a =  alertRepository.findById(id);
       a.orElseThrow(() ->
                new ResourceNotFoundException("The alert with Id " +id+"doesn't exists")
       );
        return a.get();
        //        Alert employee = repository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Alert findByVin(String vin) {
        Alert a =  alertRepository.findByVin(vin);
        if(a == null){
            throw new ResourceNotFoundException("Alert with Vin"+vin+"not found");
        }
        return a;

    }

    @Override
    public List<Alert> findAll() {
        return (List)alertRepository.findAll();
    }

    public Alert createAlert(Alert alert) {
        Alert a = alertRepository.save(alert);
        if(a == null) {
            throw new ResourceBadRequest("Bad request for creating alert"+ alert);
        }
        return a;

    }

    public Alert creteAlertBasedOnParameter(Priority priority, String vin,String alertDescription){
        Alert alert = new Alert();
        alert.setAlertDescription(alertDescription);
        alert.setVin(vin);
        alert.setPriority(priority);
        return createAlert(alert);


    }

    @Override
    public List<Alert> fetchAlertByTime(int hours) {
        Date date= new Date();
//        List<Alert> a = alertRepository.fetchAlertByTime(hours,date,Priority.High);
        List<Alert> a = alertRepository.fetchAlertByTime(Priority.High);
        if(a ==  null){
            throw new ResourceNotFoundException("no alerts witnin 2 hours");
        }
        return a;
    }


    @Override
    public List<Alert> fetchAllalertsOfVehicle(String  vin) {
        List<Alert> alerts = alertRepository.fetchAllalertsOfVehicle(vin);
        if(alerts == null){
            throw new ResourceNotFoundException("There are no alerts for vehicle "+vin);
        }
        return alerts;
    }

    @Override
    public List<Location> fetchLocationBasedOnTime(int time) {
        return null;
    }

    @Override
    public void checkAlerts(Readings readings1, Tires tire, Vehicle vehicle) {


        Tires tires = readings1.getTires();
        if(readings1.getEngineRpm() > vehicle.getRedlineRpm()){
            this.creteAlertBasedOnParameter(Priority.High, readings1.getVin(),
                    "Over speed: your vehicle RPM is greater than RedLineRpm");
        }
        if(readings1.getFuelVolume()< (0.1)*vehicle.getMaxFuelVolume()){
            this.creteAlertBasedOnParameter(Priority.Medium, readings1.getVin(),
                    "Low Fuel < 10%");
        }
        if((tires.getRearRight()< 32 || tires.getRearRight() > 36)){
            this.creteAlertBasedOnParameter(Priority.Low, readings1.getVin(),
                    "Check Rear Right tire pressure");
        }
        if((tires.getRearLeft()< 32 || tires.getRearLeft() > 36)){
            this.creteAlertBasedOnParameter(Priority.Low, readings1.getVin(),
                    "Check Rear Left tire pressure");
        }
        if((tires.getFrontRight()< 32 || tires.getFrontRight() > 36)){
            this.creteAlertBasedOnParameter(Priority.Low, readings1.getVin(),
                    "Check Front Right tire pressure");
        }
        if((tires.getFrontLeft()< 32 || tires.getFrontLeft() > 36)){
            this.creteAlertBasedOnParameter(Priority.Low, readings1.getVin(),
                    "Check Front Left tire pressure");
        }
        if (readings1.isEngineCoolantLow() == true){
            this.creteAlertBasedOnParameter(Priority.Low, readings1.getVin(),
                    " Check your engine coolant");
        }
        if(readings1.isCheckEngineLightOn() == true){
            this.creteAlertBasedOnParameter(Priority.Low, readings1.getVin(),
                    " Check your engine ");
        }
    }
}
