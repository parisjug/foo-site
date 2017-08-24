package org.parisjug.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Event implements Comparable<Event> {

    private String title;
    private String date;
    private String description;
    private String flickUrl;
    private List<String> talks = new ArrayList<>();
    private String eventbrite;
    private String startTime = "19:30"; //debut de l'event
    private int duration = 150; //min
    private int numberOfDaysSellAreOpenBefore = 6; //ouverture de l'event sur eventbrite ou sinon date - tant de jour
    private int capacity = 210;

    private List<Talk> talksObject = new ArrayList<>();
    private String internalUrl;
    private String externalUrl;


    @Override
    public int compareTo(Event o) {
        return date.compareTo(o.date);
    }
}
