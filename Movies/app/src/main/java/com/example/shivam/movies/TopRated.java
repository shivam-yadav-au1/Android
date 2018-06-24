package com.example.shivam.movies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import HttpCallUtil.ApiUrl;
import HttpCallUtil.VolleySingleton;
import Model.Movie;
import adapter.MoviesListAdapter;

import static android.content.ContentValues.TAG;

public class TopRated extends Fragment implements SendData {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Movie> moviesList = new ArrayList<Movie>();
    MoviesListAdapter adapter;

    public TopRated(){

    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter!=null){
            Log.e(TAG, "onResume: adapter is not null " );
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiCall apiCall = new ApiCall();
        apiCall.execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view ;

        view = inflater.inflate(R.layout.top_rated,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewNowPlaying);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MoviesListAdapter(moviesList,getActivity());
        recyclerView.setAdapter(adapter);
        return  view;
    }

    @Override
    public void sendJsonData(List<Movie> movieList) {

    }


    class ApiCall extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            StringRequest stringRequest = new StringRequest(Request.Method.GET, ApiUrl.TOP_RATED,

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.e(VolleyLog.TAG, "onResponse: "+response );


                            try{
                                String movieName=null;
                                String movieReleaseDate=null;
                                String moviePoster = null;
                                String moveieRating = null;
                                JSONObject reader = new JSONObject(response);
                                Log.e(VolleyLog.TAG, "onResponse: "+response );
                                JSONArray jsonArray = reader.getJSONArray("results");
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

                               /* nowPlaying.sendJsonData(moviesList);

                                popular.sendJsonData(moviesList);
                                topRated.sendJsonData(moviesList);*/


                                for(Movie item:moviesList){

                                    Log.e(VolleyLog.TAG, "Movie :====> "+item );
                                }

                            }
                            catch (Exception e){
                                Log.e(VolleyLog.TAG, "onResponse: "+e);
                            }
                        }
                    },
                    new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.e(VolleyLog.TAG, "onErrorResponse: "+error);

                        }
                    });
            VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
            return null;
        }
    }
}
