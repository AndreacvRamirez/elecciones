package com.entity;
import java.io.Serializable;

public class Voto implements Serializable{

private static final long serialVersionUID = 1L;
	
	private int id;
	private String fechacreacion;
	private String fechavoto;
	private String uuid;
	private String enlace;
	private int estamento;
	private int candidato;
	private int votante;
	
	
	public Voto(int id) {
		this.id = id;
	}
	
	public Voto() {
	}
	

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFechacreacion() {
		return fechacreacion;
	}
	public void setFechacreacion(String fechacreacion) {
		this.fechacreacion = fechacreacion;
	}
	public String getFechavoto() {
		return fechavoto;
	}
	public void setFechavoto(String fechavoto) {
		this.fechavoto = fechavoto;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public int getEstamento() {
		return estamento;
	}
	public void setEstamento(int estamento) {
		this.estamento = estamento;
	}
	public int getCandidato() {
		return candidato;
	}
	public void setCandidato(int candidato) {
		this.candidato = candidato;
	}
	public int getVotante() {
		return votante;
	}
	public void setVotante(int votante) {
		this.votante = votante;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
