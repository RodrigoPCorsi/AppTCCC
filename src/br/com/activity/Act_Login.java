package br.com.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.activity.cadastros.Act_CadastroPessoa;
import br.com.activity.viewdb.Act_ListarCartoes;
import br.com.controle.ControleLogin;
import br.com.modelo.CartaoCredito;

public class Act_Login extends Activity {

	Button btLogar;
	Button btCadastrar;

	EditText txtLogin;
	EditText txtSenha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xml_login);

		btLogar = (Button) findViewById(R.id.login_btLogar);
		btCadastrar = (Button) findViewById(R.id.login_btCadastrar);

		txtLogin = (EditText) findViewById(R.id.login_txtLogin);
		txtSenha = (EditText) findViewById(R.id.login_txtSenha);

		btLogar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!ValidarCampos()) {
					Boolean logado = ControleLogin.Logar(txtLogin.getText()
							.toString(), txtSenha.getText().toString(),
							Act_Login.this);
					if (logado) {
						Intent i = new Intent(Act_Login.this, Act_Main.class);
						startActivityForResult(i, 1);
					} else {
						new AlertDialog.Builder(Act_Login.this)
								.setTitle("Erro")
								.setMessage("Login e/ou senha inválidos")
								.setNeutralButton("ok", null).show();
					}
				}
			}
		});

		btCadastrar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {			
				Intent i = new Intent(Act_Login.this, Act_CadastroPessoa.class);
				startActivityForResult(i, 1);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act__login, menu);
		return true;
	}

	public boolean ValidarCampos() {

		boolean cancel = false;
		View focusView = null;

		if (TextUtils.isEmpty(txtLogin.getText().toString())) {
			txtLogin.setError(getString(R.string.cad_CAmpoObrigatorio));
			focusView = txtLogin;
			cancel = true;
		}

		if (TextUtils.isEmpty(txtSenha.getText().toString())) {
			txtSenha.setError(getString(R.string.cad_CAmpoObrigatorio));
			focusView = txtSenha;
			cancel = true;
		}

		return cancel;
	}

}
