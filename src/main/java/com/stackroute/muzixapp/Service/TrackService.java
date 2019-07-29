package com.stackroute.muzixapp.Service;

import com.stackroute.muzixapp.Domain.Track;
import com.stackroute.muzixapp.Exception.TrackAlreadyExistsException;
import com.stackroute.muzixapp.Exception.TrackNotFoundException;

import java.util.List;

public interface TrackService {
    Track saveTrack(Track track) throws TrackAlreadyExistsException;
    List<Track> getAllTracks();
     void deleteTrackById(int id);
    void deleteAllTrack();
    boolean updateById(int id,Track track)throws TrackNotFoundException;
    List<Track> trackByName(String trackName);
}
