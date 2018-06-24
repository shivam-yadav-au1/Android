package com.example.shivam.weatherapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import HttpRequest.ApiCall;
import HttpRequest.HttpRequest;
import adapter.ForcastWetherAdapter;
import dataobject.ForcastWether;
import dataobject.WeatherData;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ForcastWetherAdapter forcastWetherAdapter;
    private ArrayList<ForcastWether> forcastWetherArrayList = new ArrayList<ForcastWether>();

    TextView weatherDescriptionTV,humidityTV,windTV,temperatureTV;
    ImageView locationButton;
    String weatherDecriptionResult,humidityResult,windResult,temperatureResult ;
    WeatherData weatherData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        weatherDescriptionTV =(TextView)findViewById(R.id.weatherDescription);
        humidityTV = (TextView)findViewById(R.id.humidityPercentage);
        windTV = (TextView)findViewById(R.id.windSpeed);
        temperatureTV =(TextView)findViewById(R.id.temperature);
        locationButton = (ImageView)findViewById(R.id.locationButton);
        layoutManager = new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        forcastWetherAdapter = new ForcastWetherAdapter(getForcastWetherArrayList(), MainActivity.this);
        recyclerView.setAdapter(forcastWetherAdapter);

        Log.i("shivam", "onCreate: size of list" + forcastWetherArrayList.size());
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner_toolBar);
        /*GPSTracker gpsTracker = new GPSTracker(this);*/

        // Spinner click listener
        /* spinner.setOnItemSelectedListener(this);*/

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");
        locationButton.setOnClickListener(this);


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        HttpRequestHandler requestHandler = new HttpRequestHandler();
        requestHandler.execute();





    }

    public String convertToCelcius(String temp){

        DecimalFormat df2 = new DecimalFormat(".##");
        double farenhiteTemp = Double.valueOf(temp);

        double tempTemp = ((farenhiteTemp - 32)*5)/9;

        return String.valueOf(df2.format(tempTemp));
    }

    public ArrayList<ForcastWether> getForcastWetherArrayList() {

        for (int i = 0; i <= 10; i++) {
            ForcastWether forcastWether = new ForcastWether();
            forcastWether.setDay("WED");
            forcastWether.setPhrase("Rain");
            forcastWether.setTemperature("21");
            forcastWetherArrayList.add(forcastWether);
        }
        return forcastWetherArrayList;
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(MainActivity.this,"Location Button was clicked ",Toast.LENGTH_SHORT).show();
    }


    class HttpRequestHandler extends AsyncTask<Void, Void, WeatherData> {

        @Override
        protected void onPostExecute(WeatherData result) {
            Log.e(TAG, "onPostExecute: "+result );
            if (result!=null)
            weatherDescriptionTV.setText(result.getDescription());

        }

        @Override
        protected WeatherData doInBackground(Void... voids) {
            Log.e(TAG, "doInBackground: entered in doInBackground() " );


            StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiCall.url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try{

                                String lon,lat,temp;
                                String description = null;
                                final String humidity,windSpeed;


                                JSONObject reader = new JSONObject(response);
                                JSONObject coord = reader.getJSONObject("coord");
                                JSONObject main = reader.getJSONObject("main");
                                JSONObject wind = reader.getJSONObject("wind");
                                humidity = main.getString("humidity");
                                windSpeed = wind.getString("speed");
                                temp = main.getString("temp");

                                JSONArray weather = reader.getJSONArray("weather");

                                lon = coord.getString("lon");
                                lat = coord.getString("lat");
                                for(int i=0;i<weather.length();i++){
                                    JSONObject jsonObject = weather.getJSONObject(i);
                                    description = jsonObject.getString("description");
                                    weatherDecriptionResult = description;

                                }
                                humidityResult = humidity;
                                windResult = windSpeed;
                                temperatureResult = temp;

                                weatherData = new WeatherData(lon,lat,description);
                                Log.e(TAG, "doInBackground: 1"+weatherData );
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        weatherDescriptionTV.setText(weatherDecriptionResult);
                                        humidityTV.setText(humidityResult+" %");
                                        windTV.setText(" "+windSpeed+" Kmph");
                                        temperatureTV.setText(convertToCelcius(temperatureResult));
                                        Log.e(TAG, " Temperature in celcius ==>"+convertToCelcius(temperatureResult) );
                                    }
                                });

                                Log.e(TAG, " Latitude ,Longitude,Description,humidity ,windSpeed,Temp" +
                                        "  =====>: "+lon+" "+lat+" "+description+" "+humidity+" "+windSpeed+" "+temp);

                            }
                            catch (Exception e){

                                Log.e(TAG, "onResponse: "+e );
                            }


                            Log.e(TAG, "onResponse: " + response);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });





            HttpRequest.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            Log.e(TAG, "doInBackground: "+weatherData );
         return weatherData;
        }


    }
}

