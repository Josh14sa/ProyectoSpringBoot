package com.cibertec.demo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cibertec.demo.modelo.Login;

@Repository
public interface ILogin extends CrudRepository<Login, Integer>{

}
