package com.cibertec.demo.controler;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.demo.interfaceService.IEmpleadoService;
import com.cibertec.demo.interfaceService.IVacacionesService;
import com.cibertec.demo.modelo.Empleado;
import com.cibertec.demo.modelo.Login;
import com.cibertec.demo.modelo.Vacaciones;
import com.cibertec.demo.reportes.PersonasExporterPDF;
import com.cibertec.demo.reportes.PersonasExporterPDF;
import com.cibertec.demo.reportes.PersonasExporteExcel;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping
public class Controlador {

	@Autowired
	private IEmpleadoService service;
	@Autowired
	private IVacacionesService services;
	
	
	
//Pagina principal--------------------------
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Empleado> empleados = service.listar();
		model.addAttribute("empleados", empleados);
		return "indexEmpleado";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		Optional<Empleado> empleado = service.listarId(id);
		model.addAttribute("empleado", empleado);
		return "actualizar";
	}
	

	@GetMapping("/eliminar/{id}")
	public String eliminar(Model model, @PathVariable int id) {
		service.eliminar(id);
		return "redirect:/listar";
	}

	//Registro Empleado--------------------
	@GetMapping("/new")
	public String agregar(Model model) {
		model.addAttribute("empleado", new Empleado());
		return "crear";
	}
	
	@GetMapping("/new2")
	public String agregar2(Model model) {
		List<Vacaciones> vacaciones = services.listar();
		
		model.addAttribute("Vacaciones", new Vacaciones());
		return "registroVacaciones";
	}
	
	
	//Registro de vacaciones---------------
	@GetMapping("/new3")
	public String agregar3(Model model) {
		model.addAttribute("vacaciones", new Vacaciones());
		return "crearVacaciones";
	}
	
	
	@PostMapping("/save")
	public String guardar(@Validated Empleado e, Model model) {
		e.setFechaRegistro(LocalDateTime.now());
		service.guardar(e);
		return "redirect:/listar";
	}
	
	@PostMapping("/save2")
	public String guardar(@Validated Vacaciones v, Model model) {
		v.setFechaRegistro(LocalDateTime.now());
		v.setFechaInicio(LocalDate.now()); // Si v.getFechaInicio() devuelve un String
	    v.setFechaFin(LocalDate.now()); // Si v.getFechaFin() devuelve un String
		services.guardar(v);
		return "redirect:/new2";
	}
	
	//Acceso al login
	@GetMapping("/login")
	public String showLoginPage() {
		return "login"; // Esto se refiere al nombre del archivo HTML sin la extensión
	}

	@PostMapping("/update")
	public String actualizar(@Validated Empleado e, Model model) {
		service.guardar(e);
		return "redirect:/listar";
	}
	
	@PostMapping("/update1")
	public String actualizarVacaciones(@Validated Vacaciones v, Model model) {

		services.guardar(v);
		return "redirect:/new2";
	}
	
	@GetMapping("/exportarPDF")
	public void exportarListadoDePersonasEnPDF(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("aplicattion/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM_dd_HH:mm:ss");
		String fechaActual= dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment;filename = Empleados"+ fechaActual + ".pdf";
		
		response.setHeader(cabecera, valor);
		List<Empleado> personas = service.listar();
		
		PersonasExporterPDF exporter = new PersonasExporterPDF(personas);
		exporter.exportar(response);
	}
	
	@GetMapping("/exportarExcel")
	public void exportarListadoDePersonasEnExcel(HttpServletResponse response) throws IOException {
		response.setContentType("aplicattion/octet-stream");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM_dd_HH:mm:ss");
		String fechaActual= dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment;filename = Empleados"+ fechaActual + ".xlsx";
		
		response.setHeader(cabecera, valor);
		List<Empleado> personas = service.listar();
		
		PersonasExporteExcel exporter = new PersonasExporteExcel(personas);
		exporter.exportar(response);
	}
	
}
