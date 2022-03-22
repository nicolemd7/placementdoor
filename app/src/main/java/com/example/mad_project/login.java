package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    TextView register;
    //Button login;
    EditText email,password1;
    Button btn_login;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView forgotPass;
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register=findViewById(R.id.createNewAccount);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        email=findViewById(R.id.inputEmail);
        password1=findViewById(R.id.inputPassword);
        btn_login=findViewById(R.id.btnLogin);
        forgotPass=findViewById(R.id.forgotPassword);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( login.this,register.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
            private void PerformAuth() {
                String email1 = email.getText().toString();
                String pass = password1.getText().toString();


                if (!email1.matches(emailPattern)) {
                    email.setError("Enter valid email");
                } else if (pass.isEmpty() || pass.length() < 6) {
                    password1.setError("Enter proper password");
                } else {
                    progressDialog.setMessage("Please wait while we login");
                    progressDialog.setTitle("Login");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(email1,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                sendUserToNextActivity();
                                //startActivity(new Intent(register.this,HomePage.class));
                                Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(login.this, "Login Unsuccessful, Enter valid email and password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

            private void sendUserToNextActivity() {
                Intent intent=new Intent(login.this,HomePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle(("Reset Password ?"));
                passwordResetDialog.setMessage("Enter your email");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail=resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(login.this, "Check your mail for reset link", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(login.this, "Error! Incorrect email entered", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                passwordResetDialog.create().show();
            }
        });




    }
}