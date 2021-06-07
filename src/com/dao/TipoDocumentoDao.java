package com.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.*;
import com.util.*;

public class TipoDocumentoDao implements Serializable {
	private static final long serialVersionUID = 1L;

	private QueryGeneric<TipoDocumento> query;

	public TipoDocumentoDao() {
	}

	public List<TipoDocumento> list() {
		this.query = new QueryGeneric<TipoDocumento>();
		this.query.setQuery("SELECT * FROM tipodocumento");
		this.query.setList(new ArrayList<TipoDocumento>());
		try {
			this.query.setPs(ConnectionGeneric.getC().prepareStatement(this.query.getQuery()));
			this.query.setRs(this.query.getPs().executeQuery());
			while (this.query.getRs().next()) {
				this.query.setEntity(new TipoDocumento());
				this.query.getEntity().setId(this.query.getRs().getInt(1));
				this.query.getEntity().setDescripcion(this.query.getRs().getString(2));
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
