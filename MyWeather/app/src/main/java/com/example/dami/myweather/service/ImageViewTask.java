package com.example.dami.myweather.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;

public class ImageViewTask extends AsyncTask<ImageView, Void, Bitmap>{
    private final Context context;
    private ImageView imageView;

    public ImageViewTask(Context context){
        this.context = context;
    }
    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {
        imageView = imageViews[0];
        String icon = (String)imageViews[0].getTag();
        String urlStr = "http://openweathermap.org/img/w/" + icon + ".png";
        try{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(20000);
            conn.setRequestMethod("GET");
            conn.setReadTimeout(10000);
            conn.setDoInput(true);
            conn.connect();

            return BitmapFactory.decodeStream(conn.getInputStream());
        } catch(Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
        }
        super.onPostExecute(bitmap);
    }
}
