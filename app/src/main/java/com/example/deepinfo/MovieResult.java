package com.example.deepinfo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieResult extends AppCompatActivity {

    RecyclerView recyclerView;
    List<SongsList> song;
    MusicAdapter musicAdapter;
    ProgressBar loadMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_result);

        recyclerView = findViewById(R.id.songs_list);
        loadMovie = findViewById(R.id.load_movie_progress);
        song = new ArrayList<>();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Boolean isNetworkConnected = checkNetworkConnectivity();
        if(!isNetworkConnected){
            loadMovie.setVisibility(View.GONE);
            Toast.makeText(this, "Check Network Connectivity", Toast.LENGTH_LONG).show();
        } else{
            extractSongs();
        }
    }

    public void extractSongs() {
        RequestQueue queue =  Volley.newRequestQueue(this);
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, "https://simplifiedcoding.net/demos/view-flipper/heroes.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    try {
                        JSONObject songObject = new JSONObject(response);
                        JSONArray heroArray =  songObject.getJSONArray("heroes");

                        if(heroArray.length() > 0){
                            loadMovie.setVisibility(View.GONE);
                        }

                        for (int i = 0; i < heroArray.length(); i++) {

                         JSONObject heroObject = heroArray.getJSONObject(i);

                        SongsList songs = new SongsList();
                        songs.setTitle(heroObject.getString("name"));
                        songs.setArtist("Unknown");
                        songs.setCoverImage(heroObject.getString("imageurl"));
                        songs.setSongUrl("Unkown");
                        song.add(songs);
                    }

                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        musicAdapter = new MusicAdapter(getApplicationContext(), song);
                        recyclerView.setAdapter(musicAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Oops Something Went Wrong");
            }
        });

        queue.add(jsonObjectRequest);

    }

    public void searchMovie(final String movieName) {
        RequestQueue queue =  Volley.newRequestQueue(this);
        final StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, "https://simplifiedcoding.net/demos/view-flipper/heroes.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject songObject = new JSONObject(response);
                    JSONArray heroArray =  songObject.getJSONArray("heroes");

                    for (int i = 0; i < heroArray.length(); i++) {

                        JSONObject heroObject = heroArray.getJSONObject(i);

                        SongsList songs = new SongsList();
                        String getMovieName = heroObject.getString("name");
                        if (getMovieName.equals(movieName)) {
                            musicAdapter.deleteData(heroArray.length());
                            songs.setTitle(heroObject.getString("name"));
                            songs.setArtist("Unknown");
                            songs.setCoverImage(heroObject.getString("imageurl"));
                            songs.setSongUrl("Unkown");
                            song.add(songs);
                        }
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    musicAdapter = new MusicAdapter(getApplicationContext(), song);
                    recyclerView.setAdapter(musicAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Oops Something Went Wrong");
            }
        });

        queue.add(jsonObjectRequest);

    }

    public void searchMovie(View view) {
        EditText inputMovie = findViewById(R.id.search_name);
        String movieName = inputMovie.getText().toString();
        Boolean isNetworkConnected = checkNetworkConnectivity();
        if(!isNetworkConnected){
            loadMovie.setVisibility(View.GONE);
            Toast.makeText(this, "Check Network Connectivity", Toast.LENGTH_LONG).show();
        } else if(movieName.isEmpty()){
            loadMovie.setVisibility(View.GONE);
            Toast.makeText(this, "Please type movie name", Toast.LENGTH_LONG).show();
        } else {
            searchMovie(movieName);
        }
    }

    public void show_All(View view) {
        Boolean isNetworkConnected = checkNetworkConnectivity();
        if(!isNetworkConnected){
            loadMovie.setVisibility(View.GONE);
            Toast.makeText(this, "Check Network Connectivity", Toast.LENGTH_LONG).show();
        } else{
            musicAdapter.deleteData(0);
            extractSongs();
        }
    }

    public Boolean checkNetworkConnectivity(){

        ConnectivityManager connManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if(connManager != null){
            networkInfo = connManager.getActiveNetworkInfo();
        }
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        } else {
            return false;
        }
    }
}
