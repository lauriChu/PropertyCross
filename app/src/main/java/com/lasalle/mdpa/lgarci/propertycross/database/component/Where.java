package com.lasalle.mdpa.lgarci.propertycross.database.component;

import java.util.List;

/**
 * Created by mgarc_000 on 21/08/2015.
 */
public class Where {
    public enum Operator {EQUAL, NOT_EQUAL, NULL, NOT_NULL, LIKE, IN};

    public String column;
    public Operator operator;
    public Object value;

    public Where(String column, Operator operator, Object value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    public static String formatWheresColumnsForSQL(List<Where> list) {
        String result = null;
        if(list!=null) {
            for (Where where : list) {
                String sql = where.column;
                switch (where.operator) {
                    case EQUAL:
                        sql += "=?";
                        break;
                    case NOT_EQUAL:
                        sql += "<>?";
                        break;
                    case NULL:
                        sql += " IS NULL";
                        break;
                    case NOT_NULL:
                        sql += " IS NOT NULL";
                        break;
                    case LIKE:
                        sql += " LIKE ?";
                        break;
                    case IN:
                        String[] wheresIn = (String[])where.value;
                        sql += " IN (";
                        for (int i=0; i<wheresIn.length; i++) {
                            if(i==0) sql += "?";
                            else sql += ",?";
                        }
                        sql += ")";
                        break;
                }

                if(result==null) result = sql;
                else result += " AND " + sql;
            }
        }
        return result;
    }

    public static String[] formatWheresValuesForSQL(List<Where> list) {
        String[] result = null;
        if(list!=null) {
            // Determinamos la longitud
            int size = 0;
            for (Where where : list) {
                switch (where.operator) {
                    case EQUAL:
                        size++;
                        break;
                    case NOT_EQUAL:
                        size++;
                        break;
                    case NULL:
                        break;
                    case NOT_NULL:
                        break;
                    case LIKE:
                        size++;
                        break;
                    case IN:
                        String[] wheresIn = (String[])where.value;
                        size = size + wheresIn.length;
                        break;
                }
            }

            if(size>0) {
                result = new String[size];
                int index = 0;
                for (Where where : list) {
                    switch (where.operator) {
                        case EQUAL:
                            result[index] = where.value.toString();
                            index++;
                            break;
                        case NOT_EQUAL:
                            result[index] = where.value.toString();
                            index++;
                            break;
                        case NULL:
                            break;
                        case NOT_NULL:
                            break;
                        case LIKE:
                            result[index] = "%" + where.value.toString() + "%";
                            index++;
                            break;
                        case IN:
                            String sqlWhereIn = null;
                            String[] wheresIn = (String[])where.value;
                            for (String whereIn : wheresIn) {
                                result[index] = whereIn;
                                index++;
                            }
                            break;
                    }
                }
            }
        }
        return result;
    }
}
