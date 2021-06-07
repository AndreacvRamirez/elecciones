package com.entity;
import java.io.Serializable;

public class TipoDocumento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String descripcion;
	
	public TipoDocumento(int id) {
		super();
		this.id = id;
	}

	public TipoDocumento() {
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
