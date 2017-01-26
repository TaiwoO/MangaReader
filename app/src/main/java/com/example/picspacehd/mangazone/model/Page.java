package com.example.picspacehd.mangazone.model;

import com.example.picspacehd.mangazone.helper.AppConstants;

import java.util.List;

public class Page {

    private Double number;
    private String imgPath;

    static Page createInstance(List<Object> pageAsObject) {
        Page page = new Page();
        page.setNumber((Double) pageAsObject.get(AppConstants.PAGE_NUMBER));
        page.setImgPath((String) pageAsObject.get(AppConstants.PAGE_IMGPATH));

        return page;
    }

    public Double getNumber() {
        return number;
    }

    private void setNumber(Double number) {
        this.number = number;
    }

    public String getImgPath() {
        return imgPath;
    }

    private void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
