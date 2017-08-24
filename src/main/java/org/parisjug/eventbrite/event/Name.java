package org.parisjug.eventbrite.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Name {
    private String html;
    private String text;
}
