package org.parisjug.generator;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.parisjug.MainEventbrite;
import org.parisjug.eventbrite.event.EventbriteResponse;
import org.parisjug.model.Event;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class EventbriteCreationIntegrationTest {

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
    public void eventbrite_format_should_be_ok() throws IOException {

        MainEventbrite mainEventbrite = new MainEventbrite();
        Optional<Event> event = reader.readEvent("20170613-docker");

        assertThat(mainEventbrite.createAndPublishEvent(eventbriteGenerator, event.get())).isTrue();
    }

}
