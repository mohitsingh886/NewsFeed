package io.github.vishalecho.android.assignment.newsfeed.ui.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.github.vishalecho.android.assignment.newsfeed.R;
import io.github.vishalecho.android.assignment.newsfeed.data.model.Article;
import io.github.vishalecho.android.assignment.newsfeed.data.remote.DownloadImageTask;
import io.github.vishalecho.android.assignment.newsfeed.ui.base.BaseView;
import io.github.vishalecho.android.assignment.newsfeed.util.DateTimeUtil;
import io.github.vishalecho.android.assignment.newsfeed.util.StringUtil;

public class NewsFeedCardView extends BaseView {

    private Context mContext;

    private TextView tvPubShortNameLogo;
    private TextView tvPubFullName;
    private ImageButton btnNewsPostOfflineState;
    private TextView tvNewsTitle;
    private TextView tvNewsContent;
    private TextView tvNewsDescription;
    private ImageView ivNewsPostImage;
    private TextView tvNewsPublishDateTime;
    private TextView tvNewsPostAuthorName;


    public NewsFeedCardView(@NonNull Context context) {
        super(context, null);
    }

    public NewsFeedCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public NewsFeedCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void viewInflated(View view, String type, AttributeSet attrs) {
        initView();
    }

    private void initView() {
        tvPubShortNameLogo = view.findViewById(R.id.tv_pub_short_name);
        tvPubFullName = view.findViewById(R.id.tv_pub_full_name);
        btnNewsPostOfflineState = view.findViewById(R.id.btn_offline_state);
        tvNewsTitle = view.findViewById(R.id.tv_news_title);
        tvNewsContent = view.findViewById(R.id.tv_news_content);
        tvNewsDescription = view.findViewById(R.id.tv_news_description);
        ivNewsPostImage = view.findViewById(R.id.img_news_post);
        tvNewsPublishDateTime = view.findViewById(R.id.tv_pub_datetime);
        tvNewsPostAuthorName = view.findViewById(R.id.tv_auth_name);
    }

    @Override
    protected int provideLayoutId(String type) {
        return R.layout.component_news_feed_card_view;
    }

    public void bindData(Article article) {
        tvPubShortNameLogo.setText(StringUtil.checkIfNotEmpty(article.getSource().getName()) ? String.valueOf(article.getSource().getName().charAt(0)) : "");
        tvPubFullName.setText(StringUtil.checkIfNotEmpty(article.getSource().getName()) ? article.getSource().getName() : "");
        btnNewsPostOfflineState.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNewsPostOfflineState.setImageResource(R.drawable.ic_available_offline);
            }
        });
        tvNewsTitle.setText(StringUtil.checkIfNotEmpty(article.getTitle()) ? article.getTitle() : "");
        tvNewsContent.setText(StringUtil.checkIfNotEmpty(article.getContent()) ?  getResources().getString(R.string.news_content)+": "+article.getContent() : "");
        tvNewsDescription.setText(StringUtil.checkIfNotEmpty(article.getDescription()) ? getResources().getString(R.string.news_description)+": "+article.getDescription() : "");
        new DownloadImageTask(ivNewsPostImage).execute(article.getUrlToImage());
        tvNewsPublishDateTime.setText(StringUtil.checkIfNotEmpty(article.getPublishedAt()) ? DateTimeUtil.convertDateToString(DateTimeUtil.getFormattedServerDate(article.getPublishedAt())) : "");
        tvNewsPostAuthorName.setText(StringUtil.checkIfNotEmpty(article.getAuthor()) ? article.getAuthor() : "");
    }

}
