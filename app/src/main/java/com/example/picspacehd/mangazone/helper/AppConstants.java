package com.example.picspacehd.mangazone.helper;

public class AppConstants {

    public static final String API_BASE_URL     = "https://www.mangaeden.com/api/";
    public static final String API_IMG_BASE_URL = "https://cdn.mangaeden.com/mangasimg/";

    // Meaning of the indexes in the "chapters" array from the API
    //
    public static final Integer CHAPTER_NUMBER = 0;
    public static final Integer CHAPTER_DATE   = 1;
    public static final Integer CHAPTER_TITLE  = 2;
    public static final Integer CHAPTER_ID     = 3;

    // Meaning of the indexes in the "pages" array from the API
    //
    public static final Integer PAGE_NUMBER  = 0;
    public static final Integer PAGE_IMGPATH = 1;

    // Status of a manga
    //
    public static final Integer ONGOING  = 1;
    public static final Integer FINISHED = 2;

    public static final Integer HIGH_TO_LOW = 0;
    public static final Integer LOW_TO_HIGH = 1;
}
