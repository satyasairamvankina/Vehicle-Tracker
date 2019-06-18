package com.saivankina.controller;

import com.saivankina.entity.Alert;
import com.saivankina.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping
    public List<Alert> findALL(){
       return alertService.findAll();
    }

    @GetMapping("{time}")
    public List<Alert> findAlertByTime(@RequestParam("time") int time){
       return alertService.fetchAlertByTime(time);
    }


}
