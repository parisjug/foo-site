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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.parisjug.model.*;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.parisjug.ConfigUtils.*;

@Slf4j
public class YamlReader {

    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public Optional<Speaker> readSpeaker(String name) {
        return readSpeaker(name, true);
    }

    public Optional<Speaker> readSpeaker(String name, boolean recursive) {
        String ressourcePath = YAML_SPEAKERS_PATH + "/" + name + ".yaml";
        try {

            URL resource = YamlReader.class.getClassLoader().getResource(ressourcePath);
            if (resource == null) {
                log.warn("unable to read {}", ressourcePath);
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
            speaker.setInternalUrl((StringUtils.isEmpty(APPLICATION_CONTEXT) ? "" : APPLICATION_CONTEXT) + "/" + MD_SPEAKERS_PATH + "/" + name + ".html");
            speaker.setExternalUrl(URL_EXTERNAL + (StringUtils.isEmpty(APPLICATION_CONTEXT) ? "" : APPLICATION_CONTEXT) + "/" + MD_SPEAKERS_PATH + "/" + name + ".html");

            return Optional.ofNullable(speaker);
        } catch (IOException | URISyntaxException e) {
            log.error("unable to read {}", ressourcePath, e);
            return Optional.empty();
        }
    }

    public Optional<Event> readEvent(String name) {
        return readEvent(name, true);
    }

    public Optional<Event> readEvent(String name, boolean recursive) {
        String ressourcePath = YAML_EVENTS_PATH + "/" + name + ".yaml";
        try {
            URL resource = YamlReader.class.getClassLoader().getResource(ressourcePath);
            if (resource == null) {
                log.warn("unable to read {}", ressourcePath);
                return Optional.empty();
            }
            Path path = Paths.get(resource.toURI());
            Event event = mapper.readValue(path.toFile(), Event.class);

            if (recursive) {
                List<?> talks = event.getTalks().stream()
                        .map(t -> readTalk(t))
                        .filter(t -> t.isPresent())
                        .map(t -> t.get())
                        .sorted(Comparator.comparing((Talk p) -> p.getDate()).thenComparing(p -> p.getStartTime()))
                        .collect(Collectors.toList());

                event.setTalksObject((List<Talk>) talks);
            }
            LocalDate date = LocalDate.parse(event.getDate(), formatter);

            event.setInternalUrl((StringUtils.isEmpty(APPLICATION_CONTEXT) ? "" : APPLICATION_CONTEXT) + "/" + MD_EVENTS_PATH + "/" + date.getYear() + "/" + name + ".html");
            event.setExternalUrl(URL_EXTERNAL + (StringUtils.isEmpty(APPLICATION_CONTEXT) ? "" : APPLICATION_CONTEXT) + "/" + MD_EVENTS_PATH + "/" + date.getYear() + "/" + name + ".html");

            return Optional.ofNullable(event);
        } catch (IOException | URISyntaxException e) {
            log.error("unable to read {}", ressourcePath, e);
            return Optional.empty();
        }
    }

    public Optional<Talk> readTalk(String name) {
        return readTalk(name, true);
    }

    public Optional<Talk> readTalk(String name, boolean recursive) {
        String ressourcePath = YAML_TALKS_PATH + "/" + name + ".yaml";

        try {
            URL resource = YamlReader.class.getClassLoader().getResource(ressourcePath);
            if (resource == null) {
                log.warn("unable to read {}", ressourcePath);
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
            LocalDate date = LocalDate.parse(talk.getDate(), formatter);

            talk.setInternalUrl((StringUtils.isEmpty(APPLICATION_CONTEXT) ? "" : APPLICATION_CONTEXT) + "/" + MD_TALKS_PATH + "/" + date.getYear() + "/" + name + ".html");
            talk.setExternalUrl(URL_EXTERNAL + (StringUtils.isEmpty(APPLICATION_CONTEXT) ? "" : APPLICATION_CONTEXT) + "/" + MD_TALKS_PATH + "/" + date.getYear() + "/" + name + ".html");

            return Optional.ofNullable(talk);
        } catch (IOException | URISyntaxException e) {
            log.error("unable to read {}", ressourcePath, e);
            return Optional.empty();
        }
    }

    public Optional<TeamMember> readTeamMember(String name) {
        String ressourcePath = YAML_TEAM_PATH + "/" + name + ".yaml";

        try {
            URL resource = YamlReader.class.getClassLoader().getResource(ressourcePath);
            if (resource == null) {
                log.warn("unable to read {}", ressourcePath);
                return Optional.empty();
            }
            Path path = Paths.get(resource.toURI());
            TeamMember team = mapper.readValue(path.toFile(), TeamMember.class);

            team.setInternalUrl((StringUtils.isEmpty(APPLICATION_CONTEXT) ? "" : APPLICATION_CONTEXT) + "/" + MD_TEAM_PATH + "/" + name + ".html");
            team.setExternalUrl(URL_EXTERNAL + (StringUtils.isEmpty(APPLICATION_CONTEXT) ? "" : APPLICATION_CONTEXT) + "/" + MD_TEAM_PATH + "/" + name + ".html");

            return Optional.ofNullable(team);
        } catch (IOException | URISyntaxException e) {
            log.error("unable to read {}", ressourcePath, e);
            return Optional.empty();
        }
    }

    public Optional<Sponsor> readSponsor(String name) {
        String ressourcePath = YAML_SPONSORS_PATH + "/" + name + ".yaml";

        try {
            URL resource = YamlReader.class.getClassLoader().getResource(ressourcePath);
            if (resource == null) {
                log.warn("unable to read {}", ressourcePath);
                return Optional.empty();
            }
            Path path = Paths.get(resource.toURI());
            Sponsor sponsor = mapper.readValue(path.toFile(), Sponsor.class);

            sponsor.setInternalUrl((StringUtils.isEmpty(APPLICATION_CONTEXT) ? "" : APPLICATION_CONTEXT) + "/" + MD_SPONSORS_PATH + "/" + name + ".html");
            sponsor.setExternalUrl(URL_EXTERNAL + (StringUtils.isEmpty(APPLICATION_CONTEXT) ? "" : APPLICATION_CONTEXT) + "/" + MD_SPONSORS_PATH + "/" + name + ".html");

            return Optional.ofNullable(sponsor);
        } catch (IOException | URISyntaxException e) {
            log.error("unable to read {}", ressourcePath, e);
            return Optional.empty();
        }
    }

}
