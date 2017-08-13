package com.testapplication.moviesplaying.services;

import com.testapplication.moviesapi.base.api.MovieApiModel;
import com.testapplication.moviesplaying.model.Movie;

public class SearchApiModelToSearchConverter {

    public Movie convertSearchApiModelToSearchModel(MovieApiModel movieApiModel) {
        Movie movie = new Movie();
        movie.setTitle(movieApiModel.getTitle());
        movie.setId(movieApiModel.getId());
        movie.setOverview(movieApiModel.getOverview());
        return movie;
    }
}
