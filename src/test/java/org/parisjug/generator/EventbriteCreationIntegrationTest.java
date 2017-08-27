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

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.parisjug.MainEventbrite;
import org.parisjug.model.Event;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class EventbriteCreationIntegrationTest {

    private EventbriteGenerator eventbriteGenerator;
    private HtmlGenerator htmlGenerator;
    private MdGenerator mdGenerator;
    private YamlReader reader = new YamlReader();
    private YamlWriter writer = new YamlWriter();

    @Before
    public void setup() throws IOException {
        eventbriteGenerator = new EventbriteGenerator();
        htmlGenerator = new HtmlGenerator();
        mdGenerator = new MdGenerator();
    }

    @After
    public void tearDown() {
    }


    @Test
    public void eventbrite_format_should_be_ok() throws IOException {

        MainEventbrite mainEventbrite = new MainEventbrite();

        String name = "20170613-docker";
        Optional<Event> event = reader.readEvent(name);

        String htmlContent = htmlGenerator.generateHtmlForEvent(mdGenerator, name);

        Optional<String> id = mainEventbrite.createAndPublishEvent(eventbriteGenerator, event.get(), htmlContent);

        assertThat(id).isPresent();
    }

    @Test
    @Ignore
    public void eventbriteId_should_be_updated() throws IOException {

        MainEventbrite mainEventbrite = new MainEventbrite();

        String name = "20170613-docker";

        Optional<String> id = Optional.of("test");

        assertThat(id).isPresent();

        if (id.isPresent()) {
            mainEventbrite.updateYml(writer, name, id.get());
        }

        Optional<Event> event = reader.readEvent(name);
        assertThat(event).isPresent();
        assertThat(event.get().getEventbrite()).isEqualTo(id.get());
    }

}
