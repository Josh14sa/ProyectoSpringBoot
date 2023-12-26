package com.cibertec.demo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cibertec.demo.modelo.Empleado;


@Repository
public interface IEmpleado extends CrudRepository<Empleado, Integer>{


}
