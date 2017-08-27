/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.parisjug.eventbrite.ticketclasses;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TicketClassesResponse {
    private String resource_uri;
    private String name;
    private String description;
    private boolean donation;
    private boolean free;
    private int minimum_quantity;
    private int maximum_quantity;
    private int maximum_quantity_per_order;
    private int maximum_quantity_per_order_without_pending;
    private String on_sale_status;
    private int quantity_total;
    private int quantity_sold;
    private String sales_start;
    private String sales_end;
    private boolean hidden = false;
    private boolean include_fee = false;
    private boolean split_fee;
    private boolean hide_description;
    private boolean auto_hide = false;
    private List<String> variants;
    private boolean has_pdf_ticket = true;
    private List<String> sales_channels;
    private String event_id;
    private String id;

}

/*

response
{
  "resource_uri": "https://www.eventbriteapi.com/v3/events/37306953101/ticket_classes/71499316/",
  "name": "test",
  "description": null,
  "donation": false,
  "free": true,
  "minimum_quantity": 1,
  "maximum_quantity": null,
  "maximum_quantity_per_order": 10,
  "maximum_quantity_per_order_without_pending": null,
  "on_sale_status": "AVAILABLE",
  "quantity_total": 210,
  "quantity_sold": 0,
  "sales_start": "2017-08-24T03:35:00Z",
  "sales_end": "2020-06-13T16:30:00Z",
  "hidden": false,
  "include_fee": false,
  "split_fee": false,
  "hide_description": true,
  "auto_hide": false,
  "variants": [],
  "has_pdf_ticket": true,
  "sales_channels": [
    "online",
    "atd"
  ],
  "event_id": "37306953101",
  "id": "71499316"
}

 */
