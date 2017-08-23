package org.parisjug.model;

import lombok.Data;

@Data
public class TeamMember implements Comparable<TeamMember> {
    private String lastName;
    private String firstName;
    private String description;
    private String photo;
    private String tweet;
    private String url;
    private String internalUrl;

    @Override
    public int compareTo(TeamMember o) {
        return lastName.compareTo(o.lastName);
    }
}
