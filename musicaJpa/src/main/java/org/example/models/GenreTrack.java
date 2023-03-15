package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "genreTrack")
public class GenreTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    private Genre genre_ID;
    @Id
    @ManyToOne
    private Track track_ID;

    public GenreTrack(Genre genre_ID, Track track_ID) {
        this.genre_ID = genre_ID;
        this.track_ID = track_ID;
    }

    public Genre getGenre_ID() {
        return genre_ID;
    }

    public void setGenre_ID(Genre genre_ID) {
        this.genre_ID = genre_ID;
    }

    public Track getTrack_ID() {
        return track_ID;
    }

    public void setTrack_ID(Track track_ID) {
        this.track_ID = track_ID;
    }
}
