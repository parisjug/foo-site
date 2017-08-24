package org.parisjug.eventbrite.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Start {
    private String timezone;
    private String utc;
    private String local;
}
