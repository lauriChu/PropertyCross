package com.lasalle.mdpa.lgarci.propertycross.database.accessor;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lasalle.mdpa.lgarci.propertycross.database.components.Where;
import com.lasalle.mdpa.lgarci.propertycross.database.model.CommentVO;
import com.lasalle.mdpa.lgarci.propertycross.database.model.DataBaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FurruPi on 19/1/17.
 */

public class CommentDAO extends DataBaseAccessor{
    public static final String SQL_CREATE = "CREATE TABLE " + CommentVO.TABLE + " ( " +
            CommentVO.COL_ID + " integer primary key autoincrement, " +
            CommentVO.COL_ID_PROPERTY + " varchar , " +
            CommentVO.COL_USER + " varchar , " +
            CommentVO.COL_DATE + " varchar , " +
            CommentVO.COL_COMMENT + " varchar )";


    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + CommentVO.TABLE;

    public CommentDAO(Context context) { super(context); }

    public CommentDAO(Context context, SQLiteDatabase db) { super(context, db); }

    @Override
    public void create() throws SQLException {
        getWritableDatabase().execSQL(SQL_CREATE);
    }

    @Override
    public void drop() throws SQLException {
        getWritableDatabase().execSQL(SQL_DROP);
    }

    @Override
    public CommentVO get(String pk) throws SQLException {
        Cursor cursor = getWritableDatabase().query(
                CommentVO.TABLE, // Table
                CommentVO.COLUMNS, // Columns
                CommentVO.COL_ID + " = ?", // Where
                new String[]{pk}, //Where Values
                null, // Group By
                null, // Having
                null, // Order By
                null // Limit
        );

        if (cursor != null){
            if (cursor.moveToFirst()) {
                CommentVO result = new CommentVO(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
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
                CommentVO.TABLE, // Table
                CommentVO.COLUMNS, // Columns
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
                    CommentVO result = new CommentVO(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
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
        CommentVO mVo = (CommentVO) vo;
        List<Where> wheres = new ArrayList<>();
        wheres.add(new Where(CommentVO.COL_ID, Where.Operator.EQUAL, mVo.getId()));
        return getWritableDatabase().update(
                vo.getTABLE(),
                vo.getContentValues(),
                Where.formatWheresColumnsForSQL(wheres),
                Where.formatWheresValuesForSQL(wheres)
        );
    }

    @Override
    public long delete(DataBaseModel vo) throws SQLException {
        CommentVO mVo = (CommentVO) vo;
        List<Where> wheres = new ArrayList<>();
        wheres.add(new Where(CommentVO.COL_ID, Where.Operator.EQUAL, mVo.getId()));
        return getWritableDatabase().delete(
                vo.getTABLE(),
                Where.formatWheresColumnsForSQL(wheres),
                Where.formatWheresValuesForSQL(wheres)
        );
    }
}
