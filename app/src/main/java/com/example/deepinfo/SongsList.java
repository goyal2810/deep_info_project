package com.example.deepinfo;

public class SongsList {
    private String title, artist, coverImage, songUrl;

    public SongsList(){}

    public SongsList(String title, String artist, String coverImage, String songUrl){

        this.title = title;
        this.artist = artist;
        this.coverImage = coverImage;
        this.songUrl = songUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

}
