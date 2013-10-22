package br.com.activity.viewdb;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.activity.R;
import br.com.controle.AdapterListView_CartaoCredito;
import br.com.dao.Provider_CartaoCredito;
import br.com.modelo.CartaoCredito;

public class Act_ListarCartoes extends Activity implements OnItemClickListener {

	private ListView listView;
	private AdapterListView_CartaoCredito adapterListView;
	private ArrayList<CartaoCredito> itens;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// carrega o layout onde contem o ListView
		setContentView(R.layout.xml_listholder);

		// Pega a referencia do ListView
		listView = (ListView) findViewById(R.id.list);
		// Define o Listener quando alguem clicar no item.
		listView.setOnItemClickListener(this);

		createListView();
	}

	private void createListView() {
		// Criamos nossa lista que preenchera o ListView

		Provider_CartaoCredito pc = new Provider_CartaoCredito(getBaseContext());
		
		List<CartaoCredito> lCartao = new ArrayList<CartaoCredito>();
		lCartao = pc.buscarTodos(1);

		for (CartaoCredito cartaoCredito : lCartao) {
			itens.add(cartaoCredito);
		}

		// Cria o adapter
		adapterListView = new AdapterListView_CartaoCredito(this, itens);

		// Define o Adapter
		listView.setAdapter(adapterListView);
		// Cor quando a lista é selecionada para ralagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// Pega o item que foi selecionado.
		CartaoCredito item = adapterListView.getItem(arg2);
		// Demostração
		Toast.makeText(this, "Você Clicou em: " + item.nome,
				Toast.LENGTH_LONG).show();
	}

}
