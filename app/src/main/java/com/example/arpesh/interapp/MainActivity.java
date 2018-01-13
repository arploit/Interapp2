package com.example.arpesh.interapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

   // Button btn;

    private EditText inputEmail, inputPassword;
    private Button LoginButton , SignInButton;
    private FirebaseAuth mainActivity_auth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
       // btn =(Button) findViewById(R.id.login_button);
        inputEmail = (EditText) findViewById(R.id.MainActivity_Email);
        inputPassword = (EditText) findViewById(R.id.MainActivity_password);
        LoginButton = (Button) findViewById(R.id.MainActivity_LoginButton);
        SignInButton = (Button) findViewById(R.id.MainActivity_SignupButton);
        mainActivity_auth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.MainActivity_ProgressBar);
        FirebaseUser currentUser = mainActivity_auth.getCurrentUser();


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Email!", Toast.LENGTH_SHORT).show();

                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();

                }

                mainActivity_auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                     if (!task.isSuccessful()){
                                         Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                     }else{
                                         progressBar.setVisibility(View.GONE);

                                                       Toast.makeText(MainActivity.this,"Login Sucessful", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(MainActivity.this , profile.class));
                                            }


                        }
                    });
            }
        });

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    btn.setBackground(this.getResources().getDrawable(R.drawable.button_backgroundpressed));
//      new Handler().postdelay
                Intent intent = new Intent(MainActivity.this, signup_activity.class);
                Toast.makeText(MainActivity.this,"Stay were signals are present", Toast.LENGTH_LONG).show();
                startActivity(intent);

            }
        });

    }



}
