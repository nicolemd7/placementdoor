package com.example.mad_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class YouTube extends AppCompatActivity {

    String ID_PLAYLIST = "PLdo5W4Nhv31YvlDpJhvOYbM9Ap8UypgEy";
    public  static String API_KEY = "AIzaSyCg-mnYl5UPpyotjEQBNCCizSJMl55Ujx8";
    ListView lvVideo;
    ArrayList<Video> mangVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        lvVideo = (ListView) findViewById(R.id.listViewVideo);
        mangVideo = new ArrayList<Video>();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new GetJsonYoutube().execute("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId="+ ID_PLAYLIST +"&key=" + API_KEY);
            }
        });

        lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(YouTube.this, VideoPlayer.class);
                intent.putExtra("data", mangVideo.get(position).IdVideo);
                startActivity(intent);
            }
        });
    }

    class GetJsonYoutube extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonItems = jsonObject.getJSONArray("items");

                String title = "";
                String url = "";
                String videoId = "";

                for (int i = 0; i < jsonItems.length(); i++){
                    JSONObject jsonItem = jsonItems.getJSONObject(i);
                    JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                    title = jsonSnippet.getString("title");
                    JSONObject jsonThumnail = jsonSnippet.getJSONObject("thumbnails");
                    JSONObject jsonDefault = jsonThumnail.getJSONObject("default");
                    url = jsonDefault.getString("url");
                    JSONObject jsonResource =  jsonSnippet.getJSONObject("resourceId");
                    videoId = jsonResource.getString("videoId");

                    mangVideo.add(new Video(title, url, videoId));
                }

                VideoAdapter adapter = new VideoAdapter(
                        getApplicationContext(),
                        R.layout.dong_video,
                        mangVideo
                );

                lvVideo.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static String docNoiDung_Tu_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }

}
