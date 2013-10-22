package br.com.activity.cadastros;

import br.com.activity.R;
import br.com.activity.R.id;
import br.com.activity.R.layout;
import br.com.activity.R.menu;
import br.com.activity.R.string;
import br.com.dao.Provider_CartaoTcc;
import br.com.modelo.CartaoTccc;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Act_CadastroCartaoTCC extends Activity {

	private Button btCadastrar;
	private TextView txtNome;
	private TextView txtNumero;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xml_cadcartaotcc);
		
		btCadastrar = (Button) findViewById(R.id.cadCTcc_btCadastrar);
		txtNome = (TextView) findViewById(R.id.cadCTcc_Nome);
		txtNumero = (TextView) findViewById(R.id.cadCTcc_txtNumero);
		
		btCadastrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				CartaoTccc cTcc = new CartaoTccc(0, txtNome.getText().toString(), txtNumero.getText().toString(), 1);
				Provider_CartaoTcc pc = new Provider_CartaoTcc(Act_CadastroCartaoTCC.this);
				
				try {
					if (!ValidarCampos())
						pc.salvar(cTcc);
				} catch (Exception e) {
					new AlertDialog.Builder(Act_CadastroCartaoTCC.this).setTitle("Erro").setMessage(e.getMessage().toString()).setNeutralButton("ok", null).show();  
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act__cadastro_cartao_tcc, menu);
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
		
		return cancel;
	}

}
