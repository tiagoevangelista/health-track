package br.com.fiap.healthtrack.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.healthtrack.bean.Peso;
import br.com.fiap.healthtrack.dao.PesoDAO;
import br.com.fiap.healthtrack.exception.DBException;
import br.com.fiap.healthtrack.factory.DAOFactory;

/**
 * Servlet implementation class PesoServlet
 */
@WebServlet("/peso")
public class PesoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PesoDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PesoServlet() {
        super();
    }
    
    @Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getPesoDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String visao = request.getParameter("visao");

		switch (visao) {
		case "historico":
			listar(request, response);
			break;
		case "inclusao":
			inclusao(request,response);
			break;
		}
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Peso> lista = dao.listar();
		request.setAttribute("pesos", lista);
		request.getRequestDispatcher("historico-peso.jsp").forward(request, response);
	}
	
	private void inclusao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Peso> lista = dao.resumir();
		request.setAttribute("pesos", lista);
		request.getRequestDispatcher("inclusao-peso.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String visao = request.getParameter("visao");

		switch (visao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "excluir":
			excluir(request,response);
			break;
		}
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			Double nome = Double.parseDouble(request.getParameter("nr-peso"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataInc = Calendar.getInstance();
			dataInc.setTime(format.parse(request.getParameter("data-peso")));

			Peso peso = new Peso(0, nome, dataInc);
			dao.cadastrar(peso);

			request.setAttribute("exito", "Atividade cadastrada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		
		inclusao(request, response);
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		try {
			dao.remover(codigo);
			request.setAttribute("exito", "Produto removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}
}
