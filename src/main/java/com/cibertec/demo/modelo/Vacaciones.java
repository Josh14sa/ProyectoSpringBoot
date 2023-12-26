package com.cibertec.demo.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="vacaciones")
public class Vacaciones {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate FechaInicio;
	private LocalDate FechaFin;
	private String motivo;
	private String estado="Pendiente";
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime FechaRegistro;
	
	
	public Vacaciones() {
		
	}

	public Vacaciones(int id, LocalDate fechaInicio, LocalDate fechaFin, String motivo,String estado, LocalDateTime fechaRegistro) {
		super();
		this.id = id;
		FechaInicio = fechaInicio;
		FechaFin = fechaFin;
		this.motivo = motivo;
		this.estado=estado;
		this.FechaRegistro=fechaRegistro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFechaInicio() {
		return FechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		FechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return FechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		FechaFin = fechaFin;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public LocalDateTime getFechaRegistro() {
		return FechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		FechaRegistro = fechaRegistro;
	}
	

}
