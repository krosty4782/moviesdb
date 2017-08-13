package com.testapplication.moviesapi.base.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.testapplication.moviesplaying.model.Movie;

public class MovieApiModel {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("belongs_to_collection")
    @Expose
    private Movie collection;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Movie getCollection() {
        return collection;
    }

    public void setCollection(Movie collection) {
        this.collection = collection;
    }
}