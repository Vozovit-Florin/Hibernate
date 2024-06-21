package com.example.HibernateAssigment;

import jakarta.persistence.*;

import java.util.Set;

@Entity
    @Table(name = "artists")
    public class Artist {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String type;

        @Column(name = "year_formed", nullable = false)
        private int yearFormed;

        @Column(name = "year_split")
        private Integer yearSplit;

        @Column(name = "official_site")
        private String officialSite;

        @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Set<Album> albums;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYearFormed() {
        return yearFormed;
    }

    public void setYearFormed(int yearFormed) {
        this.yearFormed = yearFormed;
    }

    public Integer getYearSplit() {
        return yearSplit;
    }

    public void setYearSplit(Integer yearSplit) {
        this.yearSplit = yearSplit;
    }

    public String getOfficialSite() {
        return officialSite;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}


