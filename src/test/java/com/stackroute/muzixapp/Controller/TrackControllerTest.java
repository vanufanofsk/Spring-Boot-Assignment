package com.stackroute.muzixapp.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixapp.Domain.Track;
import com.stackroute.muzixapp.Exception.TrackAlreadyExistsException;
import com.stackroute.muzixapp.Service.TrackService;
import com.stackroute.muzixapp.Service.TrackServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jdbc.core.DataAccessStrategy;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest

public class TrackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Track track;
    @MockBean
    private TrackService trackService;
    @InjectMocks
    private TrackController trackController;
    private List<Track> list=null;
    @Before
    public void setUp(){
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(this.trackController).build();
    track = new Track();
    track.setId(1);
    track.setName("Baby");
    track.setArtist("Jst Beiber");
    list=new ArrayList<>();
    list.add(track);
    }
    @Test
    public void saveUser() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }


    @Test
    public void saveUserFailure() throws Exception {
        when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
    //Test for getAllTracks

    @Test
    public void getAllTrack() throws Exception {
        when(trackService.getAllTracks()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    //Test for DeleteAllTracks
  @Test
    public void deleteAllTrack() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/track/delete"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());



    }
    //Test for UpdateById
    @Test
    public void UpdateById() throws Exception {
        Track track = new Track(1,"Hello", "Arya Stark");
        when(trackService.trackByName(track.getName())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/update/{id}", track.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(track)))
                .andExpect(status().isCreated());
    }




    private String asJsonString(final Object object) {
        try
        {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }


}