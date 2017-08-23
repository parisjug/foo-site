package org.parisjug.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import org.parisjug.model.*;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class YamlReader {
    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public Optional<Speaker> readSpeaker(String name) {
        return readSpeaker(name, true);
    }

    public Optional<Speaker> readSpeaker(String name, boolean recursive) {

        try {
            URL resource = YamlReader.class.getClassLoader().getResource("speakers/" + name + ".yaml");
            if (resource == null) {
                log.warn("unable to read {}", name);
                return Optional.empty();
            }
            Path path = Paths.get(resource.toURI());

            Speaker speaker = mapper.readValue(path.toFile(), Speaker.class);
            if (recursive) {
                List<?> talks = speaker.getTalks().stream()
                        .map(t -> readTalk(t))
                        .filter(t -> t.isPresent())
                        .map(t -> t.get())
                        .collect(Collectors.toList());

                speaker.setTalksObject((List<Talk>) talks);
            }
            speaker.setInternalUrl("../speakers/" + name + ".html");

            return Optional.ofNullable(speaker);
        } catch (Exception e) {
            log.error("unable to read {}", name, e);
            return Optional.empty();
        }
    }

    public Optional<Event> readEvent(String name) {
        return readEvent(name, true);
    }

    public Optional<Event> readEvent(String name, boolean recursive) {

        try {
            URL resource = YamlReader.class.getClassLoader().getResource("events/" + name + ".yaml");
            if (resource == null) {
                log.warn("unable to read {}", name);
                return Optional.empty();
            }
            Path path = Paths.get(resource.toURI());
            Event event = mapper.readValue(path.toFile(), Event.class);

            if (recursive) {
                List<?> talks = event.getTalks().stream()
                        .map(t -> readTalk(t))
                        .filter(t -> t.isPresent())
                        .map(t -> t.get())
                        .collect(Collectors.toList());

                event.setTalksObject((List<Talk>) talks);
            }
            event.setInternalUrl("../events/" + name + ".html");

            return Optional.ofNullable(event);
        } catch (Exception e) {
            log.error("unable to read {}", name, e);
            return Optional.empty();
        }
    }

    public Optional<Talk> readTalk(String name) {
        return readTalk(name, true);
    }

    public Optional<Talk> readTalk(String name, boolean recursive) {

        try {
            URL resource = YamlReader.class.getClassLoader().getResource("talks/" + name + ".yaml");
            if (resource == null) {
                log.warn("unable to read {}", name);
                return Optional.empty();
            }

            Path path = Paths.get(resource.toURI());
            Talk talk = mapper.readValue(path.toFile(), Talk.class);

            if (recursive) {
                List<?> talks = talk.getSpeakers().stream()
                        .map(t -> readSpeaker(t, false))
                        .filter(t -> t.isPresent())
                        .map(t -> t.get())
                        .collect(Collectors.toList());

                talk.setSpeakersObject((List<Speaker>) talks);
            }
            talk.setInternalUrl("../talks/" + name + ".html");

            return Optional.ofNullable(talk);
        } catch (Exception e) {
            log.error("unable to read {}", name, e);
            return Optional.empty();
        }
    }

    public Optional<TeamMember> readTeamMember(String name) {
        try {
            URL resource = YamlReader.class.getClassLoader().getResource("teams/" + name + ".yaml");
            if (resource == null) {
                log.warn("unable to read {}", name);
                return Optional.empty();
            }
            Path path = Paths.get(resource.toURI());
            TeamMember team = mapper.readValue(path.toFile(), TeamMember.class);

            team.setInternalUrl("../teams/" + name + ".html");

            return Optional.ofNullable(team);
        } catch (Exception e) {
            log.error("unable to read {}", name, e);
            return Optional.empty();
        }
    }

    public Optional<Sponsor> readSponsor(String name) {
        try {
            URL resource = YamlReader.class.getClassLoader().getResource("sponsors/" + name + ".yaml");
            if (resource == null) {
                log.warn("unable to read {}", name);
                return Optional.empty();
            }
            Path path = Paths.get(resource.toURI());
            Sponsor sponsor = mapper.readValue(path.toFile(), Sponsor.class);

            sponsor.setInternalUrl("../sponsors/" + name + ".html");

            return Optional.ofNullable(sponsor);
        } catch (Exception e) {
            log.error("unable to read {}", name, e);
            return Optional.empty();
        }
    }

}
