package com.testapplication.base.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsApiModel {

    @SerializedName("results")
    @Expose
    private List<MovieApiModel> results;

    public List<MovieApiModel> getResults() {
        return results;
    }

    public void setResults(List<MovieApiModel> results) {
        this.results = results;
    }
}
