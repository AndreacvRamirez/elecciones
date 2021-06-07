package com.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.entity.*;
import com.util.*;

public class VotoDao implements Serializable {
	private static final long serialVersionUID = 1L;

	private QueryGeneric<Voto> query;
	
	private LocalDateTime localDate;
	private String date;
	private DateTimeFormatter format;

	public VotoDao() {
		this.format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	}

	public List<Voto> list(String id) {
		this.query = new QueryGeneric<Voto>();
		this.query.setQuery("SELECT v FROM voto AS v JOIN estamento AS e ON(v.estamento == e.id)");
		this.query.setList(new ArrayList<Voto>());
		try {
			this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
			this.query.setRs(this.query.getPs().executeQuery());
			while (this.query.getRs().next()) {
				this.query.setEntity(new Voto());
				this.query.getEntity().setId(this.query.getRs().getInt(1));
				this.query.getEntity().setFechacreacion(this.query.getRs().getString(2));
				this.query.getEntity().setFechavoto(this.query.getRs().getString(3));
				this.query.getEntity().setUuid(this.query.getRs().getString(4));
				this.query.getEntity().setEnlace(this.query.getRs().getString(5));
				this.query.getEntity().setEstamento(this.query.getRs().getInt(6));
				this.query.getEntity().setCandidato(this.query.getRs().getInt(7));
				this.query.getEntity().setVotante(this.query.getRs().getInt(8));
				this.query.getList().add(this.query.getEntity());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// ConnectionGeneric.close();
		}
		return this.query.getList();
	}

	public Voto find(int id) {
		this.query = new QueryGeneric<Voto>();
		this.query.setQuery("SELECT * FROM voto WHERE id = " + id);
		try {
			this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
			this.query.setRs(this.query.getPs().executeQuery());
			while (this.query.getRs().next()) {
				this.query.setEntity(new Voto());
				this.query.getEntity().setId(this.query.getRs().getInt(1));
				this.query.getEntity().setFechacreacion(this.query.getRs().getString(2));
				this.query.getEntity().setFechavoto(this.query.getRs().getString(3));
				this.query.getEntity().setUuid(this.query.getRs().getString(4));
				this.query.getEntity().setEnlace(this.query.getRs().getString(5));
				this.query.getEntity().setEstamento(this.query.getRs().getInt(6));
				this.query.getEntity().setCandidato(this.query.getRs().getInt(7));
				this.query.getEntity().setVotante(this.query.getRs().getInt(8));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// ConnectionGeneric.close();
		}
		return this.query.getEntity();
	}
	
	public Voto findVotante(int id) {
		this.query = new QueryGeneric<Voto>();
		this.query.setQuery("SELECT * FROM voto WHERE votante = "+id);
		try {
			this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
			this.query.setRs(this.query.getPs().executeQuery());
			while (this.query.getRs().next()) {
				this.query.setEntity(new Voto());
				this.query.getEntity().setId(this.query.getRs().getInt(1));
				this.query.getEntity().setFechacreacion(this.query.getRs().getString(2));
				this.query.getEntity().setFechavoto(this.query.getRs().getString(3));
				this.query.getEntity().setUuid(this.query.getRs().getString(4));
				this.query.getEntity().setEnlace(this.query.getRs().getString(5));
				this.query.getEntity().setEstamento(this.query.getRs().getInt(6));
				this.query.getEntity().setCandidato(this.query.getRs().getInt(7));
				this.query.getEntity().setVotante(this.query.getRs().getInt(8));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// ConnectionGeneric.close();
		}
		return this.query.getEntity();
	}


	public void insert(Voto t) throws ParseException {
		if (t != null) {
			this.query = new QueryGeneric<Voto>();
			this.query.setQuery(
					"INSERT INTO voto(fechacreacion,fechavoto,uuid,enlace,estamento,candidato,votante) VALUES (?,?,?,?,?,?,?)");
			try {
				this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
				this.query.getPs().setDate(1,  convertDate(currentDateTime()));
				this.query.getPs().setDate(2,  convertDate(currentDateTime()));
				this.query.getPs().setString(3, t.getUuid());
				this.query.getPs().setString(4, t.getEnlace());
				this.query.getPs().setInt(5, t.getEstamento());
				this.query.getPs().setInt(6, t.getCandidato());
				this.query.getPs().setInt(7, t.getVotante());
				this.query.getPs().executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				// ConnectionGeneric.close();
			}
		}
	}
	
	public java.sql.Date convertDate(String date) throws ParseException {
		 SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	     return new java.sql.Date(dt.parse(date).getTime());
	}
	
	public String currentDateTime() {
		this.format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		localDate = LocalDateTime.now();
		this.date = localDate.format(this.format);
		return date;
	}
}
