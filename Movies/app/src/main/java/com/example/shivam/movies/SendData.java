package com.example.shivam.movies;

import org.json.JSONObject;

import java.util.List;

import Model.Movie;

public interface SendData {

    public void sendJsonData(List<Movie> movieList);
}
