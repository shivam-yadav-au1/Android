package com.example.shivam.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shivam.listview.dataobject.PersonDetails;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
 /*   ArrayList<String> data;
    ArrayAdapter<String> adapter;*/
 PersonAdapter personAdapter;
 ArrayList<PersonDetails> data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= (ListView)findViewById(R.id.list);
        data = new ArrayList<PersonDetails>();
        data.add(new PersonDetails("shivam","allahabad"));
        data.add(new PersonDetails("Tushar","Lucknow"));
        data.add(new PersonDetails("Amit","Ambernath"));
        data.add(new PersonDetails("Balram","Ambernath"));
        data.add(new PersonDetails("Adil","Ambernath"));
        data.add(new PersonDetails("Mayur","Mumbai"));

        data.add(new PersonDetails("Ashish","Pratabghar"));
  /*      adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You clicked : " + data.get(position), Toast.LENGTH_SHORT).show();

            }
        });*/
  personAdapter = new PersonAdapter(this,data);
  listView.setAdapter(personAdapter);
  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          Toast.makeText(MainActivity.this, "You clicked : " + data.get(position), Toast.LENGTH_SHORT).show();
      }
    });

    }
}
