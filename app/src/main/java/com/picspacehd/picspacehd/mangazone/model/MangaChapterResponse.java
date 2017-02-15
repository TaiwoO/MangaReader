package com.picspacehd.picspacehd.mangazone.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MangaChapterResponse {

    @SerializedName("images")
    private List<List<Object>> pages;

    public List<Page> getPages() {

        List<Page> pageList = new ArrayList<>();

        for (List<Object> pageAsObject : pages) {
            Page page = Page.createInstance(pageAsObject);
            pageList.add(page);
        }

        return pageList;
    }
}
