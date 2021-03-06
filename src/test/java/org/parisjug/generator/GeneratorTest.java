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
import org.parisjug.model.Speaker;
import org.parisjug.model.Talk;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneratorTest {

    private YamlReader reader = new YamlReader();
    private Path dest;
    private MdGenerator generator;

    @Before
    public void setup() throws IOException {
        generator = new MdGenerator();

        dest = Paths.get(String.valueOf(File.createTempFile("test", ".md")));
//      dest = Paths.get("/tmp/toto.md");
    }

    @After
    public void tearDown() {
        dest.toFile().deleteOnExit();
    }

    @Test
    public void speaker_generator_should_success() throws IOException, URISyntaxException {
        generator.generateSpeakerMd(dest, "forax-remi");

        assertThat(dest).exists();
    }

    @Test
    public void speakers_generator_should_success() throws IOException, URISyntaxException {
        Optional<Speaker> speaker1 = reader.readSpeaker("sabourdin-charles");
        Optional<Speaker> speaker2 = reader.readSpeaker("forax-remi");

        generator.generateSpeakersMd(dest, Arrays.asList(speaker1.get(), speaker2.get()).stream().sorted().collect(Collectors.toList()));


        assertThat(dest).exists();
    }


    @Test
    public void event_generator_should_success() throws IOException, URISyntaxException {
        Event event = reader.readEvent("20170214-jigsaw").get();

        generator.generateEventMd(dest, event);

        assertThat(dest).exists();
    }

    @Test
    public void events_generator_should_success() throws IOException, URISyntaxException {
        Optional<Event> event1 = reader.readEvent("20170214-jigsaw");
        Optional<Event> event2 = reader.readEvent("20170613-docker");

        generator.generateEventsMd(dest, Arrays.asList(event1.get(), event2.get()).stream().sorted().collect(Collectors.toList()));

        assertThat(dest).exists();
    }

    @Test
    public void talks_generator_should_success() throws IOException, URISyntaxException {
        Optional<Talk> talk1 = reader.readTalk("20170214-jigsaw");
        Optional<Talk> talk2 = reader.readTalk("20170613-docker");
        Optional<Talk> talk3 = reader.readTalk("20170613-java9");

        generator.generateTalksMd(dest, Arrays.asList(talk1.get(), talk2.get(), talk3.get()).stream().sorted().collect(Collectors.toList()));

        assertThat(dest).exists();
    }
}
