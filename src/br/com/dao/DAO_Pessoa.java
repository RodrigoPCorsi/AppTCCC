package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.modelo.Pessoa;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAO_Pessoa {

	public final static String NOME_TABELA = "Pessoa";
	public final static String COLUNA_ID = "Id";
	public final static String COLUNA_EMAIL = "Email";
	public final static String COLUNA_CPF = "Cpf";
	public final static String COLUNA_RG = "Rg";
	public final static String COLUNA_NOME = "Nome";
	public final static String COLUNA_ENDERECO = "Endereco";
	public final static String COLUNA_LOGIN = "Login";
	public final static String COLUNA_SENHA = "Senha";

	public final static String SCRIPT_CREATE_TABLE_PESSOA = "CREATE TABLE "
			+ NOME_TABELA + "(" + COLUNA_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUNA_CPF + " TEXT NOT NULL UNIQUE,"
			+ COLUNA_EMAIL + " TEXT NOT NULL UNIQUE," + COLUNA_ENDERECO + " TEXT,"
			+ COLUNA_LOGIN + " TEXT NOT NULL UNIQUE," + COLUNA_SENHA + " TEXT," + COLUNA_RG
			+ " TEXT NOT NULL UNIQUE, " + COLUNA_NOME + " TEXT " + ")";

	public static final String SCRIPT_DELETE_TABLE_PESSOA = "DROP TABLE IF EXISTS "
			+ NOME_TABELA;

	private SQLiteDatabase dataBase = null;

	private static DAO_Pessoa instance;

	public static DAO_Pessoa getInstance(Context context) {
		if (instance == null)
			instance = new DAO_Pessoa(context);
		return instance;
	}

	private DAO_Pessoa(Context context) {
		PersistenceHelper persistenceHelper = PersistenceHelper
				.getInstance(context);
		dataBase = persistenceHelper.getWritableDatabase();
	}

	public void salvar(Pessoa pessoa) {
		ContentValues values = gerarContentValuesPessoa(pessoa);
		dataBase.insert(NOME_TABELA, null, values);
	}

	public List<Pessoa> recuperarTodos() {
		String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
		Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
		List<Pessoa> pessoas = construirPessoaPorCursorLista(cursor);

		return pessoas;
	}
	
	public Pessoa recuperarPorId(int id) {
		String queryReturnById = "SELECT * FROM " + NOME_TABELA + " WHERE Id = " + id;
		Cursor cursor = dataBase.rawQuery(queryReturnById, null);
		return construirPessoaPorCursorSingle(cursor);
	
	}

	public void deletar(Pessoa pessoa) {

		String[] valoresParaSubstituir = { String.valueOf(pessoa.codigo) };

		dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
	}

	public void editar(Pessoa pessoa) {
		ContentValues valores = gerarContentValuesPessoa(pessoa);

		String[] valoresParaSubstituir = { String.valueOf(pessoa.codigo) };

		dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?",
				valoresParaSubstituir);
	}

	public void fecharConexao() {
		if (dataBase != null && dataBase.isOpen())
			dataBase.close();
	}

	private List<Pessoa> construirPessoaPorCursorLista(Cursor cursor) {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		if (cursor == null)
			return pessoas;

		try {

			if (cursor.moveToFirst()) {
				do {
					int indexID = cursor.getColumnIndex(COLUNA_ID);
					int indexNome = cursor.getColumnIndex(COLUNA_NOME);
					int indexEmail = cursor.getColumnIndex(COLUNA_EMAIL);
					int indexCpf = cursor.getColumnIndex(COLUNA_CPF);
					int indexRg = cursor.getColumnIndex(COLUNA_RG);
					int indexEnd = cursor.getColumnIndex(COLUNA_ENDERECO);
					int indexLogin = cursor.getColumnIndex(COLUNA_LOGIN);
					int indexSenha = cursor.getColumnIndex(COLUNA_SENHA);

					int id = cursor.getInt(indexID);
					String email = cursor.getString(indexEmail);
					String nome = cursor.getString(indexNome);
					String cpf = cursor.getString(indexCpf);
					String rg = cursor.getString(indexRg);
					String end = cursor.getString(indexEnd);
					String login = cursor.getString(indexLogin);
					String senha = cursor.getString(indexSenha);

					Pessoa pessoa = new Pessoa(id, rg, cpf, nome, email, end,
							login, senha);

					pessoas.add(pessoa);

				} while (cursor.moveToNext());
			}

		} finally {
			cursor.close();
		}
		return pessoas;
	}

	private Pessoa construirPessoaPorCursorSingle(Cursor cursor) {
		Pessoa pessoa = new Pessoa();
		if (cursor == null)
			return pessoa;

		try {

			int indexID = cursor.getColumnIndex(COLUNA_ID);
			int indexNome = cursor.getColumnIndex(COLUNA_NOME);
			int indexEmail = cursor.getColumnIndex(COLUNA_EMAIL);
			int indexCpf = cursor.getColumnIndex(COLUNA_CPF);
			int indexRg = cursor.getColumnIndex(COLUNA_RG);
			int indexEnd = cursor.getColumnIndex(COLUNA_ENDERECO);
			int indexLogin = cursor.getColumnIndex(COLUNA_LOGIN);
			int indexSenha = cursor.getColumnIndex(COLUNA_SENHA);

			int id = cursor.getInt(indexID);
			String email = cursor.getString(indexEmail);
			String nome = cursor.getString(indexNome);
			String cpf = cursor.getString(indexCpf);
			String rg = cursor.getString(indexRg);
			String end = cursor.getString(indexEnd);
			String login = cursor.getString(indexLogin);
			String senha = cursor.getString(indexSenha);

			pessoa = new Pessoa(id, rg, cpf, nome, email, end, login, senha);

		} finally {
			cursor.close();
		}
		return pessoa;
	}

	private ContentValues gerarContentValuesPessoa(Pessoa pessoa) {
		ContentValues values = new ContentValues();
		values.put(COLUNA_CPF, pessoa.cpf);
		values.put(COLUNA_EMAIL, pessoa.email);
		values.put(COLUNA_ENDERECO, pessoa.endereco);
		values.put(COLUNA_LOGIN, pessoa.login);
		values.put(COLUNA_SENHA, pessoa.senha);
		values.put(COLUNA_RG, pessoa.rg);

		return values;
	}
}
