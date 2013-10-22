package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.modelo.CartaoCredito;
import br.com.modelo.CartaoTccc;

public class DAO_CartaoTccc {
	public final static String NOME_TABELA = "CartaoTccc";
	public final static String COLUNA_ID = "Id";
	public final static String COLUNA_USER = "IdUser";
	public final static String COLUNA_NOME = "Nome";
	public final static String COLUNA_NUMERO = "Numero";

	public final static String SCRIPT_CREATE_TABLE_CARTAOTCC = "CREATE TABLE "
			+ NOME_TABELA
			+ "("
			+ COLUNA_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUNA_NOME
			+ " TEXT,"
			+ COLUNA_USER
			+ " SMALLINT NOT NULL UNIQUE, "
			+ COLUNA_NUMERO + " TEXT NOT NULL UNIQUE" + ")";

	public static final String SCRIPT_DELETE_TABLE_CARTAOTCC = "DROP TABLE IF EXISTS "
			+ NOME_TABELA;

	private SQLiteDatabase dataBase = null;

	private static DAO_CartaoTccc instance;

	public static DAO_CartaoTccc getInstance(Context context) {
		if (instance == null)
			instance = new DAO_CartaoTccc(context);
		return instance;
	}

	private DAO_CartaoTccc(Context context) {
		PersistenceHelper persistenceHelper = PersistenceHelper
				.getInstance(context);
		dataBase = persistenceHelper.getWritableDatabase();
	}

	public void salvar(CartaoTccc cartaoTccc) {
		ContentValues values = gerarContentValuesCartaoTcc(cartaoTccc);
		dataBase.insert(NOME_TABELA, null, values);
	}

	public List<CartaoTccc> recuperarTodos(int idUser) {
		String queryReturnAll = "SELECT * FROM " + NOME_TABELA + " WHERE " + COLUNA_USER + " = " + idUser;
		Cursor cursor = dataBase.rawQuery(queryReturnAll, null);
		List<CartaoTccc> cartoesCredito = construirCartaoTcccPorCursor(cursor);

		return cartoesCredito;
	}

	public CartaoTccc recuperarPorId(int id) {
		String queryReturnId = "SELECT * FROM " + NOME_TABELA + "WHERE Id = " + id;
		Cursor cursor = dataBase.rawQuery(queryReturnId, null);
		return construirCartaoTcccPorCursorSingle(cursor);
	}

	public void deletar(CartaoCredito cartaoCredito) {

		String[] valoresParaSubstituir = { String.valueOf(cartaoCredito.id) };

		dataBase.delete(NOME_TABELA, COLUNA_ID + " =  ?", valoresParaSubstituir);
	}

	public void editar(CartaoTccc cartaoTccc) {
		ContentValues valores = gerarContentValuesCartaoTcc(cartaoTccc);

		String[] valoresParaSubstituir = { String.valueOf(cartaoTccc.id) };

		dataBase.update(NOME_TABELA, valores, COLUNA_ID + " = ?",
				valoresParaSubstituir);
	}

	public void fecharConexao() {
		if (dataBase != null && dataBase.isOpen())
			dataBase.close();
	}

	private List<CartaoTccc> construirCartaoTcccPorCursor(Cursor cursor) {
		List<CartaoTccc> CartoesTccc = new ArrayList<CartaoTccc>();
		if (cursor == null)
			return CartoesTccc;

		try {

			if (cursor.moveToFirst()) {
				do {

					int indexID = cursor.getColumnIndex(COLUNA_ID);
					int indexNome = cursor.getColumnIndex(COLUNA_NOME);
					int indexNumero = cursor.getColumnIndex(COLUNA_NUMERO);
					

					int id = cursor.getInt(indexID);
					String nome = cursor.getString(indexNome);
					String numero = cursor.getString(indexNumero);

					CartaoTccc cartaoTccc = new CartaoTccc(id, nome, numero, 0);

					CartoesTccc.add(cartaoTccc);

				} while (cursor.moveToNext());
			}

		} finally {
			cursor.close();
		}
		return CartoesTccc;
	}

	private CartaoTccc construirCartaoTcccPorCursorSingle(Cursor cursor) {
		CartaoTccc cRetorno = new CartaoTccc();
		if (cursor == null)
			return cRetorno;

		try {

			int indexID = cursor.getColumnIndex(COLUNA_ID);
			int indexNome = cursor.getColumnIndex(COLUNA_NOME);
			int indexNumero = cursor.getColumnIndex(COLUNA_NUMERO);

			int id = cursor.getInt(indexID);
			String nome = cursor.getString(indexNome);
			String numero = cursor.getString(indexNumero);

			cRetorno = new CartaoTccc(id, nome, numero, 0);

		} finally {
			cursor.close();
		}
		return cRetorno;
	}

	private ContentValues gerarContentValuesCartaoTcc(CartaoTccc cartaoTcc) {
		ContentValues values = new ContentValues();
		values.put(COLUNA_NOME, cartaoTcc.nome);
		values.put(COLUNA_NUMERO, cartaoTcc.num_Cartao);

		return values;
	}
}
