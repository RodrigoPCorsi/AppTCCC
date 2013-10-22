package br.com.modelo;

public class CartaoCredito {

	public int id;
	public String nome;
	public String numero;
	public String validade;
	public String cod_Seguranca;
	public int idPessoa;
	
	public CartaoCredito(int id, String nome, String numero, String validade,
			String cod_Seguranca, int idPessoa) {
		super();
		this.id = id;
		this.nome = nome;
		this.numero = numero;
		this.validade = validade;
		this.cod_Seguranca = cod_Seguranca;
		this.idPessoa = idPessoa;
	}

	public CartaoCredito() {
		super();
	};
	
}
