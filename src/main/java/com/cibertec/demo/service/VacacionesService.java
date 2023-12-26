package com.cibertec.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.demo.interfaceService.IVacacionesService;
import com.cibertec.demo.interfaces.IVacaciones;
import com.cibertec.demo.modelo.Vacaciones;

@Service
public class VacacionesService implements IVacacionesService{
	
	@Autowired
	public IVacaciones data;

	@Override
	public List<Vacaciones> listar() {
		return (List<Vacaciones>)data.findAll();
	}

	@Override
	public Optional<Vacaciones> listarId(int id) {
		return data.findById(id);
	}

	@Override
	public int guardar(Vacaciones e) {
		int res=0;
		Vacaciones vacaciones = data.save(e);
		if(vacaciones.equals(null)) {
			res=1;
		}
		return res;
	}
	@Override
	public void eliminar(int id) {
		data.deleteById(id);	
		
	}


}
