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


    @Override
    public int compareTo(Event o) {
        return date.compareTo(o.date);
    }
}
