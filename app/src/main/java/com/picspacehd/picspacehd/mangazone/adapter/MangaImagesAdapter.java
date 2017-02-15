package com.picspacehd.picspacehd.mangazone.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.example.picspacehd.mangazone.R;
import com.picspacehd.picspacehd.mangazone.helper.AppConstants;
import com.picspacehd.picspacehd.mangazone.model.Page;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

public class MangaImagesAdapter extends PagerAdapter {

    private List<Page> pages;
    private Context context;
    private LayoutInflater inflater;

    public MangaImagesAdapter(Context context, List<Page> pages) {
        this.context = context;
        this.pages = pages;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((FrameLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Page page = pages.get(position);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_mangaimage, container, false);

        showImage(viewLayout, page.getImgPath());
        container.addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }

    private void showImage(View view, String imgPath) {
        PhotoView imgDisplay;
        imgDisplay = (PhotoView) view.findViewById(R.id.page_image);

        Glide.with(context).load(AppConstants.API_IMG_BASE_URL + imgPath)
                .into(imgDisplay);
    }
}
