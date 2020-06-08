package com.example.deepinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class ForgetPassword extends AppCompatActivity {
    private EditText validateUserId, validateSecurityAnswer, newPassword, confirmPassword;
    private TextView securtiyQuestion, newPasswordLabel;
    private Button verifySecurityAnswer, updatePasswordButton;
    private PersonDatabase personDatabase;
    private PersonDao personDao;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        validateUserId = findViewById(R.id.forgoten_user_id);
        newPasswordLabel = findViewById(R.id.new_password_label);
        newPassword = findViewById(R.id.new_passsword);
        confirmPassword = findViewById(R.id.new_confirm_password);
        verifySecurityAnswer = findViewById(R.id.verify_security_answer);
        updatePasswordButton = findViewById(R.id.update_password_button);


        personDatabase = Room.databaseBuilder(this, PersonDatabase.class, "person_database")
                .allowMainThreadQueries().build();
        personDao = personDatabase.personDao();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void checkUserIdInDatabase(View view) {
        String userId = validateUserId.getText().toString();
        PersonInfo getResult = personDao.checkUserId(userId);
        if(getResult != null){
            securtiyQuestion = findViewById(R.id.security_question);
            validateSecurityAnswer = findViewById(R.id.confirm_security_answer);

             String getSecurityQuestion =  getResult.getUserQuestion();

             securtiyQuestion.setText(getSecurityQuestion);
             securtiyQuestion.setVisibility(View.VISIBLE);
             validateSecurityAnswer.setVisibility(View.VISIBLE);
             verifySecurityAnswer.setVisibility(View.VISIBLE);
        } else{
            Toast.makeText(this, "Id Not Found", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkSecurityAnswer(View view) {
        String securityAnswer = validateSecurityAnswer.getText().toString();
        PersonInfo validateAnswer = personDao.checkSecurityAnswer(securityAnswer);
        if(validateAnswer != null){
            securtiyQuestion.setVisibility(View.GONE);
            validateSecurityAnswer.setVisibility(View.GONE);
            verifySecurityAnswer.setVisibility(View.GONE);
            newPasswordLabel.setVisibility(View.VISIBLE);
            newPassword.setVisibility(View.VISIBLE);
            confirmPassword.setVisibility(View.VISIBLE);
            updatePasswordButton.setVisibility(View.VISIBLE);
        } else {
            validateSecurityAnswer.setText("");
            securtiyQuestion.setVisibility(View.GONE);
            validateSecurityAnswer.setVisibility(View.GONE);
            verifySecurityAnswer.setVisibility(View.GONE);
        }
    }

    public void updatePassword(View view) {
        String newPasswordText = newPassword.getText().toString();
        String userId = validateUserId.getText().toString();

        int updatePassword = personDao.updatePassword(userId, newPasswordText);
        if(updatePassword > 0) {
            Toast.makeText(this, "Updated Sucessfully", Toast.LENGTH_SHORT).show();
            newPassword.setText("");
            confirmPassword.setText("");
            newPasswordLabel.setVisibility(View.GONE);
            newPassword.setVisibility(View.GONE);
            confirmPassword.setVisibility(View.GONE);
            updatePasswordButton.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
