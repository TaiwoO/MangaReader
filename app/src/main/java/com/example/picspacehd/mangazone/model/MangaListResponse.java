package com.example.picspacehd.mangazone.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MangaListResponse {

    @SerializedName("end")
    private Integer end;
    @SerializedName("manga")
    private List<Manga> results;
    @SerializedName("page")
    private Integer page;
    @SerializedName("start")
    private Integer start;
    @SerializedName("total")
    private Integer total;

    public Integer getEnd() {return end;}

    public void setEnd(Integer end) {
        this.end = end;
    }

    public List<Manga> getResults() {
        return results;
    }

    public void setResults(List<Manga> results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
