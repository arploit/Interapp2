package com.example.arpesh.interapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import android.app.Dialog;

import android.os.PersistableBundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuthProvider;

/**
 * Created by arpesh on 6/1/18.
 */

public class signup_activity extends AppCompatActivity  {
    Dialog Popforotp;
    private FirebaseAuth mAuth;
    private EditText inputmobile , inputpassword;
    Button SignUpButton;
    private  String TAG = "FragmentActivity";

    ProgressBar progressBar;
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.signup_activity);
        inputmobile = (EditText) findViewById(R.id.Signupactivity_mobileno);
        inputpassword = (EditText) findViewById(R.id.Signupactivity_password);
         SignUpButton =(Button) findViewById(R.id.SignupActivity_SignupButton);
      //  inputVerifyPassword = (EditText) findViewById(R.id.Signupactivity_verifypassword);
        progressBar = (ProgressBar) findViewById(R.id.Signupactivity_ProgressBar);
        mAuth = FirebaseAuth.getInstance();

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputmobile.getText().toString().trim();
                String password =inputpassword.getText().toString().trim();
                progressBar.setVisibility(View.VISIBLE);
                // String verifyPassword =  inputVerifyPassword.getText().toString().trim();


             //   int mobileNo = Integer.valueOf(inputmobile.getText().toString());
//              int mobileNo = Integer.parse(inputmobile.getText().toString())
//             if(password.equals(verifyPassword)) {
//                 inputVerifyPassword.setTextColor(ContextCompat.getColor(context , R.color.GreenEditText));

                 if (TextUtils.isEmpty(email)) {

                     Toast.makeText(getApplicationContext(), "PLease enter email", Toast.LENGTH_SHORT).show();
                 }
                 if (TextUtils.isEmpty(password)) {

                     Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_SHORT).show();
                 }

                 mAuth.createUserWithEmailAndPassword(email, password)
                         .addOnCompleteListener(signup_activity.this, new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {
                                 if (task.isSuccessful()) {
                                     // Sign in success, update UI with the signed-in user's information
                                     Log.d(TAG, "createUserWithEmail:success");
                                     progressBar.setVisibility(View.GONE);

                                     startActivity(new Intent(signup_activity.this, MainActivity.class));


//                                    Toast.makeText(getApplicationContext(),"Authentication Failed",Toast.LENGTH_SHORT).show();


                                 } else {
                                     // If sign in fails, display a message to the user.
                                     Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                     Toast.makeText(signup_activity.this, "Authentication failed.",
                                             Toast.LENGTH_SHORT).show();

                                 }

                             }
                         });
             }


//            }
        });


    }

//     public void Signup_activityButton(View view)
//    {
////       AlertDialog.Builder mbuilder = new AlertDialog.Builder(signup_activity.this);
////        View v1 = getLayoutInflater().inflate(R.layout.popforotp,null);
////        mbuilder.setView(v1);
////        AlertDialog dialog = mbuilder.create();
////        dialog.show();


    }

