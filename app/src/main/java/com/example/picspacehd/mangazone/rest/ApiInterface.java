package com.example.picspacehd.mangazone.rest;

import com.example.picspacehd.mangazone.model.MangaListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("list/0")
    Call<MangaListResponse> getMangas();

}
