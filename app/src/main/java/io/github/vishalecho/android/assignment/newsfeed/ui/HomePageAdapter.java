package io.github.vishalecho.android.assignment.newsfeed.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.vishalecho.android.assignment.newsfeed.R;
import io.github.vishalecho.android.assignment.newsfeed.data.model.Article;
import io.github.vishalecho.android.assignment.newsfeed.ui.component.NewsFeedCardView;

import static io.github.vishalecho.android.assignment.newsfeed.util.Constant.EXTRA_ARTICLE_WEB_URL;

public class HomePageAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private FragmentManager mFragmentManager;
    private List<Article> articles;

    public HomePageAdapter(Context context, FragmentManager fragmentManager, List<Article> articles) {
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_feed, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
        contentViewHolder.newsFeedCardView.bindData(articles.get(position));
        contentViewHolder.newsFeedCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(EXTRA_ARTICLE_WEB_URL, articles.get(position).getUrl());
                // Create new fragment and transaction
                Fragment webViewFragment = new WebViewFragment();
                webViewFragment.setArguments(bundle);
                FragmentTransaction transaction = mFragmentManager.beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                transaction.replace(android.R.id.content, webViewFragment);
                transaction.addToBackStack(WebViewFragment.class.getName());
                // Commit the transaction
                transaction.commit();

            }
        });
        if (position < articles.size() - 1)
            contentViewHolder.dividerFullLineView.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {

        private NewsFeedCardView newsFeedCardView;
        private View dividerFullLineView;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            newsFeedCardView = itemView.findViewById(R.id.news_feed_card_view);
            dividerFullLineView = itemView.findViewById(R.id.full_line_divider_view);
        }
    }
}
