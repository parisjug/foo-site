package org.parisjug.eventbrite.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class End {
    private String timezone;
    private String utc;
    private String local;

}
