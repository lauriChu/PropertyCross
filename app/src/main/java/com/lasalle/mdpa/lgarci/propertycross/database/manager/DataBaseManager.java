package com.lasalle.mdpa.lgarci.propertycross.database.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lasalle.mdpa.lgarci.propertycross.database.accessor.CommentDAO;
import com.lasalle.mdpa.lgarci.propertycross.database.accessor.MediaDAO;
import com.lasalle.mdpa.lgarci.propertycross.database.accessor.PropertyDAO;
import com.lasalle.mdpa.lgarci.propertycross.database.accessor.SearchDAO;
import com.lasalle.mdpa.lgarci.propertycross.database.accessor.UserDAO;

/**
 * Created by mgarc_000 on 21/08/2015.
 */
public class DataBaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PropertyCrossDB";
    private int _version = 1;
    private Context context;

    public DataBaseManager(Context contexto, int version) {
        super(contexto, DATABASE_NAME, null, version);
        context = contexto;
        this._version = version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(UserDAO.SQL_DROP);
        db.execSQL(UserDAO.SQL_CREATE);

        db.execSQL(CommentDAO.SQL_DROP);
        db.execSQL(CommentDAO.SQL_CREATE);

        db.execSQL(MediaDAO.SQL_DROP);
        db.execSQL(MediaDAO.SQL_CREATE);

        db.execSQL(PropertyDAO.SQL_DROP);
        db.execSQL(PropertyDAO.SQL_CREATE);

        db.execSQL(SearchDAO.SQL_DROP);
        db.execSQL(SearchDAO.SQL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: Metodo reflection para pasar actualizar entre versiones.
        System.out.println("Actualizacion de la versión " + oldVersion + " a la versión " + newVersion);
    }

}
