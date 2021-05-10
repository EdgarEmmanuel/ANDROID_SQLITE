package com.example.android_sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_sqlite.data.CustomerDao;
import com.example.android_sqlite.data.Database;
import com.example.android_sqlite.helpers.CustomerArrayAdapter;
import com.example.android_sqlite.models.Customer;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    MaterialButton viewAll_button,submit_button;
    ListView list_cutomers;
    SwitchMaterial switch_button;
    TextInputLayout customer_age,customer_name;
    TextView displayCustomerName;
    private CustomerArrayAdapter customerArrayAdapter;
    private List<Customer> customerList;
    private CustomerDao customerDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        // get all the references
        submit_button = findViewById(R.id.submit_button);
        viewAll_button = findViewById(R.id.viewAll_button);
        switch_button = findViewById(R.id.switch_button);
        list_cutomers = findViewById(R.id.list_data);
        customer_name = findViewById(R.id.customer_name);
        displayCustomerName =findViewById(R.id.customerRowAge);
        customer_age = findViewById(R.id.customer_age);

        //on create run the function to diaply all the customer
        this.displayAllCustomer(findViewById(R.id.list_data));

        list_cutomers.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long l) {
                Customer clickedCustomer = customerList.get(position);
                Boolean isDeleted = customerDao.deleteOneCustomer(clickedCustomer);
                String resultDeleteOperation =
                        (isDeleted) ? "USER DELETED SUCCESSFULLY " : " TRY LATER ";
                Toast.makeText(HomePageActivity.this,
                        resultDeleteOperation,Toast.LENGTH_LONG).show();
            }
        });



    }

    /**
     * function to validate field
     * @param field
     * @return
     */
    public Boolean validateField(TextInputLayout field, String fieldName){
        String fieldValue = field.getEditText().getText().toString().trim();

        if(fieldValue.equalsIgnoreCase("")){
            field.setError("the field "+fieldName+" should not be empty");
            return false;
        }
        return true;
    }


    public void EmptyCustomerFormData(){
        customer_age.getEditText().setText("");
        customer_name.getEditText().setText("");
    }


    /**
     * this function permits to insert one customer
     * @param view
     */
    public void insertCustomer(View view){
        Customer customer ;
        CustomerDao customerDao = new CustomerDao(HomePageActivity.this);
        // we verify if the two field are fill
        if(!validateField(customer_name,"Customer Name") |
                !validateField(customer_age,"Customer Age")){
            return ;
        }else{
            Database database = new Database(HomePageActivity.this);

           customer = new Customer();
            customer.setId(-1);
            customer.setAge(Integer.parseInt(customer_age.getEditText().getText().toString().trim()));
            customer.setName(customer_name.getEditText().getText().toString().trim());
            customer.setActive(switch_button.isChecked());


            // save the instance in the database and return a result
            String data = customerDao.insertOneCustomer(customer) ? "SUCCESS" : "ERROR";

            //Toast.makeText(HomePageActivity.this,"SUCCESS "+data,Toast.LENGTH_LONG).show();
            displayAllCustomer(view);
            EmptyCustomerFormData();
        }
    }


    /**
     * this function display all the customers in the database
     * @param view
     */
    public void displayAllCustomer(View view){
        customerDao = new CustomerDao(HomePageActivity.this);

        customerList = customerDao.getAllCustomer();

        customerArrayAdapter = new CustomerArrayAdapter(this,
                customerList);

        list_cutomers.setAdapter(customerArrayAdapter);


    }













}
