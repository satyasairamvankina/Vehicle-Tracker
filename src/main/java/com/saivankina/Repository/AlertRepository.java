package com.saivankina.Repository;

import com.saivankina.entity.Alert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Repository
public interface AlertRepository extends CrudRepository<Alert,String> {
    Alert findByVin(String vin);

    @Query(value = "SELECT * FROM Alert alt WHERE  DATEDIFF(hour,?2, alt.createdAt) < = ?1",nativeQuery = true)
    List<Alert> fetchAlertByTime(int hours ,Date CurrentTime);


    @Query(value = "SELECT * FROM Alert alt WHERE alt.vin = ?1",nativeQuery = true)
     List<Alert> fetchAllalertsOfVehivle(String vin);
}
