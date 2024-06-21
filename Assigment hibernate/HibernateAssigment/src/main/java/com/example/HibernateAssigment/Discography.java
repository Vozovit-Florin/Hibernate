package com.example.HibernateAssigment;


import java.util.List;

public class Discography {

    private Artist artist;
    private List<Album> albums;

    public Discography(Artist artist, List<Album> albums) {
        this.artist = artist;
        this.albums = albums;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}