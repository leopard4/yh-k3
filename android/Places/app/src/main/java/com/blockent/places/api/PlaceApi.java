package com.blockent.places.api;

import com.blockent.places.model.PlaceList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceApi {

    @GET("/maps/api/place/nearbysearch/json")
    Call<PlaceList> getPlaceList(
            @Query("keyword") String keyword,
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("language") String language,
            @Query("key") String key,
            @Query("pagetoken") String pagetoken
    );

}
