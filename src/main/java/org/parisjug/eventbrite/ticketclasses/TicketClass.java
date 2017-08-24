package org.parisjug.eventbrite.ticketclasses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketClass {
    private String name;
    @Builder.Default
    private boolean donation = false;
    @Builder.Default
    private boolean free = true;
    @Builder.Default
    private int minimum_quantity = 1;
    private int quantity_total = 210;
    private String sales_start = "2017-08-24T03:35:00Z";
    private String sales_end = "2020-06-13T16:30:00Z";
    @Builder.Default
    private boolean hidden = false;
    @Builder.Default
    private boolean include_fee = false;
    @Builder.Default
    private boolean hide_description = true;
    @Builder.Default
    private boolean auto_hide = false;
}
