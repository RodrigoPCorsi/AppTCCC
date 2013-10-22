package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.modelo.CartaoCredito;

public class DAO_CartaoCredito {
	public final static String NOME_TABELA = "CartaoCredito";
	public final static String COLUNA_ID = "Id";
	public final static String COLUNA_NOME = "Nome";
	public final static String COLUNA_NUMERO = "Numero";
	public final static String COLUNA_VALIDADE = "Validade";
	public final static String COLUNA_CODSEGURANCA = "CodSeguranca";
	public final static String COLUNA_USER = "IdUser";

	public final static String SCRIPT_CREATE_TABLE_CARTAOCREDITO = "CREATE TABLE "
			+ NOME_TABELA
			+ "("
			+ COLUNA_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUNA_NOME
			+ " TEXT NOT NULL UNIQUE,"
			+ COLUNA_USER
			+ "SMALLINT NOT NULL UNIQUE, "
			+ COLUNA_NUMERO
			+ " TEXT NOT NULL UNIQUE,"
			+ COLUNA_VALIDADE
			+ " TEXT,"
			+ COLUNA_CODSEGURANCA + " TEXT NOT NULL UNIQUE" + ")";

	public static final String SCRIPT_DELETE_TABLE_CARTAOCREDITO = "DROP TABLE IF EXISTS "
			+ NOME_TABELA;

	private SQLiteDatabase dataBase = null;

	private static DAO_CartaoCredito instance;

	public static DAO_CartaoCredito getInstance(Context context) {
		if (instance == null)
			instance = new DAO_CartaoCredito(context);
		return instance;
	}

	private DAO_CartaoCredito(Context context) {
		PersistenceHelper persistenceHelper = PersistenceHelper
				.getInstance(context);
		dataBase = persistenceHelper.getWritableDatabase();
	}

	public void salvar(CartaoCredito cartaoCredito) {
		ContentValues values = gerarContentValuesCartaoCredito(cartaoCredito);
		dataBase.insert(NOME_TABELA, null, values);
	}

	public List<CartaoCredito> recuperarTodos(int idUser) {
		String queryReturnAll = "SELECT * FROM " + NOME_TABELA;
		Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
		List<CartaoCredito> cartoesCredito = construirCartaoCreditoPorCursorList(cursor);

		return cartoesCredito;
	}

	public CartaoCredito recuperarPorId(int id) {
		String queryReturnById = "SELECT * FROM " + NOME_TABELA + "WHERE Id = " + id;
		Cursor cursor = dataBase.rawQuery(queryReturnById, null);
		CartaoCredito cartaoCredito = construirCartaoCreditoPorCursorSingle(cursor);

		return cartaoCredito;
	}

	public void deletar(CartaoCredito cartaoCredito) {

		String[] valoresParaSubstituir = { String.valueOf(cartaoCredito.id) };

		dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
	}

	public void editar(CartaoCredito cartaoCredito) {
		ContentValues valores = gerarContentValuesCartaoCredito(cartaoCredito);

		String[] valoresParaSubstituir = { String.valueOf(cartaoCredito.id) };

		dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?",
				valoresParaSubstituir);
	}

	public void fecharConexao() {
		if (dataBase != null && dataBase.isOpen())
			dataBase.close();
	}

	private List<CartaoCredito> construirCartaoCreditoPorCursorList(
			Cursor cursor) {
		List<CartaoCredito> CartaoCreditos = new ArrayList<CartaoCredito>();
		if (cursor == null)
			return CartaoCreditos;

		try {

			if (cursor.moveToFirst()) {
				do {

					int indexID = cursor.getColumnIndex(COLUNA_ID);
					int indexNome = cursor.getColumnIndex(COLUNA_NOME);
					int indexNumero = cursor.getColumnIndex(COLUNA_NUMERO);
					int indexValidade = cursor.getColumnIndex(COLUNA_VALIDADE);
					int indexUser = cursor.getColumnIndex(COLUNA_USER);
					int indexCodSeguranca = cursor
							.getColumnIndex(COLUNA_CODSEGURANCA);

					int id = cursor.getInt(indexID);
					String nome = cursor.getString(indexNome);
					String numero = cursor.getString(indexNumero);
					String validade = cursor.getString(indexValidade);
					String codSeguranca = cursor.getString(indexCodSeguranca);
					int user = cursor.getInt(indexUser);

					CartaoCredito CartaoCredito = new CartaoCredito(id, nome,
							numero, validade, codSeguranca, user);

					CartaoCreditos.add(CartaoCredito);

				} while (cursor.moveToNext());
			}

		} finally {
			cursor.close();
		}
		return CartaoCreditos;
	}

	private CartaoCredito construirCartaoCreditoPorCursorSingle(Cursor cursor) {
		CartaoCredito cartaoCredito = new CartaoCredito();
		if (cursor == null)
			return cartaoCredito;

		try {
			int indexID = cursor.getColumnIndex(COLUNA_ID);
			int indexNome = cursor.getColumnIndex(COLUNA_NOME);
			int indexNumero = cursor.getColumnIndex(COLUNA_NUMERO);
			int indexValidade = cursor.getColumnIndex(COLUNA_VALIDADE);
			int indexCodSeguranca = cursor.getColumnIndex(COLUNA_CODSEGURANCA);
			int indexIdPessoa = cursor.getColumnIndex(COLUNA_USER);

			int id = cursor.getInt(indexID);
			String nome = cursor.getString(indexNome);
			String numero = cursor.getString(indexNumero);
			String validade = cursor.getString(indexValidade);
			String codSeguranca = cursor.getString(indexCodSeguranca);
			int idUser = cursor.getInt(indexIdPessoa);

			cartaoCredito = new CartaoCredito(id, nome, numero,
					validade, codSeguranca, idUser);

		} finally {
			cursor.close();
		}
		return cartaoCredito;
	}

	private ContentValues gerarContentValuesCartaoCredito(
			CartaoCredito cartaoCredito) {
		ContentValues values = new ContentValues();
		values.put(COLUNA_CODSEGURANCA, cartaoCredito.cod_Seguranca);
		values.put(COLUNA_NOME, cartaoCredito.nome);
		values.put(COLUNA_NUMERO, cartaoCredito.numero);
		values.put(COLUNA_VALIDADE, cartaoCredito.validade);
		values.put(COLUNA_USER, cartaoCredito.idPessoa);

		return values;
	}
}
