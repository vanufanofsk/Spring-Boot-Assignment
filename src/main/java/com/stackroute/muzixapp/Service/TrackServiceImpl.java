package com.stackroute.muzixapp.Service;

import com.stackroute.muzixapp.Domain.Track;
import com.stackroute.muzixapp.Exception.TrackAlreadyExistsException;
import com.stackroute.muzixapp.Exception.TrackNotFoundException;
import com.stackroute.muzixapp.Repository.TrackRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService, CommandLineRunner, ApplicationListener<ContextRefreshedEvent> {

    @Value("${track.1.trackName:default}")
    String name;
    @Value("${track.1.trackComments:default}")
    String comments1;
    @Value("${track.2.trackName:default}")
    String name2;
    @Value("${track.2.trackComments:default}")
    String comments2;
    TrackRepository trackRepository;
@Autowired
    public TrackRepository getTrackRepository() {
        return trackRepository;
    }

    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository=trackRepository;
    }
    @Override
    public Track saveTrack(Track track)throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getId())){
            throw new TrackAlreadyExistsException("Track already exist");
    }
        Track track1= trackRepository.save(track);
        if(track1==null)
        {
            throw new TrackAlreadyExistsException("User Already Exists");
        }
        //Track savedTrack=trackRepository.save(track);
        return track1 ;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public void deleteTrackById(int id) {
        trackRepository.deleteById(id);

    }

    @Override
    public void deleteAllTrack() {
        trackRepository.deleteAll();
    }

    @Override
    public boolean updateById(int id,Track track) throws TrackNotFoundException {
        Optional<Track> optionalTrack=trackRepository.findById(id);
        if(!optionalTrack.isPresent())
        { throw new TrackNotFoundException("Track not found");
            }

            track.setId(id);
            trackRepository.save(track);
            return true;
    }

    @Override
    public List<Track> trackByName(String trackName) {
        List<Track> track_id=trackRepository.findTitleByName(trackName);
        return track_id;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        trackRepository.save(new Track(1, name,comments1));
        trackRepository.save(new Track(2, name2, comments2));
    }

    @Override
    public void run(String... args) throws Exception {

        final Log logger = LogFactory.getLog(getClass());
        logger.info("Application Started ..............!");
    }
}

