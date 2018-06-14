package com.example.shivam.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.shivam.recyclerview.dataobject.CustomPojo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String TAG =this.getClass().toString();

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<CustomPojo> customPojosList = new ArrayList<CustomPojo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
     //   Log.i(TAG, "onCreate: List Size ====>"+populateRecyclerViewValues().size());
        customAdapter = new CustomAdapter(populateRecyclerViewValues(),MainActivity.this);
        recyclerView.setAdapter(customAdapter);
    }

    private ArrayList<CustomPojo> populateRecyclerViewValues() {

        for(int iter=1;iter<=50;iter++) {
            //Creating POJO class object
            CustomPojo pojoObject = new CustomPojo();
            //Values are binded using set method of the POJO class
            pojoObject.setName("Hari Vigensh Jayapalan");
            pojoObject.setContent("Hello RecyclerView! item: "+iter);
            pojoObject.setTime("10:45PM");
            //After setting the values, we add all the Objects to the array
            //Hence, listConentArr is a collection of Array of POJO objects
            customPojosList.add(pojoObject);
        }


        return customPojosList;



    }
}
