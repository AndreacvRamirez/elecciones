package com.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.*;
import com.util.*;

public class EstamentoDao implements Serializable{
	private static final long serialVersionUID = 1L;

	private QueryGeneric<Estamento> query;

	public EstamentoDao() {
	}

	public List<Estamento> list() {
		this.query = new QueryGeneric<Estamento>();
		this.query.setQuery("SELECT * FROM estamento GROUP BY descripcion");
		this.query.setList(new ArrayList<Estamento>());
		try {
			this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
			this.query.setRs(this.query.getPs().executeQuery());
			while (this.query.getRs().next()) {
				this.query.setEntity(new Estamento());
				this.query.getEntity().setId(this.query.getRs().getInt(1));
				this.query.getEntity().setEleccion(this.query.getRs().getInt(2));
				this.query.getEntity().setDescripcion(this.query.getRs().getString(3));
				this.query.getList().add(this.query.getEntity());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			// ConnectionGeneric.close();
		}
		return this.query.getList();
	}
}
