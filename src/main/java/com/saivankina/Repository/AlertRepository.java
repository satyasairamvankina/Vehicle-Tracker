package com.saivankina.Repository;

import com.saivankina.Priority;
import com.saivankina.entity.Alert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Repository
public interface AlertRepository extends CrudRepository<Alert,String> {
    Alert findByVin(String vin);

//    DATEDIFF(hour,?2, alt.createdAt) < = ?1
    @Query(value = "SELECT * FROM Alert alt WHERE  alt.priority = ?1",nativeQuery = true)
    List<Alert> fetchAlertByTime(Priority priority);
//    @Param("hours") int hours ,@Param("CurrentTime")  Date CurrentTime,

    @Query(value = "SELECT * FROM Alert alt WHERE alt.vin = ?1",nativeQuery = true)
     List<Alert> fetchAllalertsOfVehicle(String vin);
}
