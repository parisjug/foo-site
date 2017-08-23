package org.parisjug.model;

import lombok.Data;

import java.util.List;

@Data
public class Speaker implements Comparable<Speaker> {
    private String lastName;
    private String firstName;
    private String description;
    private String photo;
    private String tweet;
    private String url;
    private List<String> talks;
    private List<Talk> talksObject;
    private String internalUrl;



    @Override
    public int compareTo(Speaker o) {
        return lastName.compareTo(o.lastName);
    }
}
