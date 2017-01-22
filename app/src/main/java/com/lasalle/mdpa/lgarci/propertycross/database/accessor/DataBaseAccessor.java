package com.lasalle.mdpa.lgarci.propertycross.database.accessor;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lasalle.mdpa.lgarci.propertycross.database.manager.DataBaseManager;
import com.lasalle.mdpa.lgarci.propertycross.database.model.DataBaseModel;
import com.lasalle.mdpa.lgarci.propertycross.database.components.Where;

import java.util.List;

/**
 * Created by lgarcia on 21/01/2017.
 */
public abstract class DataBaseAccessor {
    protected Context context;
    protected DataBaseManager dataBaseManager;
    protected SQLiteDatabase dataBase;

    public DataBaseAccessor(Context context) {
        this.context = context;
        this.dataBaseManager = new DataBaseManager(context, getVersion());
    }

    public DataBaseAccessor(Context context,SQLiteDatabase dataBase) {
        this.context = context;
        this.dataBase = dataBase;
    }

    public abstract void create() throws SQLException;
    public abstract void drop() throws SQLException;
    public void truncate() throws SQLException{
        drop();
        create();
    }

    public abstract DataBaseModel get(String pk) throws SQLException;
    public abstract List<DataBaseModel> select(List<Where> wheres) throws SQLException;
    public abstract long insert(DataBaseModel vo) throws SQLException;
    public abstract long update(DataBaseModel vo) throws SQLException;
    public abstract long delete(DataBaseModel vo) throws SQLException;

    public SQLiteDatabase getWritableDatabase() {
        if(dataBase == null) {
            return dataBaseManager.getWritableDatabase();
        } else{
            return dataBase;
        }
    }


    public int getVersion() {
        return 1;
    //    return AppVersion.getCode(context);
    }

}
