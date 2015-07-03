package com.example.mauricioarce.addressbook;

import android.provider.BaseColumns;

/**
 * Created by Mauricio Arce on 02/07/2015.
 */
public class AddressContract {

    public static final String DATABASE_NAME = "Address Book";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Address";

    public class Columns {
        public static final String CONTACT_NAME = "contact";
        public static final String CONTACT_NUMBER = "number";
        public static final String CONTACT_EMAIL = "mails";
        public static final String _ID = BaseColumns._ID;
    }
}
