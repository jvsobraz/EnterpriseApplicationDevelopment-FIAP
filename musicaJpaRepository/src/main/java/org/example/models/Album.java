package org.example.models;



import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long album_ID;
    @Id
    @ManyToOne
    private Artist artist_ID;
    @Id
    @ManyToOne
    private RecordLabel record_label_ID;

    private String album_name;
    private String album_type;
    private Date album_released;
    private BigDecimal album_recorded;

    public Album(Long album_ID, Artist artist_ID, RecordLabel record_label_ID, String album_name, String album_type, Date album_released, BigDecimal album_recorded) {
        this.album_ID = album_ID;
        this.artist_ID = artist_ID;
        this.record_label_ID = record_label_ID;
        this.album_name = album_name;
        this.album_type = album_type;
        this.album_released = album_released;
        this.album_recorded = album_recorded;
    }

    public Long getAlbum_ID() {
        return album_ID;
    }

    public void setAlbum_ID(Long album_ID) {
        this.album_ID = album_ID;
    }

    public Artist getArtist_ID() {
        return artist_ID;
    }

    public void setArtist_ID(Artist artist_ID) {
        this.artist_ID = artist_ID;
    }

    public RecordLabel getRecord_label_ID() {
        return record_label_ID;
    }

    public void setRecord_label_ID(RecordLabel record_label_ID) {
        this.record_label_ID = record_label_ID;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getAlbum_type() {
        return album_type;
    }

    public void setAlbum_type(String album_type) {
        this.album_type = album_type;
    }

    public Date getAlbum_released() {
        return album_released;
    }

    public void setAlbum_released(Date album_released) {
        this.album_released = album_released;
    }

    public BigDecimal getAlbum_recorded() {
        return album_recorded;
    }

    public void setAlbum_recorded(BigDecimal album_recorded) {
        this.album_recorded = album_recorded;
    }
}
