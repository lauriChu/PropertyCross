package com.lasalle.mdpa.lgarci.propertycross.database.model;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * Created by mgarc_000 on 21/08/2015.
 */
public abstract class DataBaseModel implements Serializable {
    public abstract String getTABLE();
    public abstract ContentValues getContentValues();
}
