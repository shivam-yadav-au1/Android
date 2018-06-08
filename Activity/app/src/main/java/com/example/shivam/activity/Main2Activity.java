package com.example.shivam.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.shivam.activity.com.example.shivam.dataobject.Person;

public class Main2Activity extends AppCompatActivity {

    final String TAG=Main2Activity.this.getClass().toString();

        android.widget.TextView nameTextView,addressTextView,proffessionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nameTextView  = (android.widget.TextView)findViewById(R.id.personName);
        addressTextView  = (android.widget.TextView)findViewById(R.id.personAddress);
        proffessionTextView  = (android.widget.TextView)findViewById(R.id.personProffession);

        // Getting person data from intent form another activity.
        android.content.Intent intent = new android.content.Intent();
        Bundle data = getIntent().getExtras();
        Person person = (Person) data.getParcelable("person");
       /* String name = getIntent().getExtras().getString("name");
        String address = getIntent().getExtras().getString("address");
        String proffession = getIntent().getExtras().getString("proffession");*/
       String name = person.getName();
       String address = person.getAddress();
       String proffession = person.getProffession();
        // Setting text into text view
        nameTextView.setText(name);
        addressTextView.setText(address);
        proffessionTextView.setText(proffession);

        if(name.equals("shivam")){
            Log.i(TAG, "nameTextView is equals to shivam");
            setActivityBackgroundColor(Color.GRAY);
        }


    }

    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}
