package br.com.desafiosefaz.model;

public class Telefone {

	public Telefone() {
		super();

	}

	Telefone(int ddd, String numero, String tipo) {

		this.ddd = ddd;
		this.numero = numero;
		this.tipo = tipo;

	}

	private int ddd;
	private String numero;
	private String tipo;

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "(" + ddd + ")" + numero + " - " + tipo;
	}

}
