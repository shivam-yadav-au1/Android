package com.example.shivam.movies;


import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import HttpCallUtil.ApiUrl;
import HttpCallUtil.VolleySingleton;
import Model.Movie;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private static final String TAG = "TAG";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Bundle bundle;
    List<Movie>  moviesList = new ArrayList<Movie>();
    NowPlaying nowPlaying = null;
    Popular popular = null;
    TopRated topRated = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        nowPlaying = new NowPlaying();
        popular = new Popular();
        topRated = new TopRated();


        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
      //  tabLayout.setOnTabSelectedListener(this);


        ApiCall apiCall = new ApiCall();
        apiCall.execute();



    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(nowPlaying, "NOW PLAYING");
        adapter.addFragment(popular, "POPULAR");
        adapter.addFragment(topRated, "TOP RATED");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        bundle = new Bundle();
        /*bundle.putParcelableArrayList("movieList",moviesList);*/

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    class ApiCall extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {

            StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiUrl.URL,

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG, "onResponse: "+response );


                            try{
                                String movieName=null;
                                String movieReleaseDate=null;
                                String moviePoster = null;
                                String moveieRating = null;
                                JSONObject reader = new JSONObject(response);
                                Log.e(TAG, "onResponse: "+response );
                                JSONArray  jsonArray = reader.getJSONArray("results");
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    movieName = jsonObject.getString("title");
                                    movieReleaseDate = jsonObject.getString("release_date");
                                    moviePoster = jsonObject.getString("poster_path");
                                    moveieRating = jsonObject.getString("vote_average");
                                    Double d = Double.parseDouble(moveieRating);
                                    Movie movie = new Movie(movieName,moviePoster,movieReleaseDate,d);
                                    moviesList.add(movie);



                                }

                                    nowPlaying.sendJsonData(moviesList);

                                    popular.sendJsonData(moviesList);
                                    topRated.sendJsonData(moviesList);


                                for(Movie item:moviesList){

                                    Log.e(TAG, "Movie :====> "+item );
                                }

                            }
                            catch (Exception e){
                                Log.e(TAG, "onResponse: "+e);
                            }
                        }
                    },
                    new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.e(TAG, "onErrorResponse: "+error);

                        }
                    });
            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            return null;
        }
    }
}
