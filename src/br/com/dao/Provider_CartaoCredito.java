package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.modelo.CartaoCredito;

import com.db4o.ObjectContainer;

import android.content.Context;

public class Provider_CartaoCredito extends Db4oHelper{

	public Provider_CartaoCredito(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void salvar(CartaoCredito cartaoCredito) {
		getDatabase().store(cartaoCredito);
		commit();
	}

	public void deletar(CartaoCredito cartaoCredito) {
		getDatabase().delete(cartaoCredito);
		commit();
	}

	public List<CartaoCredito> buscarTodos(int id) {
		List<CartaoCredito> lCartao = new ArrayList<CartaoCredito>();
		for (CartaoCredito cc : getDatabase().query(CartaoCredito.class)) {
			if (cc.idPessoa == id){
				lCartao.add(cc);
			}
		}
		return lCartao;		
	}

	public void deletarTodos(int id) {
		List<CartaoCredito> list = buscarTodos(id);

		for (CartaoCredito p : list) {
			deletar(p);
		}
		commit();
	}

	public CartaoCredito LoadById(int id) {
		CartaoCredito p = new CartaoCredito();
		for (CartaoCredito cartaoCredito : this.buscarTodos(id)) {
			if (cartaoCredito.id == id) {
				p = cartaoCredito;
				break;
			}
		}
		return p;
	}	
}
