package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoPlayer extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener{

    String idVideo = "";
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube);

        Intent myIntent = getIntent();
        idVideo = myIntent.getStringExtra("data");
        Toast.makeText(getApplicationContext(), idVideo, Toast.LENGTH_SHORT).show();

        youTubePlayerView.initialize(YouTube.API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(idVideo);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, 999);
        }else {
            Toast.makeText(
                    getApplicationContext(),
                    "Video error \n" + youTubeInitializationResult.toString(),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 999){
            youTubePlayerView.initialize(YouTube.API_KEY, this);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}