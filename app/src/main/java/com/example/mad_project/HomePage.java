package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    Button pred_model;
Button vid;
Button article;
Button counsel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        vid = findViewById(R.id.videos);
        article = findViewById(R.id.articles);
        counsel = findViewById(R.id.counsellor);
        pred_model=findViewById(R.id.model);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pred_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( HomePage.this,Model.class));
            }
        });
        vid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,YouTube.class));
            }
        });
        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this,Articles.class));
            }
        });
counsel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:9082242365"));
        startActivity(callIntent);
    }
});
    }
}