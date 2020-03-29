package io.github.vishalecho.android.assignment.newsfeed.ui;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import io.github.vishalecho.android.assignment.newsfeed.R;

import static io.github.vishalecho.android.assignment.newsfeed.util.Constant.EXTRA_ARTICLE_WEB_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {

    private ProgressBar progressBar;

    public WebViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        String url = getArguments().getString(EXTRA_ARTICLE_WEB_URL);
        initUI(view, url);
        return view;
    }

    private void initUI(View view, String url) {
        WebView webView = view.findViewById(R.id.webView);
        progressBar = view.findViewById(R.id.progress_circular);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                progressBar.setVisibility(View.GONE);
            }
        });
        webView.loadUrl(url);
    }

}
