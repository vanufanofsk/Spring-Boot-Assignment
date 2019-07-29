package com.stackroute.muzixapp;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixapp.Domain.Track;

import java.io.File;
import java.io.IOException;

public class JsonToJavaObject<value> {
    public JsonToJavaObject()
    {}
    public void jsonObject()
    {
    ObjectMapper mapper=new ObjectMapper();
    Track value =new Track();
        try {
            value=mapper.readValue(new File("/home/vanmathi/Downloads/muzixapp/src/main/java/com/stackroute/muzixapp/jsonfile.json"),Track.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
