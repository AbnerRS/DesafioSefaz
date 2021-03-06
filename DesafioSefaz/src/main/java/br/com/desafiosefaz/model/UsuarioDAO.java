package br.com.desafiosefaz.model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

//Classe respons?vel pelos m?todos de requisi??o com o HSQLDB
public class UsuarioDAO {
	private final String SQL_INSERE_USUARIO = "INSERT INTO \"PUBLIC\".\"USUARIOS\"(\"NOME\", \"EMAIL\", \"SENHA\", \"DDD\", \"NUMERO\", \"TIPO\") VALUES ( ?, ?, ?, ?, ?, ?)";
	private final String SQL_ALTERA_USUARIO = "UPDATE USUARIOS SET NOME=?, EMAIL=?, SENHA=?, DDD=?, NUMERO=?, TIPO=? WHERE ID=?;";
	private final String SQL_EXCLUI_USUARIO = "DELETE FROM USUARIOS WHERE ID=?";
	private final String SQL_SELECIONA_USUARIO = "SELECT * FROM USUARIOS";
	private final String SQL_ESCOLHE_USUARIO = "SELECT * FROM USUARIOS WHERE ID = ?";
	private final String SQL_AUTENTICA_LOGIN = "SELECT * FROM USUARIOS WHERE EMAIL = ? AND SENHA = ?";
	private PreparedStatement pst = null;

	public boolean autenticarLogin(String login, String pwd) {
		ResultSet ret;
		boolean autenticado = false;
		Connection conn = DAO.conectar();

		try {
			pst = conn.prepareStatement(SQL_AUTENTICA_LOGIN);
			pst.setString(1, login);
			pst.setString(2, pwd);

			ret = pst.executeQuery();

			if (ret.next()) {
				autenticado = true;
				return true;

			} else {
				autenticado = false;
				return false;
			}

		} catch (SQLException e) {

			System.out.println("Erro ao Executar o Statment " + e.toString());
		}

		return autenticado;
	}

	public boolean inserirUsuario(Usuario usuario) {
		boolean ret = false;
		Connection conn = DAO.conectar();

		try {
			pst = conn.prepareStatement(SQL_INSERE_USUARIO);
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
			pst.setInt(4, usuario.getTelefone().get(0).getDdd());
			pst.setString(5, usuario.getTelefone().get(0).getNumero());
			pst.setString(6, usuario.getTelefone().get(0).getTipo());

			ret = pst.execute();
			pst.close();
			DAO.fecharCNX();

		} catch (SQLException e) {
			System.out.println("Erro ao Executar o Statment " + e.toString());
		}
		return ret;
	}

	public ArrayList<Usuario> listarTodosUsuarios() {

		Connection conn = DAO.conectar();
		ArrayList<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
		ArrayList<Telefone> listaTelefone = new ArrayList<Telefone>();

		try {
			pst = conn.prepareStatement(SQL_SELECIONA_USUARIO);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String email = rs.getString(3);
				String senha = rs.getString(4);
				int ddd = rs.getInt(5);
				String numero = rs.getString(6);
				String tipo = rs.getString(7);

				Telefone telefone = new Telefone();

				telefone.setDdd(ddd);
				telefone.setNumero(numero);
				telefone.setTipo(tipo);

				listaTelefone.add(telefone);

				listaDeUsuarios.add(new Usuario(id, nome, email, senha, listaTelefone));

			}

			rs.close();
			pst.close();
			DAO.fecharCNX();

		} catch (SQLException e) {
			System.out.println("Erro ao Executar o Statement" + e.toString());
		}

		return listaDeUsuarios;

	}

	public boolean alterarUsuario(Usuario usuario) {
		boolean ret = false;
		Connection conn = DAO.conectar();
		try {
			pst = conn.prepareStatement(SQL_ALTERA_USUARIO);
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());
			pst.setInt(4, usuario.getTelefone().get(0).getDdd());
			pst.setString(5, usuario.getTelefone().get(0).getNumero());
			pst.setString(6, usuario.getTelefone().get(0).getTipo());
			pst.setInt(7, usuario.getId());

			ret = pst.execute();
			pst.close();
			DAO.fecharCNX();

		} catch (SQLException e) {
			System.out.println("Erro ao Executar o Statment " + e.toString());
		}

		return ret;
	}

	public boolean excluirUsuario(Usuario usuario) {
		boolean ret = false;
		Connection conn = DAO.conectar();
		try {
			pst = conn.prepareStatement(SQL_EXCLUI_USUARIO);
			pst.setInt(1, usuario.getId());
			ret = pst.execute();
			pst.close();
			DAO.fecharCNX();

		} catch (SQLException e) {
			System.out.println("Erro ao executar o statment" + e.toString());
		}

		return ret;
	}

	public void escolheUsuario(Usuario usuario) {

		ArrayList<Telefone> listaTelefone = new ArrayList<Telefone>();

		Connection conn = DAO.conectar();
		try {
			pst = conn.prepareStatement(SQL_ESCOLHE_USUARIO);
			pst.setInt(1, usuario.getId());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				usuario.setId(rs.getInt(1));
				usuario.setNome(rs.getString(2));
				usuario.setEmail(rs.getString(3));
				usuario.setSenha(rs.getString(4));

				Telefone telefone = new Telefone();
				telefone.setDdd(rs.getInt(5));
				telefone.setNumero(rs.getString(6));
				telefone.setTipo(rs.getString(7));

				listaTelefone.add(telefone);

				usuario.setTelefone(listaTelefone);
			}

			pst.close();
			DAO.fecharCNX();

		} catch (SQLException e) {
			System.out.println("Erro ao Executar o Statment " + e.toString());
		}

	}

}
