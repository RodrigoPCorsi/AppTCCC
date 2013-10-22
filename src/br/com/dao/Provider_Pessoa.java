package br.com.dao;

import java.util.List;

import br.com.modelo.Pessoa;

import com.db4o.ObjectContainer;

import android.content.Context;

public class Provider_Pessoa extends Db4oHelper {

	public Provider_Pessoa(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void salvar(Pessoa pessoa) throws Exception {
		
		for (Pessoa p : this.buscarTodos()) {
			if(pessoa.login.equals(p.login))	
				throw new Exception("Ja existe uma pessoa cadastrada com esse login.");
			if(pessoa.cpf.equals(p.cpf))
				throw new Exception("Ja existe uma pessoa cadastrada com esse cpf.");
			if(pessoa.rg.equals(p.rg))
				throw new Exception("Ja existe uma pessoa cadastrada com esse rg.");
			if(pessoa.email.equals(p.email))
				throw new Exception("Ja existe uma pessoa cadastrada com esse email.");
		}
		
		getDatabase().store(pessoa);
		commit();
	}

	public void deletar(Pessoa pessoa) {
		getDatabase().delete(pessoa);
		commit();
	}

	public List<Pessoa> buscarTodos() {

		return getDatabase().query(Pessoa.class);
	}

	public void deletarTodos() {
		List<Pessoa> list = buscarTodos();

		for (Pessoa p : list) {
			deletar(p);
		}
		commit();
	}

	public Pessoa LoadById(int id) {
		Pessoa p = new Pessoa();
		for (Pessoa pessoa : this.buscarTodos()) {
			if (pessoa.codigo == id) {
				p = pessoa;
				break;
			}
		}
		return p;
	}

	public boolean LoadByLogin(String login, String senha) {
		boolean logar = false;
		for (Pessoa pessoa : this.buscarTodos()) {
			if (pessoa.login.equals(login)) {
				if (pessoa.senha.equals(senha)) {
					logar = true;
					break;
				}
			}
		}
		return logar;
	}

}
