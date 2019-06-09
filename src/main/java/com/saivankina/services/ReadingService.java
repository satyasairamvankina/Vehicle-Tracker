package com.saivankina.services;


import com.saivankina.entity.Readings;

import java.util.List;

public interface ReadingService {
    List<Readings> findAll();
    Readings findById(String id);
    Readings createReading(Readings reading);// use put
    Readings updateReading(String id, Readings reading);
    void deleteReading(String id);
}
