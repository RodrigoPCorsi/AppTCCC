package br.com.controle;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.activity.R;
import br.com.modelo.CartaoCredito;

public class AdapterListView_CartaoCredito extends BaseAdapter {
	private LayoutInflater mInflater;
	private ArrayList<CartaoCredito> itens;

	public AdapterListView_CartaoCredito(Context context, ArrayList<CartaoCredito> itens) {
		// Itens que preencheram o listview
		this.itens = itens;
		// responsavel por pegar o Layout do item.
		mInflater = LayoutInflater.from(context);
	}

	/**
	 * Retorna a quantidade de itens
	 * 
	 * @return
	 */
	public int getCount() {
		return itens.size();
	}

	/**
	 * Retorna o item de acordo com a posicao dele na tela.
	 * 
	 * @param position
	 * @return
	 */
	public CartaoCredito getItem(int position) {
		return itens.get(position);
	}

	/**
	 * Sem implementa��o
	 * 
	 * @param position
	 * @return
	 */
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {
		ItemSuporte itemHolder;
		// se a view estiver nula (nunca criada), inflamos o layout nela.
		if (view == null) {
			// infla o layout para podermos pegar as views
			view = mInflater.inflate(R.layout.xml_listarcartoes, null);

			// cria um item de suporte para n�o precisarmos sempre
			// inflar as mesmas informacoes
			itemHolder = new ItemSuporte();
			itemHolder.txtTitle = ((TextView) view.findViewById(R.id.text));
			itemHolder.imgIcon = ((ImageView) view
					.findViewById(R.id.imagemview));

			// define os itens na view;
			view.setTag(itemHolder);
		} else {
			// se a view j� existe pega os itens.
			itemHolder = (ItemSuporte) view.getTag();
		}

		// pega os dados da lista
		// e define os valores nos itens.
		CartaoCredito item = itens.get(position);
		itemHolder.txtTitle.setText(item.nome);

		// retorna a view com as informa��es
		return view;
	}

	/**
	 * Classe de suporte para os itens do layout.
	 */
	private class ItemSuporte {

		ImageView imgIcon;
		TextView txtTitle;
	}
}
