package com.example.picspacehd.mangazone.model;

import com.example.picspacehd.mangazone.helper.AppConstants;

import java.util.List;

class Page {

    private Integer number;
    private Integer imgPath;

    static Page createInstance(List<Object> pageAsObject) {
        Page page = new Page();
        page.setNumber((Integer) pageAsObject.get(AppConstants.PAGE_NUMBER));
        page.setImgPath((Integer) pageAsObject.get(AppConstants.PAGE_IMGPATH));

        return page;
    }
    
    public Integer getNumber() {
        return number;
    }

    private void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getImgPath() {
        return imgPath;
    }

    private void setImgPath(Integer imgPath) {
        this.imgPath = imgPath;
    }
}
