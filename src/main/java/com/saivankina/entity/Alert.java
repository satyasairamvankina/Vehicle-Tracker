package com.saivankina.entity;

import com.saivankina.Priority;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
public class Alert {

    @Id
    private String alertId;

    private String vin;
    private Priority priority;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = true)
//    @CreatedDate
    @LastModifiedDate
    private Date createdAt;

    private String alertDescription;

    Date date= new Date();
    long time = date.getTime();
    Date curretTimeStamp = new Timestamp(time);


    public Alert() {
        this.alertId = UUID.randomUUID().toString();
//        this.createdAt = curretTimeStamp;
        this.createdAt = new Date();
    }


    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAlertDescription() {
        return alertDescription;
    }

    public void setAlertDescription(String alertDescription) {
        this.alertDescription = alertDescription;
    }
}
