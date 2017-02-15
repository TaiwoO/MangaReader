package com.picspacehd.picspacehd.mangazone.rest;

import com.picspacehd.picspacehd.mangazone.model.MangaChapterResponse;
import com.picspacehd.picspacehd.mangazone.model.MangaInfoResponse;
import com.picspacehd.picspacehd.mangazone.model.MangaListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("list/0")
    Call<MangaListResponse> getMangas();

    @GET("manga/{id}")
    Call<MangaInfoResponse> getMangaInfo(@Path("id") String mangaId);

    @GET("chapter/{id}")
    Call<MangaChapterResponse> getMangaChapterInfo(@Path("id") String chapterId);

}
