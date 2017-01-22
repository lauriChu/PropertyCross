package com.lasalle.mdpa.lgarci.propertycross.database.accessor;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lasalle.mdpa.lgarci.propertycross.database.component.Where;
import com.lasalle.mdpa.lgarci.propertycross.database.model.DataBaseModel;
import com.lasalle.mdpa.lgarci.propertycross.database.model.PropertyVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FurruPi on 19/1/17.
 */

public class PropertyDAO extends DataBaseAccessor{
    public static final String SQL_CREATE = "CREATE TABLE " +  PropertyVO.TABLE + " ( " +
            PropertyVO.COL_ID + " varchar primary key, " +
            PropertyVO.COL_TITLE + " varchar , " +
            PropertyVO.COL_DESCRIPTION + " varchar , " +
            PropertyVO.COL_ADRESS + " varchar , " +
            PropertyVO.COL_ZIPCODE + " varchar , " +
            PropertyVO.COL_CITY + " varchar , " +
            PropertyVO.COL_PRICE + " varchar , " +
            PropertyVO.COL_SIZE + " varchar , " +
            PropertyVO.COL_TYPE + " varchar , " +
            PropertyVO.COL_MAIL + " varchar , " +
            PropertyVO.COL_PHONE + " varchar , " +
            PropertyVO.COL_LAT + " float , " +
            PropertyVO.COL_LNG + " float , " +
            PropertyVO.COL_DISTANCE + " float , " +
            PropertyVO.COL_FAVORITE + " varchar , " +
            PropertyVO.COL_LAST_REFRESH + " varchar )";


    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + PropertyVO.TABLE;

    public PropertyDAO(Context context) { super(context); }

    public PropertyDAO(Context context, SQLiteDatabase db) { super(context, db); }

    @Override
    public void create() throws SQLException {
        getWritableDatabase().execSQL(SQL_CREATE);
    }

    @Override
    public void drop() throws SQLException {
        getWritableDatabase().execSQL(SQL_DROP);
    }

    @Override
    public PropertyVO get(String pk) throws SQLException {
        Cursor cursor = getWritableDatabase().query(
                PropertyVO.TABLE, // Table
                PropertyVO.COLUMNS, // Columns
                PropertyVO.COL_ID + " = ?", // Where
                new String[]{pk}, //Where Values
                null, // Group By
                null, // Having
                null, // Order By
                null // Limit
        );

        if (cursor != null){
            if (cursor.moveToFirst()) {
                PropertyVO result = new PropertyVO(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getFloat(11),
                        cursor.getFloat(12),
                        cursor.getFloat(13),
                        cursor.getString(14),
                        cursor.getString(15)
                );
                return result;
            }
        }
        return null;
    }

    @Override
    public List<DataBaseModel> select(List<Where> wheres) throws SQLException {
        Cursor cursor = getWritableDatabase().query(
                PropertyVO.TABLE, // Table
                PropertyVO.COLUMNS, // Columns
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
                    PropertyVO result = new PropertyVO(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8),
                            cursor.getString(9),
                            cursor.getString(10),
                            cursor.getFloat(11),
                            cursor.getFloat(12),
                            cursor.getFloat(13),
                            cursor.getString(14),
                            cursor.getString(15)
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
        PropertyVO mVo = (PropertyVO) vo;
        List<Where> wheres = new ArrayList<>();
        wheres.add(new Where(PropertyVO.COL_ID, Where.Operator.EQUAL, mVo.getId()));
        return getWritableDatabase().update(
                vo.getTABLE(),
                vo.getContentValues(),
                Where.formatWheresColumnsForSQL(wheres),
                Where.formatWheresValuesForSQL(wheres)
        );
    }

    @Override
    public long delete(DataBaseModel vo) throws SQLException {
        PropertyVO mVo = (PropertyVO) vo;
        List<Where> wheres = new ArrayList<>();
        wheres.add(new Where(PropertyVO.COL_ID, Where.Operator.EQUAL, mVo.getId()));
        return getWritableDatabase().delete(
                vo.getTABLE(),
                Where.formatWheresColumnsForSQL(wheres),
                Where.formatWheresValuesForSQL(wheres)
        );
    }
}
