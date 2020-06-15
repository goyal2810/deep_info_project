package com.example.deepinfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class MainActivity extends AppCompatActivity {

    private EditText userName, userPassword;
    private Button loginButton;
    private PersonDatabase personDatabase;
    private PersonDao personDao;
    private TextView forgetPassword;
    private AwesomeValidation awesomeValidation;
    private CheckBox remeberMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.user_name);
        userPassword = findViewById(R.id.password_name);
        forgetPassword = findViewById(R.id.forgot_password);
        remeberMe = findViewById(R.id.remember_me);

        getSupportActionBar().hide();

        personDatabase = Room.databaseBuilder(this, PersonDatabase.class, "person_database")
                .allowMainThreadQueries().build();
        personDao = personDatabase.personDao();

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedManagement sharedManagement = new SharedManagement(MainActivity.this);
        String getUser = sharedManagement.getSession();
        Boolean isChecked = sharedManagement.isRemebered();

        if (isChecked && getUser != null){
            Intent loginSuccesScreen = new Intent(MainActivity.this, LoginSucessScreen.class);
            startActivity(loginSuccesScreen);
        }
    }

    public void updatePassword(){
    Intent forgetPasswordScreen = new Intent(MainActivity.this, ForgetPassword.class);
    startActivity(forgetPasswordScreen);
    }

    public void openSignUpScreen(View view) {
        Intent signUpScreen = new Intent(MainActivity.this, SignUpScreen.class);
        startActivity(signUpScreen);
    }

    public void tapOnLoginButton(View view) {
        String idText = userName.getText().toString();
        String passText = userPassword.getText().toString();

        Utility utilityClass = new Utility();
        String hexaPassword = utilityClass.md5(passText);

        awesomeValidation.addValidation(this, R.id.user_name, RegexTemplate.NOT_EMPTY, R.string.enter_user_id);
        awesomeValidation.addValidation(this, R.id.password_name, ".{8,}", R.string.enter_user_password);
        if(awesomeValidation.validate()) {
            PersonInfo isSuccess = personDao.tryLogin(idText, hexaPassword);
            if (isSuccess != null) {
                Boolean isRemembered = remeberMe.isChecked();
                SharedManagement newSharedManagement = new SharedManagement(MainActivity.this);
                newSharedManagement.saveSession(isSuccess, isRemembered);

                Intent openLoginSuccessScreen = new Intent(MainActivity.this, LoginSucessScreen.class);
                startActivity(openLoginSuccessScreen);
            } else {
                showLoginFailureAlert();
            }
        }
    }

    public void showLoginFailureAlert(){
        AlertDialog.Builder loginFailureAlert = new AlertDialog.Builder(MainActivity.this);
        loginFailureAlert.setMessage("It seems your entered invalid user-id or password");
        loginFailureAlert.setCancelable(false);
        loginFailureAlert.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userName.setText("");
                userPassword.setText("");
                dialog.cancel();
            }
        });

    AlertDialog showAlert = loginFailureAlert.create();
    showAlert.setTitle("Warning!!");
    showAlert.show();

    }
}
