package com.lasalle.mdpa.lgarci.propertycross.database.accessor;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lasalle.mdpa.lgarci.propertycross.database.component.Where;
import com.lasalle.mdpa.lgarci.propertycross.database.model.DataBaseModel;
import com.lasalle.mdpa.lgarci.propertycross.database.model.SearchVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FurruPi on 19/1/17.
 */

public class SearchDAO extends DataBaseAccessor{
    public static final String SQL_CREATE = "CREATE TABLE " + SearchVO.TABLE + " ( " +
            SearchVO.COL_ID + " integer primary key autoincrement, " +
            SearchVO.COL_TITLE + " varchar , " +
            SearchVO.COL_LAT + " float , " +
            SearchVO.COL_LNG + " float )";


    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + SearchVO.TABLE;

    public SearchDAO(Context context) { super(context); }

    public SearchDAO(Context context, SQLiteDatabase db) { super(context, db); }

    @Override
    public void create() throws SQLException {
        getWritableDatabase().execSQL(SQL_CREATE);
    }

    @Override
    public void drop() throws SQLException {
        getWritableDatabase().execSQL(SQL_DROP);
    }

    @Override
    public SearchVO get(String pk) throws SQLException {
        Cursor cursor = getWritableDatabase().query(
                SearchVO.TABLE, // Table
                SearchVO.COLUMNS, // Columns
                SearchVO.COL_ID + " = ?", // Where
                new String[]{pk}, //Where Values
                null, // Group By
                null, // Having
                null, // Order By
                null // Limit
        );

        if (cursor != null){
            if (cursor.moveToFirst()) {
                SearchVO result = new SearchVO(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getFloat(2),
                        cursor.getFloat(3)
                );
                return result;
            }
        }
        return null;
    }

    @Override
    public List<DataBaseModel> select(List<Where> wheres) throws SQLException {
        Cursor cursor = getWritableDatabase().query(
                SearchVO.TABLE, // Table
                SearchVO.COLUMNS, // Columns
                Where.formatWheresColumnsForSQL(wheres), // Where
                Where.formatWheresValuesForSQL(wheres), //Where Values
                null, // Group By
                null, // Having
                SearchVO.COL_ID + "DESC", // Order By
                null // Limit
        );

        if (cursor != null){
            if (cursor.moveToFirst()){
                List<DataBaseModel> results = new ArrayList<>();
                do {
                    SearchVO result = new SearchVO(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getFloat(2),
                            cursor.getFloat(3)
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
        SearchVO mVo = (SearchVO) vo;
        List<Where> wheres = new ArrayList<>();
        wheres.add(new Where(SearchVO.COL_ID, Where.Operator.EQUAL, mVo.getId()));
        return getWritableDatabase().update(
                vo.getTABLE(),
                vo.getContentValues(),
                Where.formatWheresColumnsForSQL(wheres),
                Where.formatWheresValuesForSQL(wheres)
        );
    }

    @Override
    public long delete(DataBaseModel vo) throws SQLException {
        SearchVO mVo = (SearchVO) vo;
        List<Where> wheres = new ArrayList<>();
        wheres.add(new Where(SearchVO.COL_ID, Where.Operator.EQUAL, mVo.getId()));
        return getWritableDatabase().delete(
                vo.getTABLE(),
                Where.formatWheresColumnsForSQL(wheres),
                Where.formatWheresValuesForSQL(wheres)
        );
    }
}