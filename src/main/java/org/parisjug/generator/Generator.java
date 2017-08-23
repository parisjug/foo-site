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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Generator {

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

            generateMd(dest, root, "speaker.vm");
        } catch (Exception e) {
            log.error("unable to generate md file {} for {}", dest, name, e);
        }
    }

    public void generateSpeakerMd(String name) {
        Path dest = Paths.get("src/site/markdown/speakers/" + name + ".md");
        generateSpeakerMd(dest, name);
    }

    public void generateSpeakersMd(Path dest, List<Speaker> speakers) {
        log.debug("generating md file {} for speakers", dest);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("speakers", speakers);

            generateMd(dest, root, "speakers.vm");
        } catch (Exception e) {
            log.error("unable to generate md file for speakers", dest, e);
        }
    }

    public void generateEventMd(String name) {
        Path dest = Paths.get("src/site/markdown/events/" + name + ".md");
        generateEventMd(dest, name);
    }

    void generateEventMd(Path dest, String name) {
        log.debug("generating md file {} from {}", dest, name);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("event", yamlReader.readEvent(name).get());

            generateMd(dest, root, "event.vm");
        } catch (Exception e) {
            log.error("unable to generate md file {} for {}", dest, name, e);
        }
    }

    public void generateEventsMd(Path dest, List<Event> events) {
        log.debug("generating md file {} for events", dest);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("events", events);

            generateMd(dest, root, "events.vm");
        } catch (Exception e) {
            log.error("unable to generate md file for events", dest, e);
        }
    }

    public void generateTalkMd(String name) {
        Path dest = Paths.get("src/site/markdown/talks/" + name + ".md");
        generateTalkMd(dest, name);
    }

    void generateTalkMd(Path dest, String name) {
        log.debug("generating md file {} from {}", dest, name);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("talk", yamlReader.readTalk(name).get());

            generateMd(dest, root, "talk.vm");
        } catch (Exception e) {
            log.error("unable to generate md file {} for {}", dest, name, e);
        }
    }

    public void generateTalksMd(Path dest, List<Talk> talks) {
        log.debug("generating md file {} for talks", dest);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("talks", talks);

            generateMd(dest, root, "talks.vm");
        } catch (Exception e) {
            log.error("unable to generate md file for events", dest, e);
        }
    }


    public void generateTeamMd(String name) {
        Path dest = Paths.get("src/site/markdown/teams/" + name + ".md");
        generateTeamMd(dest, name);
    }

    void generateTeamMd(Path dest, String name) {
        log.debug("generating md file {} from {}", dest, name);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("team", yamlReader.readTeamMember(name).get());

            generateMd(dest, root, "team.vm");
        } catch (Exception e) {
            log.error("unable to generate md file {} for {}", dest, name, e);
        }
    }

    public void generateTeamsMd(Path dest, List<TeamMember> talks) {
        log.debug("generating md file {} for teams", dest);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("teams", talks);

            generateMd(dest, root, "teams.vm");
        } catch (Exception e) {
            log.error("unable to generate md file for teams", dest, e);
        }
    }


    public void generateSponsorMd(String name) {
        Path dest = Paths.get("src/site/markdown/sponsors/" + name + ".md");
        generateSponsorMd(dest, name);
    }

    void generateSponsorMd(Path dest, String name) {
        log.debug("generating md file {} from {}", dest, name);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("sponsor", yamlReader.readSponsor(name).get());

            generateMd(dest, root, "sponsor.vm");
        } catch (Exception e) {
            log.error("unable to generate md file {} for {}", dest, name, e);
        }
    }

    public void generateSponsorsMd(Path dest, List<Sponsor> sponsors) {
        log.debug("generating md file {} for teams", dest);

        try {
            Map<String, Object> root = new HashMap<>();
            root.put("sponsors", sponsors);

            generateMd(dest, root, "sponsors.vm");
        } catch (Exception e) {
            log.error("unable to generate md file for sponsors", dest, e);
        }
    }

    private void generateMd(Path dest, Map<String, Object> map, String template) throws IOException, URISyntaxException, TemplateException {
        Writer out = new OutputStreamWriter(new FileOutputStream(dest.toFile()));

        Template temp = cfg.getTemplate("templates/" + template);
        temp.process(map, out);
    }
}
