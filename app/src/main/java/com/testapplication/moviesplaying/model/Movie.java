package com.testapplication.moviesplaying.model;

import java.io.Serializable;

/**
 * Created by mfolcini on 17/07/2017.
 */


/* We use this class as an internal representation of MovieApiModel.
The porpouse of this is to leave MovieApiModel as an immutable class that will mach exactly the API */
public class Movie implements Serializable {

    private String title;
    private String id;
    private String overview;
    private String collectionId;

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

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }
}
