package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.modelo.CartaoCredito;
import br.com.modelo.CartaoTccc;
import android.content.Context;

public class Provider_CartaoTcc extends Db4oHelper {

	public Provider_CartaoTcc(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void salvar(CartaoTccc cartaoTccc) {
		getDatabase().store(cartaoTccc);
		commit();
	}

	public void deletar(CartaoTccc cartaoTccc) {
		getDatabase().delete(cartaoTccc);
		commit();
	}

	public List<CartaoTccc> buscarTodos(int id) {
		List<CartaoTccc> lCartao = new ArrayList<CartaoTccc>();
		for (CartaoTccc cc : getDatabase().query(CartaoTccc.class)) {
			if (cc.idPessoa == id){
				lCartao.add(cc);
			}
		}
		return lCartao;		
	}

	public void deletarTodos(int id) {
		List<CartaoTccc> list = buscarTodos(id);

		for (CartaoTccc p : list) {
			deletar(p);
		}
		commit();
	}

	public CartaoTccc LoadById(int id) {
		CartaoTccc p = new CartaoTccc();
		for (CartaoTccc cartaoTccc : this.buscarTodos(id)) {
			if (cartaoTccc.id == id) {
				p = cartaoTccc;
				break;
			}
		}
		return p;
	}	

}
