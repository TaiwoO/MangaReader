package com.picspacehd.picspacehd.mangazone.activity;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.picspacehd.mangazone.R;
import com.picspacehd.picspacehd.mangazone.adapter.MangaListAdapter;
import com.picspacehd.picspacehd.mangazone.model.Manga;
import com.picspacehd.picspacehd.mangazone.model.MangaListResponse;
import com.picspacehd.picspacehd.mangazone.rest.ApiClient;
import com.picspacehd.picspacehd.mangazone.rest.ApiInterface;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Manga> mangas;
    private MangaListAdapter mangaListAdapter;

    @BindView(R.id.layout_connection_failed)  LinearLayout connectionFailedLayout;
    @BindView(R.id.layout_connection_success) RecyclerView connectionSuccessLayout;
    @BindView(R.id.txt_connection_Failed)     TextView connectionFailedBtn;
    @BindView(R.id.progressBar_mangaList)     ProgressBar connectingProgressbar;

    @OnClick(R.id.txt_connection_Failed)
    public void refetchMangas() {
        fetchMangas();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        fetchMangas();
    }

    private void fetchMangas() {
        displayDefaultResults();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MangaListResponse> call = apiService.getMangas();
        call.enqueue(new Callback<MangaListResponse>() {
            @Override
            public void onResponse(Call<MangaListResponse> call, Response<MangaListResponse> response) {
                mangas = response.body().getMangas();
                displaySuccessResults(mangas);
            }

            @Override
            public void onFailure(Call<MangaListResponse> call, Throwable t) {
                displayFailResults();
                Log.d(TAG, t.getMessage());
            }
        });
    }

    private void displayDefaultResults() {
        connectingProgressbar.setVisibility(View.VISIBLE);
        connectionFailedLayout.setVisibility(View.GONE);
        connectionSuccessLayout.setVisibility(View.GONE);
    }

    private void displaySuccessResults(List<Manga> mangas) {
        connectingProgressbar.setVisibility(View.GONE);
        connectionSuccessLayout.setVisibility(View.VISIBLE);

        Collections.sort(mangas, Manga.POPULARITY_COMPARATOR);
        mangaListAdapter = new MangaListAdapter(getApplicationContext(), mangas);
        connectionSuccessLayout.setLayoutManager(new LinearLayoutManager(this));
        connectionSuccessLayout.setAdapter(mangaListAdapter);
    }

    private void displayFailResults() {
        connectingProgressbar.setVisibility(View.GONE);
        connectionFailedLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                if (mangaListAdapter != null)
                    mangaListAdapter.filter(newText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
               // mangaListAdapter.filter(query);
                searchView.clearFocus();
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_refresh) {
            fetchMangas();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
