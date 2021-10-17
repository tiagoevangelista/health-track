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

import br.com.fiap.healthtrack.bean.Alimento;
import br.com.fiap.healthtrack.bean.Refeicao;
import br.com.fiap.healthtrack.dao.AlimentoDAO;
import br.com.fiap.healthtrack.dao.RefeicaoDAO;
import br.com.fiap.healthtrack.exception.DBException;
import br.com.fiap.healthtrack.factory.DAOFactory;

/**
 * Servlet implementation class AlimentoServlet
 */
@WebServlet("/alimento")
public class AlimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RefeicaoDAO refDAO;
	private AlimentoDAO dao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlimentoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getAlimentoDAO();
		refDAO = DAOFactory.getRefeicaoDAO();
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
		List<Refeicao> lista = refDAO.listar();
		request.setAttribute("refeicoes", lista);
		request.getRequestDispatcher("historico-alimento.jsp").forward(request, response);
	}
	
	private void inclusao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Refeicao> lista = refDAO.resumir();
		request.setAttribute("refeicoes", lista);
		request.getRequestDispatcher("inclusao-alimento.jsp").forward(request, response);
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
			String nome = request.getParameter("nome-almnt");
			Double caloria = Double.parseDouble(request.getParameter("qtd-cal"));
			
			Alimento alimento = new Alimento(0, caloria, nome);
			dao.cadastrar(alimento);
			
			Integer almtAtual = dao.ultimo().getCodigo();
			Alimento alimentoIndex = new Alimento(almtAtual, caloria, nome);
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataInc = Calendar.getInstance();
			dataInc.setTime(format.parse(request.getParameter("data-ref")));
			
			Refeicao refeicao = new Refeicao(0, dataInc);
			refeicao.setUsuario(10);
			refeicao.setAlimento(alimentoIndex);
			
			refDAO.cadastrar(refeicao);

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
			refDAO.remover(codigo);
			request.setAttribute("exito", "Produto removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}

}
