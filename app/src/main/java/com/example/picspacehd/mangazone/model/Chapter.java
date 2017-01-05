package com.example.picspacehd.mangazone.model;

import com.example.picspacehd.mangazone.helper.AppConstants;

import java.util.List;


public class Chapter {

    private Long date;
    private Double number;
    private String id;
    private String title;

    private Chapter() {}

    /* Create a chapter according to the "chapters" array from the API*/
    static Chapter createInstance(List<Object> list) {
        Chapter chapter = new Chapter();

        chapter.setDate((Double) list.get(AppConstants.CHAPTER_DATE));
        chapter.setNumber((Double)list.get(AppConstants.CHAPTER_NUMBER));
        chapter.setId((String)list.get(AppConstants.CHAPTER_ID));
        chapter.setTitle((String)list.get(AppConstants.CHAPTER_TITLE));
        return chapter;
    }

    public Long getDate() {
        return date;
    }

    private void setDate(Double date) {
        this.date = date.longValue();
    }

    public Double getNumber() {
        return number;
    }

    private void setNumber(Double number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
