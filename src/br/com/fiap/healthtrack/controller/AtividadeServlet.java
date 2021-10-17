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

import br.com.fiap.healthtrack.bean.Atividade;
import br.com.fiap.healthtrack.bean.Treino;
import br.com.fiap.healthtrack.dao.AtividadeDAO;
import br.com.fiap.healthtrack.dao.TreinoDAO;
import br.com.fiap.healthtrack.exception.DBException;
import br.com.fiap.healthtrack.factory.DAOFactory;

/**
 * Servlet implementation class AtividadeServlet
 */
@WebServlet("/atividade")
public class AtividadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TreinoDAO trDAO;
	private AtividadeDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtividadeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getAtividadeDAO();
		trDAO = DAOFactory.getTreinoDAO();
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
		List<Treino> lista = trDAO.listar();
		request.setAttribute("treinos", lista);
		request.getRequestDispatcher("historico-atividade.jsp").forward(request, response);
	}
	
	private void inclusao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Treino> lista = trDAO.resumir();
		request.setAttribute("treinos", lista);
		request.getRequestDispatcher("inclusao-atividade.jsp").forward(request, response);
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
			
			String nome = request.getParameter("nome-atv");
			Atividade atividade = new Atividade(0, nome);
			dao.cadastrar(atividade);
			
			Integer atvAtual = dao.ultimo().getCodigo();
			Atividade atividadeIndex = new Atividade(atvAtual, nome);

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataInc = Calendar.getInstance();
			dataInc.setTime(format.parse(request.getParameter("data-ref")));
			
			Integer duracao = Integer.parseInt(request.getParameter("tmp-duracao"));
			
			Treino treino = new Treino(0, dataInc, duracao);
			treino.setUsuario(10);
			treino.setAtividade(atividadeIndex);
			
			trDAO.cadastrar(treino);
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
			trDAO.remover(codigo);
			request.setAttribute("exito", "Produto removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}

}
