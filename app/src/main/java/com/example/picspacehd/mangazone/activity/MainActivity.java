package com.example.picspacehd.mangazone.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.picspacehd.mangazone.R;
import com.example.picspacehd.mangazone.adapter.MangaListAdapter;
import com.example.picspacehd.mangazone.model.Manga;
import com.example.picspacehd.mangazone.model.MangaListResponse;
import com.example.picspacehd.mangazone.rest.ApiClient;
import com.example.picspacehd.mangazone.rest.ApiInterface;

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

    @BindView(R.id.layout_connection_failed)  LinearLayout connectionFailedLayout;
    @BindView(R.id.layout_connection_success) RecyclerView connectionSuccessLayout;
    @BindView(R.id.txt_connection_Failed)     TextView connectionFailedBtn;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fetchMangas();

    }

    public void fetchMangas() {
        displayDefaultResults();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MangaListResponse> call = apiService.getMangas();
        call.enqueue(new Callback<MangaListResponse>() {
            @Override
            public void onResponse(Call<MangaListResponse> call, Response<MangaListResponse> response) {
                mangas = response.body().getResults();
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
        connectionFailedLayout.setVisibility(View.GONE);
        connectionSuccessLayout.setVisibility(View.GONE);
    }

    private void displaySuccessResults(List<Manga> mangas) {
        connectionSuccessLayout.setVisibility(View.VISIBLE);

        connectionSuccessLayout.setLayoutManager(new LinearLayoutManager(this));
        connectionSuccessLayout.setAdapter(new MangaListAdapter(getApplicationContext(), mangas));
    }

    private void displayFailResults() {
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
