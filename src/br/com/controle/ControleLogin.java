package br.com.controle;

import java.util.List;

import android.content.Context;
import br.com.dao.Provider_Pessoa;

public class ControleLogin {

	public static boolean Logar(String login, String senha, Context context) {
		Provider_Pessoa pp = new Provider_Pessoa(context);
		return pp.LoadByLogin(login, senha);
	}
}
