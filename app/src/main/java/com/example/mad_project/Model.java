package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Model extends AppCompatActivity {

    Spinner Gender,Stream,Degree,Work,Special;
    EditText SSC,HSC,MBA,Etest,degree_p;
    TextView result;
    Button predict;
    String url="https://placementdoor.herokuapp.com/predict";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        Gender=findViewById(R.id.gender);
        Stream=findViewById(R.id.stream);
        Degree=findViewById(R.id.degree);
        Work=findViewById(R.id.work_exp);
        Special=findViewById(R.id.specialization);
        SSC=findViewById(R.id.ssc);
        HSC=findViewById(R.id.hsc);
        MBA=findViewById(R.id.mba);
        degree_p=findViewById(R.id.degree_p);
        Etest=findViewById(R.id.etest);
        predict=findViewById(R.id.submit);
        result=findViewById(R.id.output);
        //output1=findViewById(R.id.output1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            String data=jsonObject.getString("query");

                            if(data.equals("Not Placed")){
                                result.setText("Sorry, you need to work harder!");

                            }
                            else if(data.equals("Placed")){
                                result.setText("Congratulations!! You will be placed");
                            }
                            else{
                                result.setText("This is invalid");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Model.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                }){

                   @Override
                   protected Map getParams(){
                       Map params = new HashMap();
                       String result5=Gender.getSelectedView().toString();
                       params.put("gender",result5);
                       params.put("ssc_p",SSC.getText().toString());
                       params.put("hsc_p",HSC.getText().toString());
                       String result1 = Stream.getSelectedView().toString();
                       params.put("hsc_s",result1);
                       String result2 = Degree.getSelectedView().toString();
                       params.put("degree_t",result2);
                       String result3 = Work.getSelectedView().toString();
                       params.put("work",result3);
                       String result4 = Special.getSelectedView().toString();
                       params.put("specialization",result4);
                       params.put("etest",Etest.getText().toString());
                       params.put("degree_p",degree_p.getText().toString());
                       params.put("mba_p",MBA.getText().toString());
                       return params;
                   }

                };
                RequestQueue queue= Volley.newRequestQueue(Model.this);
                queue.add(stringRequest);
            }
        });
    }
}