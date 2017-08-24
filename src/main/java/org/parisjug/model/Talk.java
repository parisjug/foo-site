package org.parisjug.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Talk implements Comparable<Talk> {
    private String title;
    private String date;
    private String description;
    private String startTime;
    private String endTime;
    private List<String> speakers = new ArrayList<>();

    private List<Speaker> speakersObject = new ArrayList<>();
    private String internalUrl;
    private String externalUrl;

    @Override
    public int compareTo(Talk o) {
        return title.compareTo(o.title);
    }
}
