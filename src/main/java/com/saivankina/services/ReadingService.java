package com.saivankina.services;


import com.saivankina.entity.Readings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReadingService {
    List<Readings> findAll();
    Readings findById(String id);
    Readings createReading(Readings reading);// use put
    Readings updateReading(String id, Readings reading);
    void deleteReading(String id);
}
