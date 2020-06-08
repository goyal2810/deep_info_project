package com.example.deepinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class SignUpScreen extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    public CheckBox recaptchaCheckbox;
    private ImageView profilePicture;
    private EditText userIdField, passwordField,securityAnswerField;
    private Spinner securityQuestionsField;
    private Button completedSignup;
    public Toolbar aToolbar;


    GoogleApiClient googleApiClient;
    PersonViewModel personViewModel;
    Bitmap cameraImage = null;

    AwesomeValidation signUpValidation;

    String siteKey = "6Ld1GvkUAAAAAN1eoa6xCZ5_ccdEuFz2VSIDpwRJ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

     // Fetching value form field
     userIdField = findViewById(R.id.input_user_id);
     passwordField = findViewById(R.id.input_user_password);
     securityAnswerField= findViewById(R.id.security_answer);
     recaptchaCheckbox = findViewById(R.id.recaptcha_checkbox);
     profilePicture =  findViewById(R.id.profile_pic);
     securityQuestionsField = (Spinner) findViewById(R.id.security_questions);
     completedSignup = findViewById(R.id.signup_complete);

     aToolbar = (Toolbar) findViewById(R.id.sign_up_toolbar);
     setSupportActionBar(aToolbar);
     getSupportActionBar().setTitle("Enter Sign up details..");
     aToolbar.setNavigationIcon(R.drawable.back_button_foreground);
     aToolbar.setNavigationOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent mainActivity = new Intent(SignUpScreen.this, MainActivity.class);
             startActivity(mainActivity);
         }
     });


     personViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);

     googleApiClient = new GoogleApiClient.Builder(this)
             .addApi(SafetyNet.API)
             .addConnectionCallbacks(SignUpScreen.this)
             .build();

     googleApiClient.connect();

     profilePicture.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             setProfilePicture();
         }
     });

     // Adding Questions to spinner
        List<String> questions = new ArrayList<String>();
        questions.add("Select any security question");
        questions.add("Who was your first teacher?");
        questions.add("What is your favourite food?");
        questions.add("What is your dream city?");
        questions.add("What is your favourite movie?");
        questions.add("What your tenth percentage?");

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, R.layout.background_spinner, questions);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        securityQuestionsField.setAdapter(adapter);
        securityQuestionsField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                securityQuestionsField.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


     recaptchaCheckbox.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            showRecaptchaApi();
         }
     });

     completedSignup.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             onCompleteSignupPressed();
         }
     });

    }

    public void setProfilePicture(){
        final CharSequence[] options = {"Open Camera", "Select From Gallery", "Cancel"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(SignUpScreen.this);
        builder.setTitle(R.string.alert_title);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            if(options[which].equals("Open Camera")){
                Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(openCamera, 1);
            } else if(options[which].equals("Select From Gallery")){
                Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(openGallery, 2);
            } else if(options[which].equals("Cancel")){
                dialog.cancel();
            }
            }
        });
    builder.show();
    }

    public void showRecaptchaApi() {
        new InnerRecaptchaFetch().execute();
    }

    public void onCompleteSignupPressed() {
        int position = securityQuestionsField.getSelectedItemPosition();
        if(position == 0){
            Toast.makeText(this, "Please select security question", Toast.LENGTH_LONG).show();
            return;
        }

        AwesomeValidation validationResult = validatePage();
        if(validationResult.validate()) {
            // Parsing value to string
            String userName = userIdField.getText().toString();
            String password = passwordField.getText().toString();
            String securityAnswer = securityAnswerField.getText().toString();
            String securityQuestion = securityQuestionsField.getSelectedItem().toString();

            String hashPassword = md5(password);

//      Convertion of Image view to byte array
//        Bitmap bitmap = ((BitmapDrawable)profilePicture.getDrawable()).getBitmap();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        cameraImage.compress(Bitmap.CompressFormat.JPEG, 0,baos);
//        byte[] profileImageByte = baos.toByteArray();

            PersonInfo personInfo = new PersonInfo(userName, hashPassword, securityQuestion, securityAnswer, userName);
            personViewModel.insert(personInfo);
            Toast.makeText(this, "Data Saved!! Now Login Quickly", Toast.LENGTH_LONG).show();

        }
    }

    public String md5(String s){
        try{
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for(int i = 0; i< s.length(); i++){
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
                return hexString.toString();
            }
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                Bitmap cameraImage = (Bitmap) data.getExtras().get("data");
                profilePicture.setImageBitmap(cameraImage);
            case 2:
                Uri galleryImage = data.getData();
                profilePicture.setImageURI(galleryImage);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) { }

    @Override
    public void onConnectionSuspended(int i) { }

    public AwesomeValidation validatePage(){
        signUpValidation = new AwesomeValidation(ValidationStyle.BASIC);
        signUpValidation.addValidation(this,R.id.input_user_id, RegexTemplate.NOT_EMPTY, R.string.enter_user_id);
        signUpValidation.addValidation(this, R.id.input_user_password, ".{8}", R.string.enter_user_password);
        signUpValidation.addValidation(this,R.id.confirm_password, R.id.input_user_password, R.string.mismatch_confirm_password);
        signUpValidation.addValidation(this, R.id.security_answer, RegexTemplate.NOT_EMPTY, R.string.enter_security_answer);

        return signUpValidation;
    }

    private class InnerRecaptchaFetch extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if (recaptchaCheckbox.isChecked()) {
                SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, siteKey)
                        .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                            @Override
                            public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                com.google.android.gms.common.api.Status status = recaptchaTokenResult.getStatus();
                                if (status != null && status.isSuccess()) {
//                                      Toast.makeText(SignUpScreen.this, "Great sucess", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                recaptchaCheckbox.setTextColor(Color.GRAY);
            }
            return null;
        }
    }
}
