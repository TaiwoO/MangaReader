package com.picspacehd.picspacehd.mangazone.fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.picspacehd.mangazone.R;
import com.picspacehd.picspacehd.mangazone.adapter.MangaChapterListAdapter;
import com.picspacehd.picspacehd.mangazone.helper.AppConstants;
import com.picspacehd.picspacehd.mangazone.model.Chapter;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MangaChaptersFragment extends Fragment {

    @BindView(R.id.manga_info_chapter_list)    RecyclerView chaptersRecyclerView;
    @BindView(R.id.manga_info_chapter_reorder) ImageButton chapterReorderBtn;
    private MangaChapterListAdapter mangaChapterListAdapter;

    private int datasetOrder;

    public MangaChaptersFragment() {
        datasetOrder =  AppConstants.HIGH_TO_LOW;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manga_chapters, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void displayResults(List<Chapter> chapters) {
        chaptersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mangaChapterListAdapter = new MangaChapterListAdapter(getActivity(), chapters);
        chaptersRecyclerView.setAdapter(mangaChapterListAdapter);
        setReorderButton(chapterReorderBtn, chapters);

    }

    private void setReorderButton(View button, final List<Chapter> chapters) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.reverse(chapters);
                mangaChapterListAdapter.notifyDataSetChanged();

                if (datasetOrder == AppConstants.HIGH_TO_LOW) {
                    datasetOrder = AppConstants.LOW_TO_HIGH;
                    mangaChapterListAdapter.setDatasetOrder(datasetOrder);
                }else{
                    datasetOrder = AppConstants.HIGH_TO_LOW;
                    mangaChapterListAdapter.setDatasetOrder(datasetOrder);
                }
            }
        });
    }
}
