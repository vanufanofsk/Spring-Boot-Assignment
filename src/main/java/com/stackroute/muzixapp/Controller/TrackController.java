package com.stackroute.muzixapp.Controller;
import com.stackroute.muzixapp.Domain.Track;
import com.stackroute.muzixapp.Exception.TrackAlreadyExistsException;
import com.stackroute.muzixapp.Exception.TrackNotFoundException;
import com.stackroute.muzixapp.Repository.TrackRepository;
import com.stackroute.muzixapp.Service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {

    public TrackService trackService;
    @Autowired
    public TrackController(TrackService trackService)
    {
        this.trackService=trackService;
    }
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track)throws TrackAlreadyExistsException
    {
      ResponseEntity responseEntity;
       /* Track savedTrack=null;*/

       try {
           trackService.saveTrack(track);
           responseEntity=new ResponseEntity<String>("success", HttpStatus.CREATED);


        } catch (TrackAlreadyExistsException e) {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }
    @GetMapping("track")
    public  ResponseEntity<?> getAllTracks(){
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable("id") int id)
    {
        trackService.deleteTrackById(id);
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }
    @DeleteMapping("track/delete")
    public ResponseEntity<?> deleteAllTrack()
    {
     trackService.deleteAllTrack();
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }
    @PutMapping("track/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") int id,@RequestBody Track track)
    {ResponseEntity responseEntity;
        try {
            trackService.updateById(id,track);
            responseEntity=new ResponseEntity<String>("updated", HttpStatus.CREATED);
        } catch (TrackNotFoundException e) {

            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;
    }
    @GetMapping("track/search/{name}")
    public ResponseEntity<List<Track>> trackByName(@PathVariable String trackName)
    {
        ResponseEntity responseEntity;
        List<Track> music= null;
        music = trackService.trackByName(trackName);
        return new ResponseEntity<List<Track>>(music,HttpStatus.OK);
    }
}
