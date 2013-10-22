package br.com.activity.cadastros;

import br.com.activity.R;
import br.com.activity.R.id;
import br.com.activity.R.layout;
import br.com.activity.R.menu;
import br.com.activity.R.string;
import br.com.dao.Provider_CartaoCredito;
import br.com.modelo.CartaoCredito;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Act_CadastroCartaoCredito extends Activity {

	private Button btCadastro;
	private TextView txtNome;
	private TextView txtNumero;
	private TextView txtValidade;
	private TextView txtCodSeguranca;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xml_cadcartaocredito);

		btCadastro = (Button) findViewById(R.id.cadCC_btCadastro);
		txtNome = (TextView) findViewById(R.id.cadCC_txtNome);
		txtNumero = (TextView) findViewById(R.id.cadCC_txtNumero);
		txtValidade = (TextView) findViewById(R.id.cadCC_txtValidade);
		txtCodSeguranca = (TextView) findViewById(R.id.cadCC_txtCodSeguranca);

		btCadastro.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Provider_CartaoCredito pcc = new Provider_CartaoCredito(Act_CadastroCartaoCredito.this);
				CartaoCredito cc = new CartaoCredito(0, txtNome.getText()
						.toString(), txtNumero.getText().toString(),
						txtValidade.getText().toString(), txtCodSeguranca
								.getText().toString(), 1);
				
				try {
					if (!ValidarCampos())
						pcc.salvar(cc);
				} catch (Exception e) {
					new AlertDialog.Builder(Act_CadastroCartaoCredito.this).setTitle("Erro").setMessage(e.getMessage().toString()).setNeutralButton("ok", null).show();
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act__cadastro_cartao_credito, menu);
		return true;
	}
	
	public boolean ValidarCampos() {

		boolean cancel = false;
		View focusView = null;

		if (TextUtils.isEmpty(txtNome.getText().toString())) {
			txtNome.setError(getString(R.string.cad_CAmpoObrigatorio));
			focusView = txtNome;
			cancel = true;
		}
		
		if (TextUtils.isEmpty(txtNumero.getText().toString())) {
			txtNumero.setError(getString(R.string.cad_CAmpoObrigatorio));
			focusView = txtNumero;
			cancel = true;
		}
		
		if (TextUtils.isEmpty(txtValidade.getText().toString())) {
			txtValidade.setError(getString(R.string.cad_CAmpoObrigatorio));
			focusView = txtValidade;
			cancel = true;
		}
		
		if (TextUtils.isEmpty(txtCodSeguranca.getText().toString())) {
			txtCodSeguranca.setError(getString(R.string.cad_CAmpoObrigatorio));
			focusView = txtCodSeguranca;
			cancel = true;
		}
		
		return cancel;
	}
}
