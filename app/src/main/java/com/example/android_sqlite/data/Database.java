package com.example.android_sqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.android_sqlite.utilities.TableCustomerUtilities;

public class Database extends SQLiteOpenHelper {


    public Database(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    // it is called when we access the database for the first time
    @Override
    public void onCreate(SQLiteDatabase SQLiteDb) {
        String SqlitStatement = "CREATE TABLE  "+ TableCustomerUtilities.CUSTOMER_TABLE+"( " +
                ""+ TableCustomerUtilities.COLUMN_ID+" INTEGER  PRIMARY KEY AUTOINCREMENT , "+
                TableCustomerUtilities.COLUMN_CUSTOMER_NAME+" TEXT , " +
                ""+ TableCustomerUtilities.COLUMN_CUSTOMER_AGE +" INTEGER, "
                + TableCustomerUtilities.COLUMN_IS_ACTIVE+" INTEGER)";

        SQLiteDb.execSQL(SqlitStatement);
    }

    // it prevents the app to crash when we change the database design
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //if(i>i1) onCreate(sqLiteDatabase);
    }

    public Database getInstance(){
        return this;
    }

}
