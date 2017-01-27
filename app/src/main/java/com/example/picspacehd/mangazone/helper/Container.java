package com.example.picspacehd.mangazone.helper;

/**
 * Container for variables accessed through different classes
 */
public class Container {

    private int orderOfChapers = AppConstants.HIGH_TO_LOW;

    private static Container instance = null;

    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    public int getOrderOfChapers() {
        return orderOfChapers;
    }

    public void setOrderOfChapers(int orderOfChapers) {
        this.orderOfChapers = orderOfChapers;
    }
}
