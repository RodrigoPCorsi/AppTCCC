package br.com.modelo;

public class CartaoTccc {

	public int id;
	public String nome;
	public String num_Cartao;
	public int idPessoa;
	
	public CartaoTccc(int id, String nome, String num_Cartao, int idPessoa) {
		super();
		this.id = id;
		this.nome = nome;
		this.num_Cartao = num_Cartao;
		this.idPessoa = idPessoa;
	}

	public CartaoTccc() {
		super();
	}
		
}
