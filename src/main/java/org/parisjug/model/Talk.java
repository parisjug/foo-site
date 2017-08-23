package org.parisjug.model;


import lombok.Data;

import java.util.List;

@Data
public class Talk implements Comparable<Talk> {
    private String title;
    private String date;
    private String description;
    private String startTime;
    private String endTime;
    private List<String> speakers;
    private List<Speaker> speakersObject;
    private String internalUrl;

    @Override
    public int compareTo(Talk o) {
        return title.compareTo(o.title);
    }
}
