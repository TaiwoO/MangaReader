package com.picspacehd.picspacehd.mangazone.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Manga {

    @SerializedName("a")
    String alias;
    @SerializedName("c")
    List<String> categories = new ArrayList<String>();
    @SerializedName("h")
    Integer hits;
    @SerializedName("i")
    String id;
    @SerializedName("im")
    String imgPath;
    @SerializedName("ld")
    Integer lastChapterDate;
    @SerializedName("s")
    Integer status;
    @SerializedName("t")
    String title;

    public static final Comparator<Manga> ALPABETICAL_COMPARATOR = new Comparator<Manga>() {
        @Override
        public int compare(Manga a, Manga b) {
            return a.getTitle().compareTo(b.getTitle());
        }
    };

    public static final Comparator<Manga> POPULARITY_COMPARATOR = new Comparator<Manga>() {
        @Override
        public int compare(Manga a, Manga b) {
            return b.getHits().compareTo(a.getHits());
        }
    };

    // TODO: implement this
    public static final Comparator<Manga> LAST_CHAPTER_DATE_COMPARATOR = new Comparator<Manga>() {
        @Override
        public int compare(Manga a, Manga b) {
            return 0;
        }
    };

    public Manga() {

    }

    public Manga(String alias, List<String> categories, Integer hits, String id, String imgPath,
                 Integer lastChapterDate, Integer status, String title) {
        this.alias = alias;
        this.categories = categories;
        this.hits = hits;
        this.id = id;
        this.imgPath = imgPath;
        this.lastChapterDate = lastChapterDate;
        this.status = status;
        this.title = title;
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

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getLastChapterDate() {
        return lastChapterDate;
    }

    public void setLastChapterDate(Integer lastChapterDate) {
        this.lastChapterDate = lastChapterDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
