package com.cibertec.demo.interfaceService;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.cibertec.demo.modelo.Vacaciones;

@Service
public interface IVacacionesService {
	
	//Declaracion de los Metodos del CRUD
	public List<Vacaciones> listar();
	public Optional<Vacaciones> listarId(int id);
	public int guardar(Vacaciones v);
	public void eliminar(int id);
	
	

}
