package com.saivankina.controller;



import com.saivankina.Repository.ReadingsRepository;
import com.saivankina.Repository.TiresRepository;
import com.saivankina.entity.Readings;
import com.saivankina.services.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/readings")
public class ReadingController {

    @Autowired
    private ReadingsRepository readingsRepository;
    @Autowired
    private ReadingService readingService;
    @Autowired
    private TiresRepository tiresrepo;

//    public ReadingController(readingsRepository readingsRepository,ReadingService readingService,tiresRepository tiresrepo){
//        this.readingsRepository = readingsRepository;
//        this.tiresrepo = tiresrepo;
//        this.readingService = readingService;
//    }

    @PostMapping
    public Readings create(@RequestBody Readings readings){
        System.out.println("reading are "+readings);
        Readings read = readingService.createReading(readings);
            return read;
    }




}
