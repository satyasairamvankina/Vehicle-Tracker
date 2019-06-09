package com.saivankina.services;

import com.saivankina.Location;
import com.saivankina.Priority;
import com.saivankina.entity.Alert;
import com.saivankina.entity.Readings;
import com.saivankina.entity.Tires;
import com.saivankina.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface AlertService {
    Alert findById(String vin);
    List<Alert> findAll();
    Alert createAlert(Alert alert);
    Alert creteAlertBasedOnParameter(Priority priority, String vin, String alertDescription);
    Alert findByVin(String vin);
    List<Alert> fetchAlertByTime(int hours);
    List<Alert> fetchAllalertsOfVehivle(String vin);
    List<Location> fetchLocationBasedOnTime(int time);
    void checkAlerts(Readings readings, Tires tire, Vehicle vehicle);

}
