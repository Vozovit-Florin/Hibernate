package com.example.HibernateAssigment;

import jakarta.persistence.*;

    @Entity
    @Table(name = "albums")
    public class Album {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "artist_id", nullable = false)
        private Artist artist;

        @Column(nullable = false)
        private String title;

        @Column(name = "release_year", nullable = false)
        private int releaseYear;

        @Column(name = "record_label", nullable = false)
        private String recordLabel;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Artist getArtist() {
            return artist;
        }

        public void setArtist(Artist artist) {
            this.artist = artist;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getReleaseYear() {
            return releaseYear;
        }

        public void setReleaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
        }

        public String getRecordLabel() {
            return recordLabel;
        }

        public void setRecordLabel(String recordLabel) {
            this.recordLabel = recordLabel;
        }
    }
