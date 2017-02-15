package com.picspacehd.picspacehd.mangazone.helper;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;

/**
 * Hacky fix for an issue with the PhotoView library when
 * used in conjunction with a view pager
 *
 * https://github.com/chrisbanes/PhotoView#issues-with-viewgroups
 */
public class ViewPagerForPhotoView extends ViewPager {

    public ViewPagerForPhotoView(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
