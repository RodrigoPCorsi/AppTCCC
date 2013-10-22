package br.com.activity.cadastros;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import br.com.activity.Act_Login;
import br.com.activity.R;
import br.com.activity.R.id;
import br.com.activity.R.layout;
import br.com.activity.R.menu;
import br.com.activity.R.string;
import br.com.dao.Provider_Pessoa;
import br.com.modelo.Pessoa;

public class Act_CadastroPessoa extends Activity {

	private TextView txtNome;
	private TextView txtEmail;
	private TextView txtEndereco;
	private TextView txtRg;
	private TextView txtCpf;
	private TextView txtLogin;
	private TextView txtSenha;
	private Button btCadastrar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xml_cadastropessoa);

		txtNome = (TextView) findViewById(R.id.cadPessoa_txtNome);
		txtEmail = (TextView) findViewById(R.id.cadPessoa_txtEmail);
		txtEndereco = (TextView) findViewById(R.id.cadPessoa_txtEndereco);
		txtLogin = (TextView) findViewById(R.id.cadPessoa_txtLogin);
		txtRg = (TextView) findViewById(R.id.cadPessoa_txtRg);
		txtCpf = (TextView) findViewById(R.id.cadPessoa_txtCpf);
		txtSenha = (TextView) findViewById(R.id.cadPessoa_txtSenha);
		btCadastrar = (Button) findViewById(R.id.cadPessoa_btCadastrar);

		btCadastrar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Pessoa p = new Pessoa(0, txtRg.getText().toString(), txtCpf
						.getText().toString(), txtNome.getText().toString(),
						txtEmail.getText().toString(), txtEndereco.getText()
								.toString(), txtLogin.getText().toString(),
						txtSenha.getText().toString());
				try {
					if (!ValidarCampos()) {
						Provider_Pessoa pp = new Provider_Pessoa(
								Act_CadastroPessoa.this);
						pp.salvar(p);
						new AlertDialog.Builder(Act_CadastroPessoa.this)
								.setTitle("Sucesso")
								.setMessage("Usuario Cadastrado com Sucesso")
								.setNeutralButton("ok", null).show();
						Intent i = new Intent(Act_CadastroPessoa.this,
								Act_Login.class);
						startActivityForResult(i, 1);
					}

				} catch (Exception e) {
					new AlertDialog.Builder(Act_CadastroPessoa.this)
							.setTitle("Erro")
							.setMessage(e.getMessage().toString())
							.setNeutralButton("ok", null).show();
				}

			}
		});

	}

	public boolean ValidarCampos() {

		boolean cancel = false;
		View focusView = null;

		if (TextUtils.isEmpty(txtCpf.getText().toString())) {
			txtCpf.setError(getString(R.string.cad_CAmpoObrigatorio));
			focusView = txtCpf;
			cancel = true;
		}
		if (TextUtils.isEmpty(txtEmail.getText().toString())) {
			txtEmail.setError(getString(R.string.cad_CAmpoObrigatorio));
			focusView = txtEmail;
			cancel = true;
		}
		if (TextUtils.isEmpty(txtEndereco.getText().toString())) {
			txtEndereco.setError(getString(R.string.cad_CAmpoObrigatorio));
			focusView = txtEndereco;
			cancel = true;
		}
		if (TextUtils.isEmpty(txtSenha.getText().toString())) {
			txtSenha.setError(getString(R.string.cad_CAmpoObrigatorio));
			focusView = txtSenha;
			cancel = true;
		}
		if (TextUtils.isEmpty(txtLogin.getText().toString())) {
			txtLogin.setError(getString(R.string.cad_CAmpoObrigatorio));
			focusView = txtLogin;
			cancel = true;
		}
		if (TextUtils.isEmpty(txtNome.getText().toString())) {
			txtNome.setError(getString(R.string.cad_CAmpoObrigatorio));
			focusView = txtNome;
			cancel = true;
		}
		if (TextUtils.isEmpty(txtRg.getText().toString())) {
			txtRg.setError(getString(R.string.cad_CAmpoObrigatorio));
			focusView = txtRg;
			cancel = true;
		}
		if (txtSenha.getText().toString().length() < 4) {
			txtSenha.setError(getString(R.string.cad_senhaInvalida));
			focusView = txtSenha;
			cancel = true;
		}
		if (!txtEmail.getText().toString().contains("@")) {
			txtEmail.setError(getString(R.string.cad_emailInvalido));
			focusView = txtEmail;
			cancel = true;
		}

		return cancel;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act__cadastro_pessoa, menu);
		return true;
	}

}
