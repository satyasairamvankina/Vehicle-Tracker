package com.saivankina.Repository;

import com.saivankina.entity.Readings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReadingsRepository extends CrudRepository<Readings,String> {
}
