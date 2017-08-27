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
