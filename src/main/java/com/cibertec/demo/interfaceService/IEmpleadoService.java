package com.cibertec.demo.interfaceService;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.cibertec.demo.modelo.Empleado;

@Service
public interface IEmpleadoService {
	
	//Declaracion de los Metodos del CRUD
			public List<Empleado> listar();
			public Optional<Empleado> listarId(int id);
			public int guardar(Empleado e);
			public void eliminar(int id);
			public int guardar1(Empleado e);

}
