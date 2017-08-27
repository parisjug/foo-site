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
package org.parisjug.eventbrite.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {
    private Name name;
    private Description description;
    private Start start;
    private End end;

    private int capacity;
    @Builder.Default
    private String currency = "EUR";
    @Builder.Default
    private boolean listed = true;
    @Builder.Default
    private boolean shareable = true;
    @Builder.Default
    private boolean invite_only = false;
    @Builder.Default
    private boolean online_event = false;
    @Builder.Default
    private boolean show_remaining = true;
    @Builder.Default
    private boolean hide_start_date = false;
    @Builder.Default
    private boolean hide_end_date = false;
    @Builder.Default
    private String organizer_id = "880413051";
    @Builder.Default
    private String venue_id = "12251888";
    @Builder.Default
    private String category_id = "102";
    @Builder.Default
    private String subcategory_id = null;
    @Builder.Default
    private String format_id = "1";
//
//    private String id;
//    private String url;
//    private String created;
//    private String changed;
//    private boolean capacity_is_custom;
//    private String status;
//    private int tx_time_limit;
//    private String locale;
//    private int is_locked;
//    private String privacy_setting;
//    private boolean is_series;
//    private boolean is_series_parent;
//    private boolean is_reserved_seating;
//    private String source;
//    private boolean is_free;
//    private String version;
//    private String logo_id;
//    private String resource_uri;
//    private String logo;
}
