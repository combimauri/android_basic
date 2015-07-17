package com.example.mauricioarce.catfacts;

import android.provider.BaseColumns;

/**
 * Created by Mauricio Arce on 16/07/2015.
 */
public class FactsContract {

    public static final String DATABASE_NAME = "Facts Book";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Facts";

    public class Columns {
        public static final String FACT_CAT = "fact";
        public static final String _ID = BaseColumns._ID;
    }
}
