package com.testapplication.moviesplaying.services;

import com.testapplication.base.api.MovieApiModel;
import com.testapplication.moviesplaying.model.Movie;

public class MovieApiModelToMovieConverter {

    public Movie convertSearchApiModelToSearchModel(MovieApiModel movieApiModel) {
        Movie movie = new Movie();
        movie.setTitle(movieApiModel.getTitle());
        movie.setId(movieApiModel.getId());
        movie.setOverview(movieApiModel.getOverview());
        if (movieApiModel.getCollection() != null) {
            movie.setCollectionId(movieApiModel.getCollection().getId());
        }
        return movie;
    }
}
