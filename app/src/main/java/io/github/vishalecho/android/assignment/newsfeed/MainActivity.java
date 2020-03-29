package io.github.vishalecho.android.assignment.newsfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.github.vishalecho.android.assignment.newsfeed.data.model.Article;
import io.github.vishalecho.android.assignment.newsfeed.data.model.NewsFeedApiResponse;
import io.github.vishalecho.android.assignment.newsfeed.data.model.Source;
import io.github.vishalecho.android.assignment.newsfeed.data.remote.ApiHandler;
import io.github.vishalecho.android.assignment.newsfeed.ui.HomeFragment;

import static io.github.vishalecho.android.assignment.newsfeed.util.Constant.EXTRA_NEWS_FEED_API_RESPONSE;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);
        new RequestAsync().execute();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

    public class RequestAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                return ApiHandler.callNewsFeedApi();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            if (s != null) {
                NewsFeedApiResponse response = new NewsFeedApiResponse();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    response.setStatus(jsonObject.getString("status"));
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");
                    List<JSONObject> jsonObjectArrayList = new ArrayList<>(jsonArray.length());
                    List<Article> articles = new ArrayList<>();
                    for (int i=0; i< jsonArray.length(); i++) {
                        jsonObjectArrayList.add(jsonArray.getJSONObject(i));
                        articles.add(new Article(
                                new Source(jsonObjectArrayList.get(i).getJSONObject("source").getString("id"), jsonObjectArrayList.get(i).getJSONObject("source").getString("name")),
                                jsonObjectArrayList.get(i).getString("author"),
                                jsonObjectArrayList.get(i).getString("title"),
                                jsonObjectArrayList.get(i).getString("description"),
                                jsonObjectArrayList.get(i).getString("url"),
                                jsonObjectArrayList.get(i).getString("urlToImage"),
                                jsonObjectArrayList.get(i).getString("publishedAt"),
                                jsonObjectArrayList.get(i).getString("content")
                                ));
                    }
                    response.setArticles(articles);
                }catch (JSONException e) {
                    Log.e("TAG", "onPostExecute: "+ e.getMessage());
                }

                Bundle bundle = new Bundle();
                bundle.putSerializable(EXTRA_NEWS_FEED_API_RESPONSE, response);
                // Create new fragment and transaction
                Fragment homeFragment = new HomeFragment();
                homeFragment.setArguments(bundle);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(android.R.id.content, homeFragment);

                // Commit the transaction
                transaction.commit();
            }
        }
    }
}
