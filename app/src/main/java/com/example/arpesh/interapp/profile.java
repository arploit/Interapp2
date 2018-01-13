package com.example.arpesh.interapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by arpesh on 10/1/18.
 */
 class USER{

    public String name;
    public String email;
    public String mobileNumber;
    public String LandlineNUmber;
    public String Address;

    public void USER(String name , String email, String MobileNumber , String LandLineNumber ,String Address){
        this.name = name;
        this.email = email;
        this.mobileNumber = MobileNumber;
        this.LandlineNUmber = LandLineNumber;
        this.Address = Address;
    }

}

public class profile extends AppCompatActivity {
    private EditText inputFName,inputLName,inputEmail;
    private EditText inputMobileN,inputLandlineN;
    private EditText inputHouseN , inputStreetAdd ,inputPinC, inputState , inputCountry;
    private ImageButton inputImage;
    private final int CHOOSE_IMAGE =101;
    private Uri filePath;
    ProgressBar progressbar;
    FirebaseAuth mAuth;
       String profileURl ;
     Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        mAuth =FirebaseAuth.getInstance();
        inputFName = (EditText) findViewById(R.id.profileactivity_Fname);
        inputLName = (EditText) findViewById(R.id.profileactivity_LName);
        inputEmail = (EditText) findViewById(R.id.profileactivity_Email);
        inputMobileN = (EditText) findViewById(R.id.profileactivity_mobileNO);
        inputLandlineN = (EditText) findViewById(R.id.profileactivity_LandLineNO);
        inputHouseN = (EditText) findViewById(R.id.profileactivity_house);
        inputStreetAdd = (EditText) findViewById(R.id.profileactivity_StreetAd);
        inputPinC = (EditText) findViewById(R.id.profileactivity_pincode);
        inputState = (EditText) findViewById(R.id.profileactivity_State);
        inputCountry = (EditText) findViewById(R.id.profileactivity_Country);
        inputImage = (ImageButton) findViewById(R.id.profileactivity_ImageButton);
        Save = (Button) findViewById(R.id.profileactivity_SaveButton);
        progressbar = (ProgressBar) findViewById(R.id.profileactivity_ProgressBar);



        inputImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                saveUserInforemation();
            }
        });

    }

    private void saveUserInforemation() {

        String Fullname = inputFName.getText().toString().trim()+" "+inputLName.getText().toString().toString();
        String Email = inputEmail.getText().toString().trim();
        String Address = inputHouseN.getText().toString().trim()+" "+inputStreetAdd.getText().toString().trim()+" "
                +inputPinC.getText().toString().trim()+" "+inputState.getText().toString().trim()+" "
                +inputCountry.getText().toString().trim();

        String MobileN = inputMobileN.getText().toString().trim();
        String LandLineN = inputLandlineN.getText().toString().trim();


//first Name
                if(inputFName.getText().toString().trim().isEmpty())
                {
                    inputFName.setError("First Name Required");
                    inputFName.requestFocus();
                    return;
                }
  //last name
                if(inputLName.getText().toString().trim().isEmpty())
                {
            inputLName.setError("Last Name Required");
                    inputLName.requestFocus();
                    return;
                    }
            //email
                    if(Email.isEmpty())
                {

                    inputEmail.setError("Email required");
                    inputEmail.requestFocus();
                    return;
                }
                //ADDRESS

                if( inputHouseN.getText().toString().trim().isEmpty()){
                        inputHouseN.setError("H. No");
                    inputHouseN.requestFocus();
                    return;

                }
                if(inputStreetAdd.getText().toString().trim().isEmpty()){
                    inputStreetAdd.setError("Street address requires");
                        inputStreetAdd.requestFocus();
                    return;
                }
                if(inputCountry.getText().toString().isEmpty()){
                    inputCountry.setError("Country name required");
                    inputCountry.requestFocus();
                    return;
                }
                if(inputPinC.getText().toString().isEmpty()){
                    inputPinC.setError("Pin Code required");
                    inputPinC.requestFocus();
                    return;

                }
                if(inputState.getText().toString().trim().isEmpty()){
                    inputState.setError("State name Required");
                    inputState.requestFocus();
                    return;
                }
                //mobile number

                if(MobileN.isEmpty() || MobileN.length() != 10)
                {
                    inputMobileN.setError("Mobile number required");
                    inputMobileN.requestFocus();
                    return;
                }
                if(LandLineN.isEmpty() || LandLineN.length() != 11)
                {
                    inputLandlineN.setError("Landline number required");
                    inputLandlineN.requestFocus();
                    return;
                }

        FirebaseUser user = mAuth.getCurrentUser();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        USER user11 = new USER();
        user11.USER(Fullname,Email,MobileN,LandLineN,Address);

      //  public void USER(String name , String email, String MobileNumber , String LandLineNumber ,String Address)


        if(user!= null){
            progressbar.setVisibility(View.VISIBLE);
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(Fullname)
                    .setPhotoUri(Uri.parse(profileURl))
                    .build();

            user.updateProfile(profile)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Profile Updated",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(profile.this, end.class));
                    }
                }
            });


        }

    }

    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode ==RESULT_OK && data != null &&  data.getData()!= null){


            filePath= data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                inputImage.setImageBitmap(bitmap);
                upLoadImageToFirebase();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    private void upLoadImageToFirebase() {
        StorageReference profileImageReference  =
                FirebaseStorage.getInstance()
                        .getReference("ProfilePicture/"+ System.currentTimeMillis()+".jpg");

            if(filePath!= null)
            {
                progressbar.setVisibility(View.VISIBLE);

                profileImageReference.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            progressbar.setVisibility(View.GONE);
                      profileURl = taskSnapshot.getDownloadUrl().toString();
                        Toast.makeText(getApplicationContext(),"Image is updated",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Failed. Try again",Toast.LENGTH_SHORT).show();
                    }
                });
            }
    }
}
