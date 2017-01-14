package com.example.picspacehd.mangazone.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.picspacehd.mangazone.helper.AppConstants;

import java.util.List;


public class Chapter implements Parcelable {

    private Long date;
    private Double number;
    private String id;
    private String title;

    public Chapter() {
    }

    public Chapter(Long date, Double number, String id, String title) {
        this.date = date;
        this.number = number;
        this.id = id;
        this.title = title;
    }

    /* Create a chapter according to the "chapters" array from the API*/
    static Chapter createInstance(List<Object> list) {
        Chapter chapter = new Chapter();

        chapter.setDate((Double) list.get(AppConstants.CHAPTER_DATE));
        chapter.setNumber((Double) list.get(AppConstants.CHAPTER_NUMBER));
        chapter.setId((String) list.get(AppConstants.CHAPTER_ID));
        chapter.setTitle((String) list.get(AppConstants.CHAPTER_TITLE));
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

    private void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }


    public Chapter(Parcel in) {
        this.date = in.readLong();
        this.number = in.readDouble();
        this.id = in.readString();
        this.title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(date);
        parcel.writeDouble(number);
        parcel.writeString(id);
        parcel.writeString(title);
    }


    public static final Parcelable.Creator<Chapter> CREATOR = new Parcelable.Creator<Chapter>() {

        @Override
        public Chapter createFromParcel(Parcel parcel) {
            return new Chapter(parcel);
        }

        @Override
        public Chapter[] newArray(int i) {
            return new Chapter[i];
        }
    };

}
