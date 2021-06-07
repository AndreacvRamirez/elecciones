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

@WebServlet(name = "votante", urlPatterns = { "/votante" })
public class VotanteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private VotanteDao dao;

	public VotanteServlet() {

		dao = new VotanteDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		//System.out.println("ENTROOOOO GET");
		if (accion != null) {
			try {
				switch (accion) {
				case "showValidarVotante":
					showValidarVotante(request, response);
					break;
				case "newVotante":
					showViewFormVotante(request, response);
					break;
				case "insertVotante":
					System.out.println("ENTROOOOO");
					insertVotante(request, response);
					break;
				case "deleteVotante":
					deleteVotante(request, response);
					break;
				case "updateVotante":
					updateVotante(request, response);
					break;
				case "showUpdateVotante":
					showUpdateVotante(request, response);
					break;
				case "listVotante":
					listVotante(request, response);
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
		System.out.println("ENTROOOOO POST");
		if (accion != null) {
			try {
				switch (accion) {
				case "validarVotante":
					validarVotante(request, response);
					break;
				case "insertVotante":
					System.out.println("ENTROOOOO POST INSERT");
					insertVotante(request, response);
					break;
				case "deleteVotante":
					deleteVotante(request, response);
					break;
				case "updateVotante":
					updateVotante(request, response);
					break;
				case "showUpdateVotante":
					showUpdateVotante(request, response);
					break;
				case "listVotante":
					listVotante(request, response);
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

	private void showValidarVotante(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setAttribute("tipos", listTipoDocumento());
		RequestDispatcher dispatcher = request.getRequestDispatcher("validacion.jsp");
		dispatcher.forward(request, response);
	}

	private void validarVotante(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		String nombre = request.getParameter("nombre");
		int tipo_documento = Integer.parseInt("" + request.getParameter("tipo"));
		String documento = request.getParameter("documento");
		String email = request.getParameter("email");

		VotanteDao dao = new VotanteDao();
		Votante aux = new Votante(nombre, email, documento, tipo_documento, -1);
		aux = dao.find(aux);

		boolean aux2 = false;
		if (aux != null && aux.getNombre().equalsIgnoreCase(nombre)) {
			if (aux.getEmail().equalsIgnoreCase(email)) {
				if (aux.getTipo_documento() == tipo_documento) {
					aux2 = true;
				}
			}
		}
		if (aux2) {
			VotoDao vDao = new VotoDao();
			Voto vo = vDao.findVotante(aux.getId());
			if (vo == null) {
				request.setAttribute("votanteValidado", aux2);
				String q = request.getParameter("idEleccion");
				request.setAttribute("eleccionID", q == null ? 1 : q);
				request.setAttribute("listCandidato", listEleccion(q));
			}
		}
		request.setAttribute("votante", aux);
		RequestDispatcher dispatcher = request.getRequestDispatcher("votacion.jsp");
		dispatcher.forward(request, response);
	}

	private void showViewFormVotante(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		EstamentoDao dao = new EstamentoDao();
		EleccionDao eDao = new EleccionDao();
		TipoDocumentoDao tDao = new TipoDocumentoDao();
		List<Estamento> e = dao.list();
		List<TipoDocumento> t = tDao.list();
		List<Eleccion> el = eDao.list();
		request.setAttribute("estamentos", e);
		request.setAttribute("tipos", t);
		request.setAttribute("procesos", el);
		RequestDispatcher dispatcher = request.getRequestDispatcher("votante.jsp");
		dispatcher.forward(request, response);
	}

	private void insertVotante(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String nombre = request.getParameter("nombre");
		String estamento = request.getParameter("estamento");
		int tipo_documento = Integer.parseInt("" + request.getParameter("tipo"));
		String documento = request.getParameter("documento");
		String email = request.getParameter("email");
		int eleccion = Integer.parseInt("" + request.getParameter("procesos"));

		VotanteDao dao = new VotanteDao();
		Votante aux = new Votante(nombre, email, documento, tipo_documento, eleccion);
		dao.insert(aux);
		response.sendRedirect("votante?accion=listVotante");
	}

	private void deleteVotante(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("votante"));

		VotanteDao dao = new VotanteDao();
		dao.delete(id);
		response.sendRedirect("votante?accion=listVotante");
	}

	private void updateVotante(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String estamento = request.getParameter("estamento");
		int tipo_documento = Integer.parseInt("" + request.getParameter("tipo"));
		String documento = request.getParameter("documento");
		String email = request.getParameter("email");
		int eleccion = Integer.parseInt("" + request.getParameter("procesos"));

		VotanteDao dao = new VotanteDao();
		Votante aux = new Votante(id, nombre, email, documento, tipo_documento, eleccion);
		dao.update(aux);
		response.sendRedirect("votante?accion=listVotante");
	}

	private void showUpdateVotante(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("votante"));

		VotanteDao dao = new VotanteDao();
		Votante aux = dao.find(id);
		EstamentoDao Edao = new EstamentoDao();
		EleccionDao eDao = new EleccionDao();
		TipoDocumentoDao tDao = new TipoDocumentoDao();
		List<Estamento> e = Edao.list();
		List<TipoDocumento> t = tDao.list();
		List<Eleccion> el = eDao.list();
		request.setAttribute("estamentos", e);
		request.setAttribute("tipos", t);
		request.setAttribute("procesos", el);
		request.setAttribute("votante", aux);
		RequestDispatcher dispatcher = request.getRequestDispatcher("votante.jsp");
		dispatcher.forward(request, response);

	}

	private void listVotante(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String eleccion = request.getParameter("eleccion");
		if (dao == null) {
			init();
		}
		VotanteDao dao = new VotanteDao();
		List<Votante> list = (eleccion != null) ? dao.list(eleccion) : dao.list();
		request.setAttribute("listVotante", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("votanteList.jsp");
		dispatcher.forward(request, response);
	}

	public List<Candidato> listEleccion(String eleccion) {
		int idEleccion = (eleccion != null ? Integer.parseInt(eleccion) : 1);
		CandidatoDao cDao = new CandidatoDao();
		return cDao.list(idEleccion);
	}

	public List<TipoDocumento> listTipoDocumento() {
		TipoDocumentoDao tDao = new TipoDocumentoDao();
		return tDao.list();
	}

	public VotanteDao getDao() {
		return dao;
	}

	public void setDao(VotanteDao dao) {
		this.dao = dao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
