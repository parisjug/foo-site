package org.parisjug.eventbrite.ticketclasses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TicketClassRequest {
    private TicketClass ticket_class;

}

/*
request
{
  "ticket_class":
    {
      "name": "test",
      "donation": false,
      "free": true,
      "minimum_quantity": 1,
      "quantity_total": 210,
      "sales_start": "2017-08-24T03:35:00Z",
      "sales_end": "2020-06-13T16:30:00Z",
      "hidden": false,
      "include_fee": false,
      "hide_description": true,
      "auto_hide": false
    }

}




 */
