package com.example.android_sqlite.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_sqlite.R;
import com.example.android_sqlite.models.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerArrayAdapter extends ArrayAdapter<Customer> {

    private Context context;
    private List<Customer> customers = new ArrayList<>() ;

    public CustomerArrayAdapter(@NonNull Context context, List<Customer> customers) {
        super(context, 0 ,customers);
        this.context = context;
        this.customers = customers;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View oneCustomerResourceFile = convertView;

        if(oneCustomerResourceFile==null)
            oneCustomerResourceFile= LayoutInflater.from(context).inflate(R.layout.one_customer,parent,false);


        Customer currentCustomer = this.customers.get(position);

        TextView customerName = oneCustomerResourceFile
                .findViewById(R.id.customerRowName);
        customerName.setText(currentCustomer.getName());

        TextView customerAge = (TextView)oneCustomerResourceFile
                .findViewById(R.id.customerRowAge);
        customerAge.setText(currentCustomer.getAge()+" years");

        TextView customerIsActive = (TextView)oneCustomerResourceFile
                .findViewById(R.id.customerRawIsActive);
        customerIsActive.setText("Actif/ve :"+(currentCustomer.isActive() == true ? " Oui " : " Non "));


        return oneCustomerResourceFile;
    }








}
