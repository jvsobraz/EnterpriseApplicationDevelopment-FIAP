package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "trackArtist")
public class TrackArtist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    private Track track_ID;
    @Id
    @ManyToOne
    private Artist artist_ID;
    @Id
    @ManyToOne
    private Artist featuring_artist_ID;

    public TrackArtist(Track track_ID, Artist artist_ID, Artist featuring_artist_ID) {
        this.track_ID = track_ID;
        this.artist_ID = artist_ID;
        this.featuring_artist_ID = featuring_artist_ID;
    }

    public Track getTrack_ID() {
        return track_ID;
    }

    public void setTrack_ID(Track track_ID) {
        this.track_ID = track_ID;
    }

    public Artist getArtist_ID() {
        return artist_ID;
    }

    public void setArtist_ID(Artist artist_ID) {
        this.artist_ID = artist_ID;
    }

    public Artist getFeaturing_artist_ID() {
        return featuring_artist_ID;
    }

    public void setFeaturing_artist_ID(Artist featuring_artist_ID) {
        this.featuring_artist_ID = featuring_artist_ID;
    }
}
