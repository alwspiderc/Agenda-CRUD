package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		}else if (action.equals("/delete")) {
			removerContato(request, response);
		}
		else {
			response.sendRedirect("index.html");
		}

	}

	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ir� receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();

		// Ecaminhando a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	// Adcionar novo contato
	private void novoContato(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// setar as vari�veis JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// invocar o m�todo insetirContato passando o objeto contato
		dao.inserirContato(contato);

		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");

	}

	// Editar Contato
	private void listarContato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Recebimento do id do contato que ser� editado
		String idcon = request.getParameter("idcon");
		//System.out.println("idcon:"+idcon);
		
		// Setar a vari�vel javaBeans
		contato.setIdcon(idcon);
		
		// Executar o m�todo selecionarContato (DAO)
		dao.selecionarContato(contato);

		// Setar os atributos do formul�rio com o conte�do JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		
		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	private void editarContato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// setar as vari�veis JavaBens
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		// Executar o m�todo alterarContato
		dao.alterarContato(contato);
		
		// redirecinar para o documento agenda.jsp (atualizando as altera��es)
		response.sendRedirect("main");
	}
	
	// Remover um contato
	private void removerContato(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// recebimento do id do contato a ser exclu�do (validados.js)
		String idcon = request.getParameter("idcon");
		
		// setar a vari�vel idcon JavaBeans
		contato.setIdcon(idcon);
		
		// Executar o m�todo deletarContato (DAO) passando o objeto contato
		dao.deletarContato(contato);
		
		// redirecinar para o documento agenda.jsp (atualizando as altera��es)
		response.sendRedirect("main");
	}
}







