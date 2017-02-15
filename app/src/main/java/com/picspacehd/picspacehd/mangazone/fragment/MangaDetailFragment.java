package com.picspacehd.picspacehd.mangazone.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.picspacehd.mangazone.R;
import com.picspacehd.picspacehd.mangazone.helper.AppConstants;
import com.picspacehd.picspacehd.mangazone.helper.Util;
import com.picspacehd.picspacehd.mangazone.model.MangaInfoResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MangaDetailFragment extends Fragment {

    @BindView(R.id.manga_info_cover)       ImageView cover;
    @BindView(R.id.manga_info_alias)       TextView alias;
    @BindView(R.id.manga_info_chapter_length) TextView chapterLength;
    @BindView(R.id.manga_info_latest)      TextView latestChapterDate;
    @BindView(R.id.manga_info_status)      TextView status;
    @BindView(R.id.manga_info_genres)      TextView genres;
    @BindView(R.id.manga_info_description) TextView description;

    public MangaDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manga_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    // TODO: use bundles and implement parcelable on POJOs to prevent tight coupling between the activity and Fragment
    public void displayResults(MangaInfoResponse results) {
        showCover(results.getImgPath());
        showAlias(results.getAlias());
        showLastChapter(results.getChapterLen());
        showLatestChapterDate(results.getLastChapterDate());
        showStatus(results.getStatus());
        showGeneres(results.getCategories());
        showDescription(results.getDescription());
    }

    private void showCover(String imgPath) {
        String coverLink = AppConstants.API_IMG_BASE_URL + imgPath;
        Glide.with(this).load(coverLink).into(cover);
    }
    private void showAlias(String alias) {
        this.alias.setText(alias);
    }
    private void showLastChapter(Integer num) {
        chapterLength.setText(Integer.toString(num));
    }
    private void showLatestChapterDate(Long date) {
        latestChapterDate.setText(Util.TIME_STAMP_TO_DATE(date));
    }
    private void showStatus(Integer status) {

        if (status.equals(AppConstants.ONGOING)) {
            this.status.setText(R.string.layout_item_status_ongoing);
            this.status.setTextColor(getResources().getColor(R.color.colorStatusOngoing));
        }
        else if(status.equals(AppConstants.FINISHED)) {
            this.status.setText(R.string.layout_item_status_finished);
            this.status.setTextColor(getResources().getColor(R.color.colorStatusFinished));
        }
        else {
            this.status.setText(R.string.layout_item_status_unknown);
        }
    }

    private void showGeneres(List<String> genreList) {
        String genres = "";

        for (String genre : genreList) {
            genres += genre + ", ";
        }
        if (genres.length() > 2) {
            genres = genres.substring(0, genres.length() - 2);
        }

        this.genres.setText(genres);
    }
    private void showDescription(String description) {
        this.description.setText(description);
    }
}
