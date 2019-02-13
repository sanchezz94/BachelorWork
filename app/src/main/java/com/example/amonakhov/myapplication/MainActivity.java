package com.example.amonakhov.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.igorpresnyakov.myapplication.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    CustomList adapter = null;
    ArrayList<Children> tb = new ArrayList<Children>();
    public void updateImg(int pos)
    {
        Children a = tb.get(pos);
        Intent intent = new Intent(this, infoScreen.class);
        intent.putExtra("name",a.getName());
        intent.putExtra("bitmap",a.getBitMap());
        intent.putExtra("summ",a.getSum());
        intent.putExtra("diag",a.getDiagnosis());
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ParseTask().execute();

    }

    public void updateTable(ArrayList<String> ch,ArrayList<Bitmap> u){
        adapter = new
                CustomList(MainActivity.this,ch,u);
        ListView list =(ListView)findViewById(R.id.list);;
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                updateImg(position);

            }
        });
    }

    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("http://www.bfnn.ru/get-children/");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);

            try {
                JSONObject jObject = new JSONObject(resultJson);
                Iterator<String> keys = jObject.keys();
                int i =0;
                while( keys.hasNext() ) {
                    String key = (String)keys.next();
                    i++;
                    Children ch = new Children(jObject.getJSONObject(key));
                    tb.add(ch);
                }
                ArrayList<String> s = new ArrayList<String>();
                ArrayList<Bitmap> u = new ArrayList<Bitmap>();
                for (int j=0;j<tb.size();j++)
                {
                    s.add(tb.get(j).getName());
                    u.add(tb.get(j).getBitMap());
                }
                updateTable(s,u);
            }catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}


