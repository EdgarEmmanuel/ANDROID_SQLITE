package com.example.android_sqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android_sqlite.models.Customer;
import com.example.android_sqlite.utilities.TableCustomerUtilities;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    // ====== sqlite database ===========================================
    SQLiteOpenHelper database = null;
    private SQLiteDatabase readAnddeleteDatabaseDataInstance;
    // ====================================================================

    public CustomerDao(Context context){
        database = new Database(context).getInstance();
    }

    public Boolean insertOneCustomer(Customer customer){
        SQLiteDatabase insertDatabaseData= database.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(TableCustomerUtilities.COLUMN_CUSTOMER_AGE,customer.getAge());
        contentValues.put(TableCustomerUtilities.COLUMN_CUSTOMER_NAME,customer.getName());
        contentValues.put(TableCustomerUtilities.COLUMN_IS_ACTIVE,customer.isActive());

        long result = insertDatabaseData.insert(TableCustomerUtilities.CUSTOMER_TABLE,null,contentValues);

        if(result==-1) return false;
        return true;
    }

    public Boolean deleteOneCustomer(Customer customerToDelete){

        readAnddeleteDatabaseDataInstance = database.getWritableDatabase();

        if(getOneCustomer(customerToDelete.getId())){
            String whereClause = TableCustomerUtilities.COLUMN_ID+ "=?";
            String whereArgs[] = {""+customerToDelete.getId()};
            readAnddeleteDatabaseDataInstance
                    .delete(
                            TableCustomerUtilities.CUSTOMER_TABLE,
                            whereClause,
                            whereArgs
                    );
            return true;
        }else{
            return false;
        }

    }

    public Boolean getOneCustomer(int idCustomer){

        String queryGetAllCustomer = "SELECT * FROM "+
                TableCustomerUtilities.CUSTOMER_TABLE+" WHERE "
                +TableCustomerUtilities.COLUMN_ID+
                "="+idCustomer;


        readAnddeleteDatabaseDataInstance = database.getWritableDatabase();

        Cursor cursor = readAnddeleteDatabaseDataInstance.rawQuery(queryGetAllCustomer,null);

        if(cursor.moveToFirst()){
            return true;
        }
        return false;
    }


    public List<Customer> getAllCustomer(){
        List<Customer> customersList = new ArrayList<>();
        int isActive = 1;

        String queryGetAllCustomer = "SELECT * FROM "+ TableCustomerUtilities.CUSTOMER_TABLE;


        SQLiteDatabase readDatabaseData = database.getReadableDatabase();

        Cursor cursor = readDatabaseData.rawQuery(queryGetAllCustomer,null);

        if(cursor.moveToFirst()){

            do{
                Customer customer = new Customer();
                customer.setId(cursor.getInt(0));
                customer.setName(cursor.getString(1));
                customer.setAge(cursor.getInt(2));
                customer.setActive(cursor.getInt(3) == isActive ? true : false);

                customersList.add(customer);
            }while(cursor.moveToNext());

        }else{

        }


        // close the cursor and the connection to the database
        cursor.close();
        readDatabaseData.close();
        return customersList;
    }

}
