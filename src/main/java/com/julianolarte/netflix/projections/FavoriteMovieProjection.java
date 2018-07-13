package com.julianolarte.netflix.projections;

import com.julianolarte.netflix.models.Gender;

public interface FavoriteMovieProjection {

    Long getId();
    String getName();
    String getDescription();
    String getLanguage();
    String getYear();
    Long getDurationMin();
    Gender getGender();
    boolean getFavorite();
}
