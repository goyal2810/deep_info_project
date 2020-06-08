package com.example.deepinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class DeliveryLocation extends AppCompatActivity {

    private TextView addressField;
    private Button addressButton;
    private PersonDatabase personDatabase;
    private PersonDao personDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_location);

        addressField = findViewById(R.id.address_field);
        addressButton = findViewById(R.id.set_address);

        personDatabase = Room.databaseBuilder(this, PersonDatabase.class,"person_database")
                .allowMainThreadQueries().build();
        personDao = personDatabase.personDao();

        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAddress();
            }
        });
    }

    public void updateAddress(){
        String addressText = addressField.getText().toString();
        if(addressText.length() < 10){
            Toast.makeText(this, "Address length should be more than 10", Toast.LENGTH_SHORT).show();
        } else {
            SharedManagement sharedManagement = new SharedManagement(DeliveryLocation.this);
            String userSession =  sharedManagement.getSession();
            if(userSession != null){
                personDao.updateAddress(userSession, addressText);
                Toast.makeText(this, "Address updated Successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
