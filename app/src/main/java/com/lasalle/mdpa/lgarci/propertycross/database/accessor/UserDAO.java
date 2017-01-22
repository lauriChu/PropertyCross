package com.lasalle.mdpa.lgarci.propertycross.database.accessor;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lasalle.mdpa.lgarci.propertycross.database.component.Where;
import com.lasalle.mdpa.lgarci.propertycross.database.model.DataBaseModel;
import com.lasalle.mdpa.lgarci.propertycross.database.model.UserVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FurruPi on 19/1/17.
 */

public class UserDAO extends DataBaseAccessor {

    public static final String SQL_CREATE = "CREATE TABLE " + UserVO.TABLE + " ( " +
            UserVO.COL_ID + " varchar primary key, " +
            UserVO.COL_TOKEN + " varchar , " +
            UserVO.COL_EMAIL + " varchar , " +
            UserVO.COL_NAME + " varchar , " +
            UserVO.COL_SURNAME + " varchar , " +
            UserVO.COL_PHONE + " varchar , " +
            UserVO.COL_PHOTO + " varchar )";


    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + UserVO.TABLE;

    public UserDAO(Context context) { super(context); }

    public UserDAO(Context context, SQLiteDatabase db) { super(context, db); }

    @Override
    public void create() throws SQLException {
        getWritableDatabase().execSQL(SQL_CREATE);
    }

    @Override
    public void drop() throws SQLException {
        getWritableDatabase().execSQL(SQL_DROP);
    }

    @Override
    public UserVO get(String pk) throws SQLException {
        Cursor cursor = getWritableDatabase().query(
                UserVO.TABLE, // Table
                UserVO.COLUMNS, // Columns
                UserVO.COL_ID + " = ?", // Where
                new String[]{pk}, //Where Values
                null, // Group By
                null, // Having
                null, // Order By
                null // Limit
        );

        if (cursor != null){
            if (cursor.moveToFirst()) {
                UserVO result = new UserVO(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
                return result;
            }
        }
        return null;
    }

    @Override
    public List<DataBaseModel> select(List<Where> wheres) throws SQLException {
        Cursor cursor = getWritableDatabase().query(
                UserVO.TABLE, // Table
                UserVO.COLUMNS, // Columns
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
                    UserVO result = new UserVO(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6)
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
        UserVO mVo = (UserVO) vo;
        List<Where> wheres = new ArrayList<>();
        wheres.add(new Where(UserVO.COL_ID, Where.Operator.EQUAL, mVo.getId()));
        return getWritableDatabase().update(
                vo.getTABLE(),
                vo.getContentValues(),
                Where.formatWheresColumnsForSQL(wheres),
                Where.formatWheresValuesForSQL(wheres)
        );
    }

    @Override
    public long delete(DataBaseModel vo) throws SQLException {
        UserVO mVo = (UserVO) vo;
        List<Where> wheres = new ArrayList<>();
        wheres.add(new Where(UserVO.COL_ID, Where.Operator.EQUAL, mVo.getId()));
        return getWritableDatabase().delete(
                vo.getTABLE(),
                Where.formatWheresColumnsForSQL(wheres),
                Where.formatWheresValuesForSQL(wheres)
        );
    }
}