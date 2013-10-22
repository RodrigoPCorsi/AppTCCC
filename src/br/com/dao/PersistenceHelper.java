package br.com.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersistenceHelper extends SQLiteOpenHelper{
	public static final String NOME_BANCO =  "Tccc";
    public static final int VERSAO =  2;
     
    private static PersistenceHelper instance;
     
    private PersistenceHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }
     
    public static PersistenceHelper getInstance(Context context) {
        if(instance == null)
            instance = new PersistenceHelper(context);
         
        return instance;
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DAO_Pessoa.SCRIPT_CREATE_TABLE_PESSOA);
        db.execSQL(DAO_CartaoCredito.SCRIPT_CREATE_TABLE_CARTAOCREDITO);
        db.execSQL(DAO_CartaoTccc.SCRIPT_CREATE_TABLE_CARTAOTCC);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DAO_Pessoa.SCRIPT_DELETE_TABLE_PESSOA);
        db.execSQL(DAO_CartaoTccc.SCRIPT_DELETE_TABLE_CARTAOTCC);
        db.execSQL(DAO_CartaoCredito.SCRIPT_DELETE_TABLE_CARTAOCREDITO);
        onCreate(db);
    }

}
