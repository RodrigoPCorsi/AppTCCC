package br.com.dao;

import android.content.Context;
import android.util.Log;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

public class Db4oHelper {
	private ObjectContainer database = null;

	private String DATABASE_NAME = "tccc.db4o";

	public Db4oHelper(Context context) {
		try {
			if (database == null || database.ext().isClosed()) {
				database = Db4oEmbedded.openFile(config(),
						db4oDBFullPath(context));
			}
		} catch (Exception ie) {
			Log.e(Db4oHelper.class.getName(), ie.getMessage());
		}
	}

	/**
	 *  Cria uma nova instância de configuração
	 * @return
	 */
	private EmbeddedConfiguration config() {
		EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
		return configuration;
	}

	/**
	 * 
	 * @param ctx
	 * @return retorna o caminho da base
	 */
	private String db4oDBFullPath(Context ctx) {
		return ctx.getDir("data", 0) + "/" + DATABASE_NAME;
	}

	public void commit() {
		database.commit();
	}

	public void rollBack() {
		database.rollback();
	}

	public void close() {
		if (this.database != null) {
			this.database.close();
		}
	}

	/**
	 * 	
	 * @return A data base para os providers utilizarem.
	 */
	public ObjectContainer getDatabase() {
		return this.database;
	}
}