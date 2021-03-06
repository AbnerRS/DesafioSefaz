package br.com.desafiosefaz.model;

import java.util.ArrayList;

import javax.persistence.Id;

public class Usuario {

	@Id
	private int id;
	private String nome;
	private String email;
	private String senha;
	private ArrayList<Telefone> telefone = new ArrayList<Telefone>();

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(int id, String nome, String email, String senha, ArrayList<Telefone> telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public ArrayList<Telefone> getTelefone() {

		return telefone;
	}

	public void setTelefone(ArrayList<Telefone> telefone) {
		this.telefone = telefone;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
