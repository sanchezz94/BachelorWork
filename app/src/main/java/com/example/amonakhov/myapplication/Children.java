package com.example.amonakhov.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class Children implements Serializable {

    private String name;
    private String diagnosis;
    private String sum;
    private String icon;
    private Bitmap bitMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Children(JSONObject obj){
        try {
            name = obj.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            diagnosis = obj.getString("diagnosis");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            sum = obj.getString("sum");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            icon = obj.getString("icon");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            String a = new LoadImages().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getBitMap() {
        return bitMap;
    }

    public void setBitMap(Bitmap bitMap) {
        this.bitMap = bitMap;
    }

    private class LoadImages extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            URL url = null;
            try {
                url = new URL(icon);
                String path = url.getPath();
                String idStr = path.substring(path.lastIndexOf('/') + 1);
                String encoded = URLEncoder.encode(idStr, "utf-8");
                url = new URL(icon.replace(idStr,encoded));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Bitmap bmp = null;

            try {
                bitMap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(String strJson) {

        }
    }
}
