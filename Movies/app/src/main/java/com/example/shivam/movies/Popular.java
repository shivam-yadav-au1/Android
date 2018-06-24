package com.example.shivam.movies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import Model.Movie;
import adapter.MoviesListAdapter;

import static android.content.ContentValues.TAG;

public class Popular  extends Fragment implements SendData{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Movie> movieList = new ArrayList<Movie>();
    MoviesListAdapter adapter;
    public Popular(){

    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume: called in fragment  and size of list "+movieList.size() );
        super.onResume();
        if (adapter!=null){
            Log.e(TAG, "onResume: adapter is not null " );
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view ;

        view = inflater.inflate(R.layout.popular,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerViewNowPlaying);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MoviesListAdapter(movieList,getActivity());
        recyclerView.setAdapter(adapter);
        return  view;
    }

    @Override
    public void sendJsonData(List<Movie> list) {

        /*movieList.clear();*/
        movieList.addAll(list);
        Log.e(TAG, "sendJsonData: Size of list  "+movieList.size() );
        if (adapter!=null){
            Log.e(TAG, "sendJsonData: "+movieList.size() );
            adapter.notifyDataSetChanged();
        }
    }
}
