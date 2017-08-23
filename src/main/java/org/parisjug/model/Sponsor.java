package org.parisjug.model;

import lombok.Data;

import java.util.Comparator;

@Data
public class Sponsor implements Comparable<Sponsor> {
    private String name;
    private String description;
    private String url;
    private String logo;
    private SponsorType type;
    private String internalUrl;

    @Override
    public int compareTo(Sponsor o) {
        return Comparator.comparing((Sponsor p) -> p.type)
                .thenComparing(p -> p.name)
                .compare(this, o);
    }
}
