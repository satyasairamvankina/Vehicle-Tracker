package com.saivankina.entity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Embeddable
@Entity
public class Tires {

    @Id
    private  String tiresId;
    private Integer frontLeft;
    private Integer  frontRight;
    private Integer  rearLeft;
    private Integer rearRight;

    public Tires() {

        this.tiresId = UUID.randomUUID().toString();
    }

    public Tires(Integer frontLeft, Integer frontRight, Integer rearLeft, Integer rearRight) {
        this.tiresId = UUID.randomUUID().toString();
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
    }


    public String getTiresId() {
        return tiresId;
    }

    public void setTiresId(String tiresId) {
        this.tiresId = tiresId;
    }

    public Integer getFrontLeft() {
        return frontLeft;
    }

    public void setFrontLeft(Integer frontLeft) {
        this.frontLeft = frontLeft;
    }

    public Integer getFrontRight() {
        return frontRight;
    }

    public void setFrontRight(Integer frontRight) {
        this.frontRight = frontRight;
    }

    public Integer getRearLeft() {
        return rearLeft;
    }

    public void setRearLeft(Integer rearLeft) {
        this.rearLeft = rearLeft;
    }

    public Integer getRearRight() {
        return rearRight;
    }

    public void setRearRight(Integer rearRight) {
        this.rearRight = rearRight;
    }

    @Override
    public String toString() {
        return "Tires{" +
                "tiresId='" + tiresId + '\'' +
                ", frontLeft=" + frontLeft +
                ", frontRight=" + frontRight +
                ", rearLeft=" + rearLeft +
                ", rearRight=" + rearRight +
                '}';
    }
}
