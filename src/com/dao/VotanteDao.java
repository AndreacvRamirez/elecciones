package com.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.*;
import com.util.*;

public class VotanteDao implements Serializable {
	private static final long serialVersionUID = 1L;

	private QueryGeneric<Votante> query;

	public VotanteDao() {
	}

	public List<Votante> list() {
		this.query = new QueryGeneric<Votante>();
		this.query.setQuery("SELECT * FROM votante");
		this.query.setList(new ArrayList<Votante>());
		try {
			this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
			this.query.setRs(this.query.getPs().executeQuery());
			while (this.query.getRs().next()) {
				this.query.setEntity(new Votante());
				this.query.getEntity().setId(this.query.getRs().getInt(1));
				this.query.getEntity().setNombre(this.query.getRs().getString(2));
				this.query.getEntity().setEmail(this.query.getRs().getString(3));
				this.query.getEntity().setDocumento(this.query.getRs().getString(4));
				this.query.getEntity().setTipo_documento(this.query.getRs().getInt(5));
				this.query.getEntity().setEleccion(this.query.getRs().getInt(6));
				this.query.getList().add(this.query.getEntity());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// ConnectionGeneric.close();
		}
		return this.query.getList();
	}

	public List<Votante> list(String id) {
		this.query = new QueryGeneric<Votante>();
		this.query.setQuery("SELECT * FROM votante WHERE eleccion = " + id);
		this.query.setList(new ArrayList<Votante>());
		try {
			this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
			this.query.setRs(this.query.getPs().executeQuery());
			while (this.query.getRs().next()) {
				this.query.setEntity(new Votante());
				this.query.getEntity().setId(this.query.getRs().getInt(1));
				this.query.getEntity().setNombre(this.query.getRs().getString(2));
				this.query.getEntity().setEmail(this.query.getRs().getString(3));
				this.query.getEntity().setDocumento(this.query.getRs().getString(4));
				this.query.getEntity().setTipo_documento(this.query.getRs().getInt(5));
				this.query.getEntity().setEleccion(this.query.getRs().getInt(6));
				this.query.getList().add(this.query.getEntity());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// ConnectionGeneric.close();
		}
		return this.query.getList();
	}

	public Votante find(int id) {
		this.query = new QueryGeneric<Votante>();
		this.query.setQuery("SELECT * FROM votante WHERE id = " + id);
		try {
			this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
			this.query.setRs(this.query.getPs().executeQuery());
			while (this.query.getRs().next()) {
				this.query.setEntity(new Votante());
				this.query.getEntity().setId(this.query.getRs().getInt(1));
				this.query.getEntity().setNombre(this.query.getRs().getString(2));
				this.query.getEntity().setEmail(this.query.getRs().getString(3));
				this.query.getEntity().setDocumento(this.query.getRs().getString(4));
				this.query.getEntity().setTipo_documento(this.query.getRs().getInt(5));
				this.query.getEntity().setEleccion(this.query.getRs().getInt(6));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// ConnectionGeneric.close();
		}
		return this.query.getEntity();
	}
	
	public Votante find(Votante id) {
		this.query = new QueryGeneric<Votante>();
		this.query.setQuery("SELECT * FROM votante WHERE documento = '" + id.getDocumento() + "'");
		try {
			this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
			this.query.setRs(this.query.getPs().executeQuery());
			while (this.query.getRs().next()) {
				this.query.setEntity(new Votante());
				this.query.getEntity().setId(this.query.getRs().getInt(1));
				this.query.getEntity().setNombre(this.query.getRs().getString(2));
				this.query.getEntity().setEmail(this.query.getRs().getString(3));
				this.query.getEntity().setDocumento(this.query.getRs().getString(4));
				this.query.getEntity().setTipo_documento(this.query.getRs().getInt(5));
				this.query.getEntity().setEleccion(this.query.getRs().getInt(6));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// ConnectionGeneric.close();
		}
		return this.query.getEntity();
	}


	public void insert(Votante t) {
		if (t != null) {
			this.query = new QueryGeneric<Votante>();
			this.query.setQuery(
					"INSERT INTO votante(nombre,email,documento,tipodocumento,eleccion) VALUES (?,?,?,?,?)");
			try {
				this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
				this.query.getPs().setString(1, t.getNombre());
				this.query.getPs().setString(2, t.getEmail());
				this.query.getPs().setString(3, t.getDocumento());
				this.query.getPs().setInt(4, t.getTipo_documento());
				this.query.getPs().setInt(5, t.getEleccion());
				this.query.getPs().executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				// ConnectionGeneric.close();
			}
		}
	}

	public void update(Votante t) {
		if (t != null) {
			this.query = new QueryGeneric<Votante>();
			this.query.setQuery(
					"UPDATE votante SET nombre = ?, email = ?, documento = ?, tipodocumento = ?, eleccion = ?  WHERE id = "
							+ t.getId() + "");
			try {
				this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
				this.query.getPs().setString(1, t.getNombre());
				this.query.getPs().setString(2, t.getEmail());
				this.query.getPs().setString(3, t.getDocumento());
				this.query.getPs().setInt(4, t.getTipo_documento());
				this.query.getPs().setInt(5, t.getEleccion());
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
			this.query = new QueryGeneric<Votante>();
			query.setQuery("DELETE FROM votante WHERE id = " + id + "");
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
