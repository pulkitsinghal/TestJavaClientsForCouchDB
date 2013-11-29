package com.fermyon.router.pojos.shoppinpal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties({"id", "revision"})
@JsonIgnoreProperties(ignoreUnknown=true)
public class FoursquareVenue {

    @JsonProperty("_id")
    private String id;
    public void setId(String s) {
        id = s;
    }
    public String getId() {
        return id;
    }

    @JsonProperty("_rev")
    private String revision;
    public String getRevision() {
        return revision;
    }

    public String type;
    public String venueId;
    public String venueName;
    public String posUrl;
}