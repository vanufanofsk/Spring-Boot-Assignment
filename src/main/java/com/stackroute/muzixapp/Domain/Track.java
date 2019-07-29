package com.stackroute.muzixapp.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "Track")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {
    @Id
    private int id;
    private String name;
    private String artist;

   /* public Track() {
    }

    public Track(int id, String trackName, String trackComments) {
        this.id = id;
        this.trackName = trackName;
        this.trackComments = trackComments;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", trackName='" + trackName + '\'' +
                ", trackComments='" + trackComments + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackComments() {
        return trackComments;
    }

    public void setTrackComments(String trackComments) {
        this.trackComments = trackComments;
    }*/
}
