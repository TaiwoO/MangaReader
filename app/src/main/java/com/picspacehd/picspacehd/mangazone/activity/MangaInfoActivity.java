package com.picspacehd.picspacehd.mangazone.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.picspacehd.mangazone.R;
import com.picspacehd.picspacehd.mangazone.fragment.MangaChaptersFragment;
import com.picspacehd.picspacehd.mangazone.fragment.MangaDetailFragment;
import com.picspacehd.picspacehd.mangazone.model.MangaInfoResponse;
import com.picspacehd.picspacehd.mangazone.rest.ApiClient;
import com.picspacehd.picspacehd.mangazone.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MangaInfoActivity extends AppCompatActivity {

    @BindView(R.id.manga_info_viewpager) ViewPager viewPager;
    @BindView(R.id.manga_info_tabs) TabLayout tabLayout;

    private MangaDetailFragment mangaDetailFragment = new MangaDetailFragment();
    private MangaChaptersFragment mangaChaptersFragment = new MangaChaptersFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_info);
        ButterKnife.bind(this);

        Intent i = getIntent();
        String mangaId = i.getStringExtra("manga_id");
        String mangaTitle = i.getStringExtra("manga_title");

        setTitle(mangaTitle);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpViewpager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        fetchMangaInfo(mangaId);
    }

    private void fetchMangaInfo(String mangaId) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MangaInfoResponse> call = apiService.getMangaInfo(mangaId);
        call.enqueue(new Callback<MangaInfoResponse>() {
            @Override
            public void onResponse(Call<MangaInfoResponse> call, Response<MangaInfoResponse> response) {
                MangaInfoResponse results = response.body();
                displaySuccessResults(results);
            }

            @Override
            public void onFailure(Call<MangaInfoResponse> call, Throwable t) {

            }
        });
    }

    private void displaySuccessResults(MangaInfoResponse results) {

        mangaDetailFragment.displayResults(results);
        mangaChaptersFragment.displayResults(results.getChapters());
    }

    private void setUpViewpager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(mangaDetailFragment, "Details");
        adapter.addFragment(mangaChaptersFragment, "Chapters");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
