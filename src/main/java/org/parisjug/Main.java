package org.parisjug;

import org.parisjug.generator.Generator;
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
        Generator generator = new Generator();
        YamlReader reader = new YamlReader();

        generateEvents(generator, reader);
        generateSpeakers(generator, reader);
        generateTalks(generator, reader);
        generateTeam(generator, reader);
        generateSponsors(generator, reader);
    }

    private static void generateEvents(Generator generator, YamlReader reader) throws URISyntaxException, IOException {
        Path eventsPath = Paths.get(Main.class.getClassLoader().getResource("events").toURI());

        Paths.get("src/site/markdown/events").toFile().mkdirs();

        Path eventsOutput = Paths.get("src/site/markdown/events/events.md");
        Path oldEventsOutput = Paths.get("src/site/markdown/events/old_events.md");
        Path nextEventsOutput = Paths.get("src/site/markdown/events/next_events.md");

        List<Event> events = Files.list(eventsPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .map(t -> reader.readEvent(t))
                .filter(t -> t.isPresent())
                .map(t -> t.get())
                .sorted()
                .collect(toList());

        generator.generateEventsMd(eventsOutput, events);
        generator.generateOldEventsMd(oldEventsOutput, events);
        generator.generateNextEventsMd(nextEventsOutput, events);

        Files.list(eventsPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .forEach(e -> generator.generateEventMd(e));
    }

    private static void generateSpeakers(Generator generator, YamlReader reader) throws URISyntaxException, IOException {
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

    private static void generateTalks(Generator generator, YamlReader reader) throws URISyntaxException, IOException {
        Path talksPath = Paths.get(Main.class.getClassLoader().getResource("talks").toURI());

        Paths.get("src/site/markdown/talks").toFile().mkdirs();

        Path talksOutput = Paths.get("src/site/markdown/talks/talks.md");

        List<Talk> talks = Files.list(talksPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .map(t -> reader.readTalk(t))
                .filter(t -> t.isPresent())
                .map(t -> t.get())
                .sorted()
                .collect(toList());

        generator.generateTalksMd(talksOutput, talks);

        Files.list(talksPath)
                .map(f -> f.getFileName().toString().replaceFirst(".yaml", ""))
                .forEach(e -> generator.generateTalkMd(e));
    }

    private static void generateTeam(Generator generator, YamlReader reader) throws URISyntaxException, IOException {
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

    private static void generateSponsors(Generator generator, YamlReader reader) throws URISyntaxException, IOException {
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
