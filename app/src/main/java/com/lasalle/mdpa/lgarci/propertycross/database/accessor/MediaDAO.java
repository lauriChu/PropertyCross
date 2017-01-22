package com.lasalle.mdpa.lgarci.propertycross.database.accessor;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lasalle.mdpa.lgarci.propertycross.database.component.Where;
import com.lasalle.mdpa.lgarci.propertycross.database.model.DataBaseModel;
import com.lasalle.mdpa.lgarci.propertycross.database.model.MediaVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FurruPi on 19/1/17.
 */

public class MediaDAO extends DataBaseAccessor{
    public static final String SQL_CREATE = "CREATE TABLE " + MediaVO.TABLE + " ( " +
            MediaVO.COL_Path + " varchar primary key, " +
            MediaVO.COL_IdProperty + " varchar , " +
            MediaVO.COL_IdComment + " integer , " +
            MediaVO.COL_Type + " varchar , " +
            MediaVO.COL_Value + " varchar )";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + MediaVO.TABLE;

    public MediaDAO(Context context) { super(context); }

    public MediaDAO(Context context, SQLiteDatabase db) { super(context, db); }

    @Override
    public void create() throws SQLException {
        getWritableDatabase().execSQL(SQL_CREATE);
    }

    @Override
    public void drop() throws SQLException {
        getWritableDatabase().execSQL(SQL_DROP);
    }

    @Override
    public MediaVO get(String pk) throws SQLException {
        Cursor cursor = getWritableDatabase().query(
                MediaVO.TABLE, // Table
                MediaVO.COLUMNS, // Columns
                MediaVO.COL_Path + " = ?", // Where
                new String[]{pk}, //Where Values
                null, // Group By
                null, // Having
                null, // Order By
                null // Limit
        );

        if (cursor != null){
            if (cursor.moveToFirst()) {
                MediaVO result = new MediaVO(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                return result;
            }
        }
        return null;
    }

    @Override
    public List<DataBaseModel> select(List<Where> wheres) throws SQLException {
        Cursor cursor = getWritableDatabase().query(
                MediaVO.TABLE, // Table
                MediaVO.COLUMNS, // Columns
                Where.formatWheresColumnsForSQL(wheres), // Where
                Where.formatWheresValuesForSQL(wheres), //Where Values
                null, // Group By
                null, // Having
                null, // Order By
                null // Limit
        );

        if (cursor != null){
            if (cursor.moveToFirst()){
                List<DataBaseModel> results = new ArrayList<>();
                do {
                    MediaVO result = new MediaVO(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    );
                    results.add(result);
                } while (cursor.moveToNext());
                return results;
            }
        }
        return null;
    }

    @Override
    public long insert(DataBaseModel vo) throws SQLException {
        long pk = getWritableDatabase().insert(vo.getTABLE(), null, vo.getContentValues());
        return pk;
    }

    @Override
    public long update(DataBaseModel vo) throws SQLException {
        MediaVO mVo = (MediaVO) vo;
        List<Where> wheres = new ArrayList<>();
        wheres.add(new Where(MediaVO.COL_Path, Where.Operator.EQUAL, mVo.getPath()));
        return getWritableDatabase().update(
                vo.getTABLE(),
                vo.getContentValues(),
                Where.formatWheresColumnsForSQL(wheres),
                Where.formatWheresValuesForSQL(wheres)
        );
    }

    @Override
    public long delete(DataBaseModel vo) throws SQLException {
        MediaVO mVo = (MediaVO) vo;
        List<Where> wheres = new ArrayList<>();
        wheres.add(new Where(MediaVO.COL_Path, Where.Operator.EQUAL, mVo.getPath()));
        return getWritableDatabase().delete(
                vo.getTABLE(),
                Where.formatWheresColumnsForSQL(wheres),
                Where.formatWheresValuesForSQL(wheres)
        );
    }
}
