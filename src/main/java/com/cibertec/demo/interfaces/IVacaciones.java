package com.cibertec.demo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cibertec.demo.modelo.Vacaciones;

@Repository
public interface IVacaciones extends CrudRepository<Vacaciones, Integer>{

}
