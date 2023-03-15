package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "albumTrack")
public class AlbumTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    private Album album_ID;
    @ManyToOne
    private Track track_ID;

    public AlbumTrack(Album album_ID, Track track_ID) {
        this.album_ID = album_ID;
        this.track_ID = track_ID;
    }

    public Album getAlbum_ID() {
        return album_ID;
    }

    public void setAlbum_ID(Album album_ID) {
        this.album_ID = album_ID;
    }

    public Track getTrack_ID() {
        return track_ID;
    }

    public void setTrack_ID(Track track_ID) {
        this.track_ID = track_ID;
    }
}
