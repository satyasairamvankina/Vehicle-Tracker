package com.saivankina.Repository;

import com.saivankina.entity.Tires;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiresRepository extends CrudRepository<Tires,String > {
}
