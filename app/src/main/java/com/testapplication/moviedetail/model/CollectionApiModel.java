package com.testapplication.moviedetail.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.testapplication.moviesapi.base.api.MovieApiModel;

import java.util.List;

public class CollectionApiModel {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("parts")
    @Expose
    private List<MovieApiModel> movies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieApiModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieApiModel> movies) {
        this.movies = movies;
    }
}
