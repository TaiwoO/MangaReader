package com.example.picspacehd.mangazone.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.picspacehd.mangazone.R;
import com.example.picspacehd.mangazone.fragment.MangaChaptersFragment;
import com.example.picspacehd.mangazone.fragment.MangaDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MangaInfoActivity extends AppCompatActivity {

    //@BindView(R.id.toolbar)              Toolbar toolbar;
    @BindView(R.id.manga_info_viewpager) ViewPager viewPager;
    @BindView(R.id.manga_info_tabs)      TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_info);
        ButterKnife.bind(this);

        Intent i = getIntent();
        String mangaID = i.getStringExtra("manga_id");
        Toast.makeText(this, mangaID, Toast.LENGTH_SHORT).show();

        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpViewpager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewpager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MangaDetailFragment(), "Details");
        adapter.addFragment(new MangaChaptersFragment(), "Chapters");
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

        public void addFragment( Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
