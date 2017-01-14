package com.example.picspacehd.mangazone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picspacehd.mangazone.R;
import com.example.picspacehd.mangazone.model.Chapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChapterImagesActivity extends AppCompatActivity {

    private List<Chapter> chapters;

    @BindView(R.id.current_chapter) TextView currentChapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_images);
        ButterKnife.bind(this);

        chapters = getIntent().getParcelableArrayListExtra("chapters");
        Integer startingChapter = getIntent().getIntExtra("starting_chapter", 0);
        showCurrentChapter(startingChapter);

        fetchChapterPages(chapters.get(startingChapter).getId());

    }

    private void fetchChapterPages(String chapterId) {
        
    }

    private void showCurrentChapter(int position) {
        this.currentChapter.setText(chapters.get(position).getNumber().toString());
    }

}
