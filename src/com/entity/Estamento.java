package com.entity;

import java.io.Serializable;

public class Estamento implements Serializable{

		private static final long serialVersionUID = 1L;
		
		private int id;
		private int eleccion;
		private String descripcion;
		

	
		public Estamento(int id) {
			this.id = id;
		}

		
		public Estamento() {
		}

		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public int getEleccion() {
			return eleccion;
		}


		public void setEleccion(int eleccion) {
			this.eleccion = eleccion;
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
