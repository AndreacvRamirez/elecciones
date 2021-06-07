package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;
import com.entity.*;


@WebServlet(name = "voto", urlPatterns = { "/voto" })
public class VotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public VotoServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if (accion != null) {
			try {
				switch (accion) {
				case "votar":
					voto(request, response);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void voto(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String candidato = request.getParameter("candidato");
		String documento = request.getParameter("usuario");
		String eleccion = request.getParameter("eleccion");
		if(candidato != null && documento != null) {
			int idEleccion = eleccion == null ? 1 : Integer.parseInt(eleccion);
			VotanteDao dao = new VotanteDao();
			Votante v = dao.find(new Votante(-1, documento));
			CandidatoDao cDao = new CandidatoDao();
			Candidato c = cDao.find(candidato);
			if(c != null && v != null) {
				Voto voto = new Voto();
				voto.setCandidato(c.getId());
				voto.setVotante(v.getId());
				VotoDao vDao = new VotoDao();
				try {
					vDao.insert(voto);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				request.setAttribute("votante", null);
				request.setAttribute("votanteValidado", false);
				request.setAttribute("eleccionID", idEleccion);
				request.setAttribute("listCandidato", null);
				response.sendRedirect("votante?accion=showValidarVotante");
			}
			System.out.print("FIN DEL REGISTRO VOTO");
			
		}
	}

}
