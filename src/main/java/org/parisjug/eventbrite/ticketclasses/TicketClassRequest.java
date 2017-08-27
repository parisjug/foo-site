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
