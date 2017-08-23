package org.parisjug.model;

import lombok.Data;

import java.util.List;

@Data
public class Event implements Comparable<Event> {

    private String title;
    private String date;
    private String description;
    private String flickUrl;
    private List<String> talks;
    private List<Talk> talksObject;
    private String internalUrl;
    private String eventbrite;
    private String startTime; //debut de l'event
    private int duration = 150; //min
    private String sellStartDate; //ouverture de l'event sur eventbrite ou sinon date - tant de jour
    private String sellEndDate; //startTime + Duration
    private int capacity = 210;


    @Override
    public int compareTo(Event o) {
        return date.compareTo(o.date);
    }
}
