package br.com.desafiosefaz.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.security.auth.message.MessageInfo;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.component.message.Message;

import br.com.desafiosefaz.model.DAO;
import br.com.desafiosefaz.model.Telefone;
import br.com.desafiosefaz.model.Usuario;
import br.com.desafiosefaz.model.UsuarioDAO;

@WebServlet(urlPatterns = { "/main", "/insert", "/select", "/update", "/delete", "/login" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO();
	Usuario usr = new Usuario();
	UsuarioDAO usrDAO = new UsuarioDAO();
	Telefone telefone = new Telefone();
	ArrayList<Telefone> listaTelefone = new ArrayList<Telefone>();

	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		

		if (action.equals("/main")) {
			usuarios(request, response);

		} else if (action.equals("/login")) {
			autenticarLogin(request, response);

		} else if (action.equals("/insert")) {
			novoUsuario(request, response);

		} else if (action.equals("/select")) {
			editarUsuario(request, response);

		} else if (action.equals("/update")) {
			updateUsuario(request, response);

		} else if (action.equals("/delete")) {
			excluirUsuario(request, response);

		} else {
			response.sendRedirect("index.html");
		}
	}

	// fazer a verifica??o se o usu?rio est? cadastrado no banco para realizar o
	// login
	public void autenticarLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recebimento do login e da senha do Usuario que vai ser verificado no banco
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");

		boolean permissao = usrDAO.autenticarLogin(login, pwd);

		if (permissao == true) {
			// redirecionar para principal.jsp
			response.sendRedirect("main");
		} else {

			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.forward(request, response);
		}

	}

	// Listar Usu?rios
	public void usuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Usuario> lista = usrDAO.listarTodosUsuarios();

		request.setAttribute("usuarios", lista);
		RequestDispatcher rd = request.getRequestDispatcher("principal.jsp");
		rd.forward(request, response);
	}

	// Novo Usu?rio
	protected void novoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setar as vari?veis
		usr.setNome(request.getParameter("nome"));
		usr.setEmail(request.getParameter("email"));
		usr.setSenha(request.getParameter("senha"));

		// pegar o telefone em partes
		String telddd = request.getParameter("telefoneDdd");
		String telNumero = request.getParameter("telefoneNumero");
		String telTipo = request.getParameter("telefoneTipo");

		int telConvertido = Integer.parseInt(telddd);

		// preenchendo a Lista de telefone
		telefone.setDdd(telConvertido);
		telefone.setNumero(telNumero);
		telefone.setTipo(telTipo);

		listaTelefone.add(telefone);
		usr.setTelefone(listaTelefone);

		// invocar o Inserir passando o usu?rio
		usrDAO.inserirUsuario(usr);

		// redirecionar para principal.jsp
		response.sendRedirect("main");
	}

	// Listar Usu?rio separadamento na aba de editar
	protected void editarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recebimento do id do Usuario que vai ser editado
		String id = request.getParameter("id");
		// Setar a vari?vel Usuario
		int idConvertido = Integer.parseInt(id);
		usr.setId(idConvertido);

		// Executar o m?todo escolheUsuario
		usrDAO.escolheUsuario(usr);

		// Setar os atributos do formul?rio com o Conte?do do Usu?rio da tabela
		request.setAttribute("id", usr.getId());
		request.setAttribute("nome", usr.getNome());
		request.setAttribute("email", usr.getEmail());
		request.setAttribute("senha", usr.getSenha());
		request.setAttribute("telefoneDdd", usr.getTelefone().get(0).getDdd());
		request.setAttribute("telefoneNumero", usr.getTelefone().get(0).getNumero());
		request.setAttribute("telefoneTipo", usr.getTelefone().get(0).getTipo());

		// encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}

	// Editar dados do usu?rio
	protected void updateUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setar as vari?veis do usuario
		String id = request.getParameter("id");
		int idConvertido = Integer.parseInt(id);

		usr.setId(idConvertido);
		usr.setNome(request.getParameter("nome"));
		usr.setEmail(request.getParameter("email"));
		usr.setSenha(request.getParameter("senha"));

		// pegar o telefone em partes
		String telddd = request.getParameter("telefoneDdd");
		String telNumero = request.getParameter("telefoneNumero");
		String telTipo = request.getParameter("telefoneTipo");

		int telConvertido = Integer.parseInt(telddd);

		// preenchendo a Lista de telefone
		telefone.setDdd(telConvertido);
		telefone.setNumero(telNumero);
		telefone.setTipo(telTipo);

		listaTelefone.add(telefone);
		usr.setTelefone(listaTelefone);

		// executando o m?todo de alterar o usuario
		usrDAO.alterarUsuario(usr);

		// redirecionar para o documento principal.jsp ( atualizar as altera??es)
		response.sendRedirect("main");

	}

	// Excluir Usu?rio
	protected void excluirUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recebimento do id do contato do documento confirmador.js
		String id = request.getParameter("id");
		int idConvertido = Integer.parseInt(id);

		usr.setId(idConvertido);
		usrDAO.excluirUsuario(usr);

		// redirecionar para o documento principal.jsp ( atualizar as altera??es)
		response.sendRedirect("main");

	}
}
