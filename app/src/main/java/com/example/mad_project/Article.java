package com.example.mad_project;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Article extends AppCompatActivity {
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        lv=findViewById(R.id.listView);
        String[] li = getResources().getStringArray(R.array.array_technology);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.listdesign,li);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://digitallearning.eletsonline.com/2019/06/campus-placement-in-india-an-overview/")));
                        break;


                    case 1:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://timesofindia.indiatimes.com/topic/placement/news")));
                        break;


                    case 2:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://journals.sagepub.com/doi/abs/10.1177/0950422220950191")));
                        break;

                    case 3:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://turcomat.org/index.php/turkbilmat/article/view/5868")));
                        break;

                    case 4:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.yourarticlelibrary.com/paragraphs/placement-short-paragraph-on-placement/35286")));
                        break;

                    case 5:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.frontiersin.org/articles/10.3389/feduc.2021.654843/full")));
                        break;
                    case 6:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.researchgate.net/publication/263459813_Students_on_placement_A_comparative_study")));
                        break;
                    case 7:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.tandfonline.com/doi/full/10.11120/plan.2006.00160043")));
                        break;
                    case 8:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.peoplematters.in/news/life-at-work/job-market-scenario-in-2021-28061")));
                        break;
                    case 9:
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.indiatoday.in/education-today/latest-studies/story/campus-hiring-hit-across-82-colleges-in-india-due-to-covid-19-survey-1695446-2020-06-30")));
                        break;


                }
            }
        });
    }
}