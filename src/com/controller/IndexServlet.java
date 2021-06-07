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

@WebServlet("/")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CandidatoDao dao;

	public IndexServlet() {
	}

	////////////////////////////////////////////////
	/// Method
	////////////////////////////////////////////////
	public void init() throws ServletException {
		dao = new CandidatoDao();
	}

	////////////////////////////////////////////////
	/// Method - Override
	////////////////////////////////////////////////
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/newCandidato":
				showViewFormCandiato(request, response);
				break;
			case "/insertCandidato":
				insertCandiato(request, response);
				break;
			case "/deleteCandidato":
				deleteCandiato(request, response);
				break;
			case "/editCandidato":
				showEditCandiato(request, response);
				break;
			case "/updateCandidato":
				updateCandiato(request, response);
				break;
			case "/listCandidato":
				listCandiato(request, response);
				break;
			// VOTANTE
			case "/newVotante":
				showViewFormVotante(request, response);
				break;
			case "/insertVotante":
				insertVotante(request, response);
				break;
			case "/deleteVotante":
				deleteVotante(request, response);
				break;
			case "/updateVotante":
				updateVotante(request, response);
				break;
			case "/showUpdateVotante":
				showUpdateVotante(request, response);
				break;
			case "/listVotante":
				listVotante(request, response);
				break;
			case "/validarVotante":
				validarVotante(request, response);
				break;
			case "/showValidarVotante":
				showValidarVotante(request, response);
				break;
			default:
				showView(request, response);
				break;
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	////////////////////////////////////////////////
	/// Method
	////////////////////////////////////////////////
	private void showView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	////////////////////////////////////////////////
	/// Method - Votante
	////////////////////////////////////////////////
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
			int tipo_documento = Integer.parseInt(""+request.getParameter("tipo"));
			String documento = request.getParameter("documento");
			String email = request.getParameter("email");
			int eleccion = Integer.parseInt(""+request.getParameter("procesos"));
			
			VotanteDao dao = new VotanteDao();
			Votante aux = new Votante(nombre, email, documento, tipo_documento, eleccion);
			dao.insert(aux);
			response.sendRedirect("listVotante");
	}
	
	private void deleteVotante(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
			int id = Integer.parseInt(request.getParameter("votante"));
			
			VotanteDao dao = new VotanteDao();
			dao.delete(id);
			response.sendRedirect("listVotante");
	}
	
	private void updateVotante(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
			
			int id = Integer.parseInt(request.getParameter("id"));
			String nombre = request.getParameter("nombre");
			String estamento = request.getParameter("estamento");
			int tipo_documento = Integer.parseInt(""+request.getParameter("tipo"));
			String documento = request.getParameter("documento");
			String email = request.getParameter("email");
			int eleccion = Integer.parseInt(""+request.getParameter("procesos"));
			
			VotanteDao dao = new VotanteDao();
			Votante aux = new Votante(id, nombre, email, documento, tipo_documento, eleccion);
			dao.update(aux);
			response.sendRedirect("listVotante");
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
	
	private void validarVotante(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		String nombre = request.getParameter("nombre");
		int tipo_documento = Integer.parseInt(""+request.getParameter("tipo"));
		String documento = request.getParameter("documento");
		String email = request.getParameter("email");
		
		VotanteDao dao = new VotanteDao();
		Votante aux = new Votante(nombre, email, documento, tipo_documento, -1);
		aux = dao.find(aux);
		
		boolean aux2 = false;
		if(aux.getNombre().equalsIgnoreCase(nombre)) {
			if(aux.getEmail().equalsIgnoreCase(email)) {
				if(aux.getTipo_documento()==tipo_documento) {
					aux2 = true;
				}
			}
		}
		String eleccion = request.getParameter("idEleccion");
		int idEleccion = (eleccion != null ? Integer.parseInt(eleccion) : 1);
		CandidatoDao cDao = new CandidatoDao();
		List<Candidato> ca = cDao.list(idEleccion);
		request.setAttribute("votanteValidado", aux2);
		request.setAttribute("listCandidato", ca);
		RequestDispatcher dispatcher = request.getRequestDispatcher("votacion.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showValidarVotante(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		TipoDocumentoDao tDao = new TipoDocumentoDao();
		List<TipoDocumento> t = tDao.list();
		request.setAttribute("tipos", t);
		RequestDispatcher dispatcher = request.getRequestDispatcher("validacion.jsp");
		dispatcher.forward(request, response);
	}

	////////////////////////////////////////////////
	/// Method - Candidato
	////////////////////////////////////////////////
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
		response.sendRedirect("listCandidato");
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
		response.sendRedirect("listCandidato");
	}

	private void deleteCandiato(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String id = request.getParameter("id");
		if (dao == null) {
			this.init();
		}
		dao.delete(Integer.parseInt(id));
		response.sendRedirect("listCandidato");
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
