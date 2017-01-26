package com.example.picspacehd.mangazone.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picspacehd.mangazone.R;
import com.example.picspacehd.mangazone.adapter.MangaImagesAdapter;
import com.example.picspacehd.mangazone.helper.ViewPagerForPhotoView;
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

    @BindView(R.id.current_chapter) TextView currentChapter;
    @BindView(R.id.current_page)    TextView currentPage;
    @BindView(R.id.total_pages)     TextView totalPages;
    @BindView(R.id.image_viewpager) ViewPager imagesViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_images);
        ButterKnife.bind(this);

        chapters = getIntent().getParcelableArrayListExtra("chapters");
        Integer startingChapter = getIntent().getIntExtra("starting_chapter", 0);
        showCurrentChapter(startingChapter);

        fetchChapterPages(getChapterId(startingChapter));

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

        imagesViewPager.setAdapter(new MangaImagesAdapter(this, pages));
        imagesViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                showCurrentPageNum(position+1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void showCurrentChapter(int position) {
        this.currentChapter.setText(chapters.get(position).getNumber().toString());
    }

    private void showTotalNumPages(Integer total) {
        this.totalPages.setText(total.toString());
    }

    private void showCurrentPageNum(int num) {
        currentPage.setText(Integer.toString(num));
    }

    private String getChapterId(int position) {
        return chapters.get(position).getId();
    }
}
