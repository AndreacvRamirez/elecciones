package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.*;
import com.entity.*;

/**
 * Servlet implementation class CandidatoServlet
 */
@WebServlet(name = "candidato", urlPatterns = { "/candidato" })
public class CandidatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CandidatoDao dao;

	public CandidatoServlet() {
		super();
		dao = new CandidatoDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if (accion != null) {
			try {
				switch (accion) {
				case "newCandidato":
					showViewFormCandiato(request, response);
					break;
				case "insertCandidato":
					insertCandiato(request, response);
					break;
				case "deleteCandidato":
					deleteCandiato(request, response);
					break;
				case "editCandidato":
					showEditCandiato(request, response);
					break;
				case "updateCandidato":
					updateCandiato(request, response);
					break;
				case "listCandidato":
					listCandiato(request, response);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if (accion != null) {
			try {
				switch (accion) {
				case "insertCandidato":
					insertCandiato(request, response);
					break;
				case "deleteCandidato":
					deleteCandiato(request, response);
					break;
				case "editCandidato":
					showEditCandiato(request, response);
					break;
				case "updateCandidato":
					updateCandiato(request, response);
					break;
				case "listCandidato":
					listCandiato(request, response);
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

	private void listCandiato(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String eleccion = request.getParameter("eleccion");
		if (dao == null) {
			init();
		}
		List<Candidato> list = (eleccion != null) ? dao.list(eleccion) : dao.list();
		request.setAttribute("listCandidato", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("candidatoList.jsp");
		dispatcher.forward(request, response);
	}

	private void showViewFormCandiato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EleccionDao eDao = new EleccionDao();
		List<Eleccion> list = eDao.list();
		request.setAttribute("listEleccion", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("candidato.jsp");
		dispatcher.forward(request, response);
	}

	private void insertCandiato(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String id = request.getParameter("id");
		String documento = request.getParameter("documento");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String eleccion = request.getParameter("eleccion");
		String numero = request.getParameter("numero");

		Candidato c = new Candidato(Integer.parseInt(id), documento, nombre, apellido, Integer.parseInt(eleccion),
				Integer.parseInt(numero));
		if (dao == null) {
			init();
		}
		dao.insert(c);
		response.sendRedirect("candidato?accion=listCandidato");
	}

	private void updateCandiato(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String id = request.getParameter("id");
		String documento = request.getParameter("documento");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String eleccion = request.getParameter("eleccion");
		String numero = request.getParameter("numero");
		Candidato c = new Candidato(Integer.parseInt(id), documento, nombre, apellido, Integer.parseInt(eleccion),
				Integer.parseInt(numero));
		if (dao == null) {
			this.init();
		}
		dao.update(c);
		response.sendRedirect("candidato?accion=listCandidato");
	}

	private void deleteCandiato(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String id = request.getParameter("id");
		if (dao == null) {
			this.init();
		}
		dao.delete(Integer.parseInt(id));
		response.sendRedirect("candidato?accion=listCandidato");
	}

	private void showEditCandiato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String id = request.getParameter("id");
		if (dao == null) {
			this.init();
		}
		Candidato c = dao.find(id);
		EleccionDao eDao = new EleccionDao();
		List<Eleccion> list = eDao.list();
		request.setAttribute("listEleccion", list);
		request.setAttribute("candidato", c);
		RequestDispatcher dispatcher = request.getRequestDispatcher("candidato.jsp");
		dispatcher.forward(request, response);
	}
}
