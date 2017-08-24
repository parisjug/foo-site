package org.parisjug.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.parisjug.model.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.parisjug.ConfigUtils.*;

@Slf4j
public class Generator {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    final private Configuration cfg;
    final private YamlReader yamlReader;

    public Generator() {
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setClassForTemplateLoading(Generator.class, "/");
        cfg.setDefaultEncoding("UTF-8");

        yamlReader = new YamlReader();
    }

    void generateSpeakerMd(Path dest, String name) {
        log.debug("generating md file {} from {}", dest, name);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("speaker", yamlReader.readSpeaker(name).get());

            generateMd(dest, root, SPEAKER_TEMPLATE);
        } catch (Exception e) {
            log.error("unable to generate md file {} for {}", dest, name, e);
        }
    }

    public void generateSpeakerMd(String name) {
        Path dest = Paths.get(MARKDOWN_DEST_PATH + "/" + MD_SPEAKERS_PATH + "/" + name + ".md");
        generateSpeakerMd(dest, name);
    }

    public void generateSpeakersMd(Path dest, List<Speaker> speakers) {
        log.debug("generating md file {} for speakers", dest);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("speakers", speakers);

            generateMd(dest, root, SPEAKERS_TEMPLATE);
        } catch (Exception e) {
            log.error("unable to generate md file for speakers", dest, e);
        }
    }

    public void generateEventMd(String name) {
        Event event = yamlReader.readEvent(name).get();
        LocalDate date = LocalDate.parse(event.getDate(), formatter);

        Paths.get(MARKDOWN_DEST_PATH + "/" + MD_EVENTS_PATH + "/" + date.getYear()).toFile().mkdirs();

        Path dest = Paths.get(MARKDOWN_DEST_PATH + "/" + MD_EVENTS_PATH + "/" + date.getYear() + "/" + name + ".md");

        generateEventMd(dest, event);
    }

    void generateEventMd(Path dest, Event event) {
        log.debug("generating md file {} from {}", dest, event.getTitle());

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("event", event);

            generateMd(dest, root, EVENT_TEMPLATE);
        } catch (Exception e) {
            log.error("unable to generate md file {} for {}", dest, event.getTitle(), e);
        }
    }

    public void generateEventsMd(Path dest, List<Event> events) {
        log.debug("generating md file {} for events", dest);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("events", events);

            generateMd(dest, root, EVENTS_TEMPLATE);
        } catch (Exception e) {
            log.error("unable to generate md file for events", dest, e);
        }
    }

    public void generateTalkMd(String name) {
        Talk talk = yamlReader.readTalk(name).get();
        LocalDate date = LocalDate.parse(talk.getDate(), formatter);

        Paths.get(MARKDOWN_DEST_PATH + "/" + MD_TALKS_PATH + "/" +  date.getYear()).toFile().mkdirs();

        Path dest = Paths.get(MARKDOWN_DEST_PATH + "/" + MD_TALKS_PATH + "/" +  date.getYear() + "/" + name + ".md");

        generateTalkMd(dest, talk);
    }

    void generateTalkMd(Path dest, Talk talk) {
        log.debug("generating md file {} from {}", dest, talk.getTitle());

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("talk", talk);

            generateMd(dest, root, TALK_TEMPLATE);
        } catch (Exception e) {
            log.error("unable to generate md file {} for {}", dest, talk.getTitle(), e);
        }
    }

    public void generateTalksMd(Path dest, List<Talk> talks) {
        log.debug("generating md file {} for talks", dest);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("talks", talks);

            generateMd(dest, root, TALKS_TEMPLATE);
        } catch (Exception e) {
            log.error("unable to generate md file for events", dest, e);
        }
    }


    public void generateTeamMd(String name) {
        Path dest = Paths.get(MARKDOWN_DEST_PATH + "/" + MD_TEAM_PATH + "/" + name + ".md");
        generateTeamMd(dest, name);
    }

    void generateTeamMd(Path dest, String name) {
        log.debug("generating md file {} from {}", dest, name);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("team", yamlReader.readTeamMember(name).get());

            generateMd(dest, root, TEAM_TEMPLATE);
        } catch (Exception e) {
            log.error("unable to generate md file {} for {}", dest, name, e);
        }
    }

    public void generateTeamsMd(Path dest, List<TeamMember> talks) {
        log.debug("generating md file {} for teams", dest);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("teams", talks);

            generateMd(dest, root, TEAMS_TEMPLATE);
        } catch (Exception e) {
            log.error("unable to generate md file for teams", dest, e);
        }
    }


    public void generateSponsorMd(String name) {
        Path dest = Paths.get(MARKDOWN_DEST_PATH + "/" + MD_SPONSORS_PATH + "/" + name + ".md");
        generateSponsorMd(dest, name);
    }

    void generateSponsorMd(Path dest, String name) {
        log.debug("generating md file {} from {}", dest, name);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("sponsor", yamlReader.readSponsor(name).get());

            generateMd(dest, root, SPONSOR_TEMPLATE);
        } catch (Exception e) {
            log.error("unable to generate md file {} for {}", dest, name, e);
        }
    }

    public void generateSponsorsMd(Path dest, List<Sponsor> sponsors) {
        log.debug("generating md file {} for teams", dest);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("sponsors", sponsors);

            generateMd(dest, root, SPONSORS_TEMPLATE);
        } catch (Exception e) {
            log.error("unable to generate md file for sponsors", dest, e);
        }
    }

    public void generatePreviousEventsMd(Path dest, List<Event> events) {
        log.debug("generating md file {} for events", dest);

        try {
            Map<String, Object> root = new HashMap<>();
            List<Event> collect = events.stream().filter(e -> LocalDate.parse(e.getDate(), formatter).isBefore(LocalDate.now())).collect(toList());
            root.put("events", collect);

            generateMd(dest, root, PREVIOUS_EVENTS_TEMPLATE);
        } catch (Exception e) {
            log.error("unable to generate md file for events", dest, e);
        }
    }

    public void generateNextEventsMd(Path dest, List<Event> events) {
        log.debug("generating md file {} for events", dest);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("events", events.stream().filter(e -> LocalDate.parse(e.getDate(), formatter).isAfter(LocalDate.now()) || LocalDate.parse(e.getDate(), formatter).isEqual(LocalDate.now())).collect(toList()));

            generateMd(dest, root, NEXT_EVENTS_TEMPLATE);
        } catch (Exception e) {
            log.error("unable to generate md file for events", dest, e);
        }
    }


    private void generateMd(Path dest, Map<String, Object> map, String template) throws IOException, URISyntaxException, TemplateException {
        Writer out = new OutputStreamWriter(new FileOutputStream(dest.toFile()));

        Template temp = cfg.getTemplate(TEMPLATE_PATH + "/" + template);
        temp.process(map, out);
    }
}
