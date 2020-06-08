package com.example.deepinfo;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MusicPlayer extends AppCompatActivity {

    private Button playButton, pauseButton, stopButton;
    MediaPlayer mediaPlayer;
    int mediaPauseLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        getSupportActionBar().hide();

        playButton = findViewById(R.id.play_button);
        pauseButton = findViewById(R.id.pause_button);
        stopButton = findViewById(R.id.stop_button);

    }

    public void pauseMusic(View view) {
        if(mediaPlayer != null){
            mediaPlayer.pause();
            mediaPauseLocation = mediaPlayer.getCurrentPosition();
        }

    }

    public void playMusic(View view) {
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ve);
            mediaPlayer.start();
        }

        else if(!mediaPlayer.isPlaying()){
            mediaPlayer.seekTo(mediaPauseLocation);
            mediaPlayer.start();
        }
    }

    public void stopMusic(View view) {
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
    }
}
