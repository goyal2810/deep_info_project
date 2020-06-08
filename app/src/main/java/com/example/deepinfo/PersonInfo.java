package com.example.deepinfo;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "person_table")
public class PersonInfo {

    @PrimaryKey
    @NonNull
    private String userId;

    @NonNull
    private String userPassword;

    @NonNull
    private String userQuestion;

    @NonNull
    private String userAnswer;

    @NonNull
    public String deliveryAddress;


    public PersonInfo(@NonNull String userId, String userPassword, String userQuestion, String userAnswer, String deliveryAddress){

        this.userId = userId;
        this.userPassword = userPassword;
        this.userQuestion = userQuestion;
        this.userAnswer = userAnswer;
        this.deliveryAddress = deliveryAddress;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    @NonNull
    public String getUserPassword() {
        return userPassword;
    }

    @NonNull
    public String getUserQuestion() {
        return userQuestion;
    }

    @NonNull
    public String getUserAnswer() {
        return userAnswer;
    }

    @NonNull
    public String getDeliveryAddress() { return deliveryAddress; }
}
