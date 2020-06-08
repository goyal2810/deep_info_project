package com.example.deepinfo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PersonInfo personInfo);

    @Query("SELECT * FROM person_table WHERE userId = :userId and userPassword = :userPassword")
    PersonInfo tryLogin(String userId, String userPassword);

    @Query("SELECT * FROM person_table WHERE userId = :userId")
    PersonInfo checkUserId(String userId);

    @Query("SELECT * FROM person_table WHERE userAnswer = :userAnswer ")
    PersonInfo checkSecurityAnswer(String userAnswer);

    @Query("UPDATE person_table SET deliveryAddress = :deliveryAddress WHERE userId = :userId")
    int updateAddress(String userId, String deliveryAddress);

    @Query("UPDATE person_table SET userPassword = :userPassword WHERE userId = :userId")
    int updatePassword(String userId, String userPassword);
}
