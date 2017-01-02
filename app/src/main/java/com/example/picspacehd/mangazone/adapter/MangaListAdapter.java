package com.example.picspacehd.mangazone.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.picspacehd.mangazone.R;
import com.example.picspacehd.mangazone.activity.MainActivity;
import com.example.picspacehd.mangazone.helper.AppConstants;
import com.example.picspacehd.mangazone.model.Manga;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MangaListAdapter extends RecyclerView.Adapter<MangaListAdapter.ViewHolder> {
    private static final String TAG = MangaListAdapter.class.getSimpleName();

    private Context context;
    private List<Manga> mangas;
    private Intent intent;
    private List<Manga> mangasCopy = new ArrayList<Manga>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.manga_layout_item_cover)  ImageView cover;
        @BindView(R.id.manga_layout_item_title)  TextView title;
        @BindView(R.id.manga_layout_item_status) TextView status;
        @BindView(R.id.manga_layout_item_alias)  TextView alias;
        @BindView(R.id.manga_layout_item_genres) TextView genres;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public MangaListAdapter(Context context, List<Manga> mangas) {
        this.context = context;
        this.mangas  = mangas;
        this.mangasCopy.addAll(mangas);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_mangalist_item, parent, false);

        if (isTransparentViewType(viewType)) {
            itemView.setBackgroundResource(android.R.color.transparent);
        }
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Manga manga = mangas.get(position);

        showCover(holder, manga.getImgPath());
        showTitle(holder, manga.getTitle());
        showStatus(holder, manga.getStatus());
        showAlias(holder, manga.getAlias());
        showGeneres(holder, manga.getCategories());

    }

    @Override
    public int getItemCount() {
        return mangas.size();
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        return position;
    }

    private void showCover(ViewHolder holder, String imgPath) {
        String coverLink = AppConstants.API_IMG_BASE_URL + imgPath;
        Glide.with(context).load(coverLink).into(holder.cover);
    }
    private void showTitle(ViewHolder holder, String title) {
        holder.title.setText(title);
    }
    private void showStatus(ViewHolder holder, Integer status) {

        if (status.equals(AppConstants.ONGOING)) {
            holder.status.setText(R.string.layout_item_status_ongoing);
            holder.status.setTextColor(context.getResources().getColor(R.color.colorStatusOngoing));
        }
        else if(status.equals(AppConstants.FINISHED)) {
            holder.status.setText(R.string.layout_item_status_finished);
            holder.status.setTextColor(context.getResources().getColor(R.color.colorStatusFinished));
        }
        else {
            holder.status.setText(R.string.layout_item_status_unknown);
        }
    }
    private void showAlias(ViewHolder holder, String alias) {
        holder.alias.setText(alias);
    }
    private void showGeneres(ViewHolder holder, List<String> genreList) {
        String genres = "";

        for (String genre : genreList) {
            genres += genre + ", ";
        }
        if (genres.length() > 2) {
            genres = genres.substring(0, genres.length() - 2);
        }

        holder.genres.setText(genres);
    }

    public void filter (String text) {
        mangas.clear();
        if(text.isEmpty()) {
            mangas.addAll(mangasCopy);
        } else {
            text = text.toLowerCase();
            for ( Manga manga : mangasCopy) {
                if (manga.getTitle().toLowerCase().contains(text)) {
                    mangas.add(manga);
                }
            }
        }
        notifyDataSetChanged();
    }
    private boolean isTransparentViewType(int viewType) {
        return isEven(viewType);
    }
    private boolean isEven(int num) {
        return num % 2 == 0;
    }


}














