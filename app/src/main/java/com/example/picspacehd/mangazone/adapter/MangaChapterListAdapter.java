package com.example.picspacehd.mangazone.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.picspacehd.mangazone.R;
import com.example.picspacehd.mangazone.activity.ChapterImagesActivity;
import com.example.picspacehd.mangazone.helper.Util;
import com.example.picspacehd.mangazone.model.Chapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MangaChapterListAdapter extends RecyclerView.Adapter<MangaChapterListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.manga_chapter_number) TextView chapterNum;
        @BindView(R.id.manga_chapter_date)   TextView chapterDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private Context context;
    private List<Chapter> chapters;

    public MangaChapterListAdapter(Context context, List<Chapter> chapters){
        this.context = context;
        this.chapters = chapters;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_chapterlist_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chapter chapter = chapters.get(position);

        showChapterNum(holder, chapter.getNumber());
        showChapterDate(holder, chapter.getDate());
        setClickListener(holder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    private void showChapterNum(ViewHolder holder, Double num) {
        holder.chapterNum.setText(Double.toString(num));
    }

    private void showChapterDate(ViewHolder holder, Long date) {
        holder.chapterDate.setText(Util.TIME_STAMP_TO_DATE(date));
    }

    private void setClickListener(View view, final int position) {

        final Intent intent = new Intent(context, ChapterImagesActivity.class);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("starting_chapter", position);
                intent.putParcelableArrayListExtra("chapters", (ArrayList<Chapter>) chapters);
                view.getContext().startActivity(intent);
            }
        });
    }

}

































