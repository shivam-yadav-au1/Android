package com.example.shivam.radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton radioButton;
    RadioGroup radioGroup;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = (Button)findViewById(R.id.submit);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(selectedId);
                Toast.makeText(MainActivity.this,radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
