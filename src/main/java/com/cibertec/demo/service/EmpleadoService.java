package com.cibertec.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibertec.demo.interfaceService.IEmpleadoService;
import com.cibertec.demo.interfaces.IEmpleado;
import com.cibertec.demo.modelo.Empleado;

@Service
public class EmpleadoService implements IEmpleadoService{

	@Autowired
	public IEmpleado data;	
	
	@Override
	public List<Empleado> listar() {
		return (List<Empleado>)data.findAll();
	}

	@Override
	public Optional<Empleado> listarId(int id) {
		return data.findById(id);
	}

	@Override
	public int guardar(Empleado e) {
		int res=0;
		Empleado empleado = data.save(e);
		if(empleado.equals(null)) {
			res=1;
		}
		return res;
	}
	@Override
	public void eliminar(int id) {
		data.deleteById(id);	
		
	}

	@Override
	public int guardar1(Empleado e) {
		int rest=0;
		Empleado empleado = data.save(e);
		if(empleado.equals(null)) {
			rest=1;
		}
		return rest;
	}
	
	
	

}
