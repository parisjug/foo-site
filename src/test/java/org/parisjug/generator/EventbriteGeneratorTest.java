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
package org.parisjug.generator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.parisjug.model.Event;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class EventbriteGeneratorTest {

    private EventbriteGenerator eventbriteGenerator;

    private YamlReader reader = new YamlReader();

    @Before
    public void setup() throws IOException {
        eventbriteGenerator = new EventbriteGenerator();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void eventbrite_format_should_be_ok() {
        Optional<Event> event = reader.readEvent("20170613-docker");

        String request = eventbriteGenerator.generateRequest(event.get(), "blabla");

        assertThat(request).isEqualTo("{\"event\":{\"name\":{\"html\":\"Docker et java 9 (2020/06/13)\"},\"description\":{\"html\":\"blabla\"},\"start\":{\"timezone\":\"Europe/Paris\",\"utc\":\"2020-06-13T17:30:00Z\"},\"end\":{\"timezone\":\"Europe/Paris\",\"utc\":\"2020-06-13T20:00:00Z\"},\"capacity\":210,\"currency\":\"EUR\",\"listed\":true,\"shareable\":true,\"invite_only\":false,\"online_event\":false,\"show_remaining\":true,\"hide_start_date\":false,\"hide_end_date\":false,\"organizer_id\":\"880413051\",\"venue_id\":\"12251888\",\"category_id\":\"102\",\"format_id\":\"1\"}}");
    }

    @Test
    public void eventbrite_ticketClass_format_should_be_ok() {
        Optional<Event> event = reader.readEvent("20170613-docker");

        String request = eventbriteGenerator.generateTicketClassRequest(event.get());

        assertThat(request).isEqualTo("{\"ticket_class\":{\"name\":\"Docker et java 9\",\"donation\":false,\"free\":true,\"minimum_quantity\":1,\"quantity_total\":210,\"sales_start\":\"2020-06-06T22:00:00Z\",\"sales_end\":\"2020-06-13T20:00:00Z\",\"hidden\":false,\"include_fee\":false,\"hide_description\":true,\"auto_hide\":false}}");
    }

}
