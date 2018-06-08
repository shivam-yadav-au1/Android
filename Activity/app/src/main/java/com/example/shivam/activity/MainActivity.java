package com.example.shivam.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.shivam.activity.com.example.shivam.dataobject.Person;

public class MainActivity extends AppCompatActivity {
    final String TAG=MainActivity.this.getClass().toString();

    android.widget.EditText editTextName,editTextAddress,editTextProffession;
    android.widget.Button buttonSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate() method  executed: ");
        editTextName = (android.widget.EditText)findViewById(R.id.name);
        editTextAddress = (android.widget.EditText)findViewById(R.id.address);
        editTextProffession = (android.widget.EditText)findViewById(R.id.proffession);
        buttonSubmit = (android.widget.Button)findViewById(R.id.submit);







        buttonSubmit.setOnClickListener(new android.view.View.OnClickListener(){

         public void onClick(android.view.View v) {

             Log.i(TAG,"Button Clicked...");
             String name = editTextName.getText().toString();
             String address = editTextAddress.getText().toString();
             String proffession = editTextProffession.getText().toString();
             Person person = new Person(name,address,proffession);

             Log.i(TAG,"Person Details "+person);

             android.content.Intent intent = new android.content.Intent(MainActivity.this,Main2Activity.class);
            /* intent.putExtra("name",person.getName());
             intent.putExtra("address",person.getAddress());
             intent.putExtra("proffession",person.getProffession());*/
             intent.putExtra("person",new Person(person.getName(),person.getAddress(),person.getProffession()));
             startActivity(intent);
             }

        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() method  executed: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() method  executed: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() method  executed: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() method  executed: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart() method  executed: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestory() method  executed: ");
    }
}
