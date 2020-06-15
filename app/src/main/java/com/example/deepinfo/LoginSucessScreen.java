package com.example.deepinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.google.android.material.navigation.NavigationView;

public class LoginSucessScreen extends AppCompatActivity {

    private DrawerLayout drawerOptions;
    private ActionBarDrawerToggle aDT;
    private NavigationView nV;
    private PersonDatabase personDatabase;
    private PersonDao personDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sucess_screen);

        getSupportActionBar().setTitle("Now Shop From Here!!");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerOptions = findViewById(R.id.login_success_screen);
        aDT = new ActionBarDrawerToggle(this, drawerOptions,R.string.open, R.string.close);

        drawerOptions.addDrawerListener(aDT);
        aDT.syncState();

        PopularProducts popularProducts= new PopularProducts();
        getSupportFragmentManager().beginTransaction().replace(R.id.drawer_frame, popularProducts).commit();



        nV = (NavigationView) findViewById(R.id.navItem);
        nV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                switch (id){
                    case R.id.male:
                        fragment = new MaleDresses();
                    break;
                    case R.id.female:
                        fragment = new GirlDress();
                        break;
                    case R.id.kids:
                        fragment = new KidsDress();
                        break;
                    case R.id.fashion:
                        fragment = new FashionItem();
                    break;
                    case R.id.music:
                       Intent musicLibrary = new Intent(LoginSucessScreen.this, MusicPlayer.class);
                       startActivity(musicLibrary);
                    break;
                    case R.id.movie:
                        Intent searchMovie = new Intent(LoginSucessScreen.this, MovieResult.class);
                        startActivity(searchMovie);
                    break;
                    case R.id.deliverly_location:
                        Intent deliveryLocation =  new Intent(LoginSucessScreen.this, DeliveryLocation.class);
                        startActivity(deliveryLocation);
                    break;
                    case R.id.logout:
                        logoutCurrentUser();
                     break;
                }

                if(fragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.drawer_frame, fragment).commit();
                } else {
                    fragment = new PopularProducts();
                    getSupportFragmentManager().beginTransaction().replace(R.id.drawer_frame, fragment).commit();
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.support_action_bar, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId ==  R.id.cart_list){
            SharedManagement newSharedManagement = new SharedManagement(LoginSucessScreen.this);
            String currentUser = newSharedManagement.getSession();
            personDatabase = Room.databaseBuilder(this, PersonDatabase.class, "person_database")
                    .allowMainThreadQueries().build();
            personDao = personDatabase.personDao();
            PersonInfo getResult = personDao.checkUserId(currentUser);
            String getCurrentAddress = getResult.getDeliveryAddress();

            if(getCurrentAddress.length() < 10 ){
                Toast.makeText(this, "Set Delivery Address First", Toast.LENGTH_SHORT).show();
            } else {
                Intent showCartList = new Intent(LoginSucessScreen.this, CartList.class);
                startActivity(showCartList);
            }
        }
        if(aDT.onOptionsItemSelected(item)){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void logoutCurrentUser(){
        SharedManagement sharedManagement = new SharedManagement(LoginSucessScreen.this);
        sharedManagement.removeSession();
//        Intent logoutMe = new Intent(LoginSucessScreen.this, LogoutAnimation.class);
        Intent logoutMe = new Intent(LoginSucessScreen.this, MainActivity.class);
        startActivity(logoutMe);
    }
}
