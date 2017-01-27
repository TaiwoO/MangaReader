package com.example.picspacehd.mangazone.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picspacehd.mangazone.R;
import com.example.picspacehd.mangazone.adapter.MangaImagesAdapter;
import com.example.picspacehd.mangazone.helper.AppConstants;
import com.example.picspacehd.mangazone.helper.Container;
import com.example.picspacehd.mangazone.model.Chapter;
import com.example.picspacehd.mangazone.model.MangaChapterResponse;
import com.example.picspacehd.mangazone.model.Page;
import com.example.picspacehd.mangazone.rest.ApiClient;
import com.example.picspacehd.mangazone.rest.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapterImagesActivity extends AppCompatActivity {

    private List<Chapter> chapters;
    private Integer currentChapter = 0;

    @BindView(R.id.current_chapter) TextView currentChap;
    @BindView(R.id.current_page)    TextView currentPage;
    @BindView(R.id.total_pages)     TextView totalPages;
    @BindView(R.id.image_viewpager) ViewPager imagesViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_images);
        ButterKnife.bind(this);

        chapters = getIntent().getParcelableArrayListExtra("chapters");
        currentChapter = getIntent().getIntExtra("starting_chapter", 0);
        showCurrentChapter(currentChapter);

        fetchChapterPages(getChapterId(currentChapter));

    }

    private void fetchChapterPages(String chapterId) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MangaChapterResponse> call = apiService.getMangaChapterInfo(chapterId);
        call.enqueue(new Callback<MangaChapterResponse>() {
            @Override
            public void onResponse(Call<MangaChapterResponse> call, Response<MangaChapterResponse> response) {
                List<Page> pages = response.body().getPages();
                displaySuccessResults(pages);
            }

            @Override
            public void onFailure(Call<MangaChapterResponse> call, Throwable t) {

            }
        });
    }

    private void displaySuccessResults(List<Page> pages) {
        showTotalNumPages(pages.size());
        showCurrentPageNum(1);
        setupPrevChapterBtn();
        setupNextChapterBtn();

        imagesViewPager.setAdapter(new MangaImagesAdapter(this, pages));
        imagesViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                showCurrentPageNum(position + 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    private void showCurrentChapter(int position) {
        this.currentChap.setText(chapters.get(position).getNumber().toString());
    }

    private void showTotalNumPages(Integer total) {
        this.totalPages.setText(total.toString());
    }

    private void showCurrentPageNum(int num) {
        currentPage.setText(Integer.toString(num));
    }

    private void setupPrevChapterBtn() {
        RelativeLayout prevChapterBtn = (RelativeLayout) findViewById(R.id.prev_chapter_layout);
        prevChapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Previous chapter depends on the order in which the chapters are
                if (getChapterOrder() == AppConstants.LOW_TO_HIGH) {
                    fetchPreviousChapter();
                } else {
                    fetchNextChapter();
                }
            }
        });
    }

    private void setupNextChapterBtn() {
        RelativeLayout nextChapterBtn = (RelativeLayout) findViewById(R.id.next_chapter_layout);
        nextChapterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Previous chapter depends on the order in which the chapters are
                if (getChapterOrder() == AppConstants.LOW_TO_HIGH) {
                    fetchNextChapter();
                } else {
                    fetchPreviousChapter();
                }
            }
        });
    }

    private int getLastChapterIndex() {
        if (chapters != null) {
            return chapters.size() - 1;
        } else {
            return -1;
        }
    }

    private int getFirstChapterIndex() {
        return 0;
    }

    private void fetchPreviousChapter() {

        if (currentChapter == getFirstChapterIndex()) {
            Toast.makeText(ChapterImagesActivity.this
                    , "Nothing left"
                    , Toast.LENGTH_SHORT)
                    .show();
        } else {
            currentChapter--;
            showCurrentChapter(currentChapter);
            fetchChapterPages(getChapterId(currentChapter));
        }
    }

    private void fetchNextChapter() {
        if (currentChapter == getLastChapterIndex()) {
            Toast.makeText(ChapterImagesActivity.this
                    , "Nothing left"
                    , Toast.LENGTH_SHORT)
                    .show();
        } else {
            currentChapter++;
            showCurrentChapter(currentChapter);
            fetchChapterPages(getChapterId(currentChapter));
        }
    }

    private int getChapterOrder() {
        return Container.getInstance().getOrderOfChapers();
    }

    private String getChapterId(int position) {
        return chapters.get(position).getId();
    }
}
