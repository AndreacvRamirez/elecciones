package com.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.*;
import com.util.*;

public class EleccionDao implements Serializable {

	private static final long serialVersionUID = 1L;

	private QueryGeneric<Eleccion> query;
	
	public EleccionDao() {
	}
	
	public List<Eleccion> list() {
		this.query = new QueryGeneric<Eleccion>();
		this.query.setQuery("SELECT * FROM eleccion");
		this.query.setList(new ArrayList<Eleccion>());
		try {
			this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
			this.query.setRs(this.query.getPs().executeQuery());
			while (this.query.getRs().next()) {
				this.query.setEntity(new Eleccion());
				this.query.getEntity().setId(this.query.getRs().getInt(1));
				this.query.getEntity().setNombre(this.query.getRs().getString(2));
				this.query.getEntity().setFecha_inicio(this.query.getRs().getString(3));
				this.query.getEntity().setFecha_fin(this.query.getRs().getString(4));
				this.query.getEntity().setCargo(this.query.getRs().getString(5));
				this.query.getList().add(this.query.getEntity());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// ConnectionGeneric.close();
		}
		return this.query.getList();
	}
	
	public Eleccion find(String id) {
		this.query = new QueryGeneric<Eleccion>();
		this.query.setQuery("SELECT * FROM eleccion WHERE id = "+id);
		try {
			this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
			this.query.setRs(this.query.getPs().executeQuery());
			while (this.query.getRs().next()) {
				this.query.setEntity(new Eleccion());
				this.query.getEntity().setId(this.query.getRs().getInt(1));
				this.query.getEntity().setNombre(this.query.getRs().getString(2));
				this.query.getEntity().setFecha_inicio(this.query.getRs().getString(3));
				this.query.getEntity().setFecha_fin(this.query.getRs().getString(4));
				this.query.getEntity().setCargo(this.query.getRs().getString(5));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// ConnectionGeneric.close();
		}
		return this.query.getEntity();
	}
	

	public void insert(Eleccion t) {
		if (t != null) {
			this.query = new QueryGeneric<Eleccion>();
			this.query.setQuery(
					"INSERT INTO eleccion(id,nombre,fechainicio,fechafin,cargo) VALUES (?,?,?,?,?)");
			try {
				this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
				this.query.getPs().setInt(1, t.getId());
				this.query.getPs().setString(2, t.getNombre());
				this.query.getPs().setString(3, t.getFecha_inicio());
				this.query.getPs().setString(4, t.getFecha_fin());
				this.query.getPs().setString(5, t.getCargo());
				this.query.getPs().executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				// ConnectionGeneric.close();
			}
		}
	}

	public void update(Eleccion t) {
		if (t != null) {
			this.query = new QueryGeneric<Eleccion>();
			this.query.setQuery(
					"UPDATE eleccion SET nombre = ?, fechainicio = ?, fechafin = ?, cargo = ? WHERE id = "
							+ t.getId() + "");
			try {
				this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
				this.query.getPs().setString(1, t.getNombre());
				this.query.getPs().setString(2, t.getFecha_inicio());
				this.query.getPs().setString(3, t.getFecha_fin());
				this.query.getPs().setString(4, t.getCargo());
				this.query.getPs().executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				// ConnectionGeneric.close();
			}
		}
	}

	public boolean delete(int id) {
		if (id > 0) {
			this.query = new QueryGeneric<Eleccion>();
			query.setQuery("DELETE FROM eleccion WHERE id = " + id + "");
			try {
				this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
				if (this.query.getPs().executeUpdate() > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				ConnectionGeneric.close();
			}
		}
		return false;
	}
}
