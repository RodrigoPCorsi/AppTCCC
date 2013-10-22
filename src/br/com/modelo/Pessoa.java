package br.com.modelo;

public class Pessoa {

	public int codigo;
	public String rg;
	public String cpf;
	public String nome;
	public String email;
	public String endereco;
	public String login;
	public String senha;
	
	public Pessoa(int codigo, String rg, String cpf, String nome, String email,
			String endereco, String login, String senha) {
		super();
		this.codigo = codigo;
		this.rg = rg;
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
		this.login = login;
		this.senha = senha;
		this.email = email;
	}

	public Pessoa() {
		super();
	}
}
