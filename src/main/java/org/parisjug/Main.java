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
package org.parisjug;

import org.parisjug.generator.MdGenerator;
import org.parisjug.generator.YamlReader;
import org.parisjug.model.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String... args) throws URISyntaxException, IOException {
        MdGenerator generator = new MdGenerator();
        YamlReader reader = new YamlReader();

        generateEvents(generator, reader);
        generateSpeakers(generator, reader);
        generateTalks(generator, reader);
        generateTeam(generator, reader);
        generateSponsors(generator, reader);
    }

    private static void generateEvents(MdGenerator generator, YamlReader reader) throws URISyntaxException, IOException {
        Path eventsPath = Paths.get(Main.class.getClassLoader().getResource("events").toURI());

        Paths.get("src/site/markdown/events").toFile().mkdirs();
        Paths.get("src/site/resources").toFile().mkdirs();

        Path eventsOutput = Paths.get("src/site/markdown/events/events.md");
        Path eventsJsonOutput = Paths.get("src/site/resources/timeline3-events.json");

        List<Event> events = Files.list(eventsPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .map(t -> reader.readEvent(t))
                .filter(t -> t.isPresent())
                .map(t -> t.get())
                .sorted()
                .collect(toList());

        generator.generateEventsMd(eventsOutput, events);
        generator.generateEventsJSonMd(eventsJsonOutput, events);

        Files.list(eventsPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .forEach(e -> generator.generateEventMd(e));
    }

    private static void generateSpeakers(MdGenerator generator, YamlReader reader) throws URISyntaxException, IOException {
        Path speakersPath = Paths.get(Main.class.getClassLoader().getResource("speakers").toURI());

        Paths.get("src/site/markdown/speakers").toFile().mkdirs();

        Path speakersOutput = Paths.get("src/site/markdown/speakers/speakers.md");

        List<Speaker> speakers = Files.list(speakersPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .map(t -> reader.readSpeaker(t))
                .filter(t -> t.isPresent())
                .map(t -> t.get())
                .sorted()
                .collect(toList());

        generator.generateSpeakersMd(speakersOutput, speakers);

        Files.list(speakersPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .forEach(e -> generator.generateSpeakerMd(e));
    }

    private static void generateTalks(MdGenerator generator, YamlReader reader) throws URISyntaxException, IOException {
        Path talksPath = Paths.get(Main.class.getClassLoader().getResource("talks").toURI());

        Paths.get("src/site/markdown/talks").toFile().mkdirs();

        Path talksOutput = Paths.get("src/site/markdown/talks/talks.md");
        Path talksJsonOutput = Paths.get("src/site/resources/timeline3-talks.json");


        List<Talk> talks = Files.list(talksPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .map(t -> reader.readTalk(t))
                .filter(t -> t.isPresent())
                .map(t -> t.get())
                .sorted()
                .collect(toList());

        generator.generateTalksMd(talksOutput, talks);
        generator.generateTalksJSonMd(talksJsonOutput, talks);

        Files.list(talksPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .forEach(e -> generator.generateTalkMd(e));
    }

    private static void generateTeam(MdGenerator generator, YamlReader reader) throws URISyntaxException, IOException {
        Path talksPath = Paths.get(Main.class.getClassLoader().getResource("teams").toURI());

        Paths.get("src/site/markdown/teams").toFile().mkdirs();

        Path teamMembersOuput = Paths.get("src/site/markdown/teams/teams.md");

        List<TeamMember> teamMembers = Files.list(talksPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .map(t -> reader.readTeamMember(t))
                .filter(t -> t.isPresent())
                .map(t -> t.get())
                .sorted()
                .collect(toList());

        generator.generateTeamsMd(teamMembersOuput, teamMembers);

        Files.list(talksPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .forEach(e -> generator.generateTeamMd(e));
    }

    private static void generateSponsors(MdGenerator generator, YamlReader reader) throws URISyntaxException, IOException {
        Path talksPath = Paths.get(Main.class.getClassLoader().getResource("sponsors").toURI());

        Paths.get("src/site/markdown/sponsors").toFile().mkdirs();

        Path teamMembersOuput = Paths.get("src/site/markdown/sponsors/sponsors.md");

        List<Sponsor> sponsors = Files.list(talksPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .map(t -> reader.readSponsor(t))
                .filter(t -> t.isPresent())
                .map(t -> t.get())
                .sorted()
                .collect(toList());

        generator.generateSponsorsMd(teamMembersOuput, sponsors);

        Files.list(talksPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .forEach(e -> generator.generateSponsorMd(e));
    }
}
