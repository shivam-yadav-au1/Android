package com.example.shivam.cardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import adapter.CardViewAdapter;
import model.Person;

public class MainActivity extends AppCompatActivity {

    String TAG =this.getClass().toString();

    private RecyclerView recyclerView;
    private CardViewAdapter cardViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Person> personList = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        cardViewAdapter = new CardViewAdapter(populateRecyclerViewValues(),MainActivity.this);
        Log.e(TAG, "onCreate: PersonList size ===>"+personList.size());
        recyclerView.setAdapter(cardViewAdapter);
    }

    private ArrayList<Person> populateRecyclerViewValues() {

        for(int iter=1;iter<=50;iter++) {
            //Creating POJO class object
            Person pojoObject = new Person();
            //Values are binded using set method of the POJO class
            pojoObject.setName("Hari Vigensh Jayapalan");
            pojoObject.setContent("Hello RecyclerView! item: "+iter);
            pojoObject.setTime("10:45PM");
            //After setting the values, we add all the Objects to the array
            //Hence, listConentArr is a collection of Array of POJO objects
            personList.add(pojoObject);
        }


        return personList;
    }
}
