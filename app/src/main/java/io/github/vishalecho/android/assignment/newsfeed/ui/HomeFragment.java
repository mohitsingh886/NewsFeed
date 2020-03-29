package io.github.vishalecho.android.assignment.newsfeed.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.github.vishalecho.android.assignment.newsfeed.R;
import io.github.vishalecho.android.assignment.newsfeed.data.model.Article;
import io.github.vishalecho.android.assignment.newsfeed.data.model.NewsFeedApiResponse;

import static io.github.vishalecho.android.assignment.newsfeed.util.Constant.EXTRA_NEWS_FEED_API_RESPONSE;

public class HomeFragment extends Fragment {
    private RecyclerView newsFeedRecyclerView;
    private HomePageAdapter homePageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_home, container, false);
        NewsFeedApiResponse newsFeedApiResponse = (NewsFeedApiResponse) getArguments().getSerializable(EXTRA_NEWS_FEED_API_RESPONSE);
        if (newsFeedApiResponse != null) {
            initUi(inflatedView, newsFeedApiResponse.getArticles());
        }
        return inflatedView;
    }

    private void initUi(View inflatedView, List<Article> articles) {
        newsFeedRecyclerView = inflatedView.findViewById(R.id.news_feed_recycler_view);
        newsFeedRecyclerView.setHasFixedSize(true);
        newsFeedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homePageAdapter = new HomePageAdapter(getContext(), getFragmentManager(), articles);
        newsFeedRecyclerView.setAdapter(homePageAdapter);
    }
}
