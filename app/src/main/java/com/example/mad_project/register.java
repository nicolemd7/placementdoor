package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register extends AppCompatActivity {
    TextView alreadyaccount;
    EditText email,password1,password2;
    Button btn_register;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        alreadyaccount=findViewById(R.id.alreadyHaveAccount);
        email=findViewById(R.id.inputEmail);
        password1=findViewById(R.id.inputPassword);
        password2=findViewById(R.id.confirmPassword);
        btn_register=findViewById(R.id.btnRegister);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        alreadyaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this,login.class));
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }

            private void PerformAuth() {
                String email1=email.getText().toString();
                String pass=password1.getText().toString();
                String pass2=password2.getText().toString();

                if(!email1.matches(emailPattern)){
                    email.setError("Enter valid email");
                }
                else if(pass.isEmpty() || pass.length()<6){
                    password1.setError("Enter proper password");
                }
                else if(!pass.equals(pass2)){
                    password2.setError("Passwords don't match");
                }
                else{
                    progressDialog.setMessage("Please wait while we get you registered");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(email1,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                sendUserToNextActivity();
                                //startActivity(new Intent(register.this,HomePage.class));
                                Toast.makeText(register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(register.this, "", Toast.LENGTH_SHORT).show();
                            }
                        }


                    });

                }
            }

            private void sendUserToNextActivity() {
                Intent intent=new Intent(register.this,HomePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}