package com.picspacehd.picspacehd.mangazone.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MangaInfoResponse {

    @SerializedName("alias")
    private String alias;
    @SerializedName("categories")
    private List<String> categories;
    @SerializedName("chapters_len")
    private Integer chapterLen;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String imgPath;
    @SerializedName("created")
    private Long releaseDate;
    @SerializedName("last_chapter_date")
    private Long lastChapterDate;
    @SerializedName("status")
    private Integer status;
    @SerializedName("chapters")
    private List<List<Object>> chapters;

    public MangaInfoResponse() {
    }

    public List<Chapter> getChapters() {

        List<Chapter> chapterList = new ArrayList<Chapter>();

        for (List<Object> chapterAsObject : chapters) {
            Chapter objectAsChapter = Chapter.createInstance(chapterAsObject);
            chapterList.add(objectAsChapter);
        }
        return chapterList;
    }

    public void setChapters(List<List<Object>> chapters) {
        this.chapters = chapters;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Integer getChapterLen() {
        return chapterLen;
    }

    public void setChapterLen(Integer chapterLen) {
        this.chapterLen = chapterLen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getLastChapterDate() {
        return lastChapterDate;
    }

    public void setLastChapterDate(Long lastChapterDate) {
        this.lastChapterDate = lastChapterDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
