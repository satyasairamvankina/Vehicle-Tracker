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

    @GetMapping("/{time}")
    public List<Alert> findAlertByTime(@RequestParam("time") int time){
        System.out.println("Alerts time"+time);
       return alertService.fetchAlertByTime(time);
//        Employee employee = repository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    //        @PutMapping("/employees/{id}")
//        Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
//
//            return repository.findById(id)
//                    .map(employee -> {
//                        employee.setName(newEmployee.getName());
//                        employee.setRole(newEmployee.getRole());
//                        return repository.save(employee);
//                    })
//                    .orElseGet(() -> {
//                        newEmployee.setId(id);
//                        return repository.save(newEmployee);
//                    });
//        }


}
