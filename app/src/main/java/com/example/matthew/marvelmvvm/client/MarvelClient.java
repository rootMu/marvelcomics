package com.example.matthew.marvelmvvm.client;

import com.example.matthew.marvelmvvm.model.ComicResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MarvelClient
{
    @GET("v1/public/comics")
    Observable<ComicResponse> getComics(@Query("ts") String timeStamp, @Query("apikey") String apiKey, @Query("hash") String hash, @Query("limit") int limit);
}
