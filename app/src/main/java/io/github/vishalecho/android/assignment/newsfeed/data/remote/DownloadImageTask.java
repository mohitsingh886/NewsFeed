package io.github.vishalecho.android.assignment.newsfeed.data.remote;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView bmImage;
    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urlDisplay = urls[0];
        Bitmap bmp = null;
        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bmp;
    }
    protected void onPostExecute(Bitmap bmp) {
        if (bmp != null) {
            bmImage.setVisibility(View.VISIBLE);
            bmImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, 128, 96, false));
            bmp.recycle();
        } else {
            bmImage.setVisibility(View.GONE);
        }
    }
}
