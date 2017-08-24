package org.parisjug.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import org.parisjug.model.Event;

import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.parisjug.ConfigUtils.YAML_EVENTS_PATH;

@Slf4j
public class YamlWriter {

    private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public void updateEventbriteIdFromEvent(String name, String id) {
        log.debug("update yaml for eventbriteId {}", name);
        try {
            URL resource = YamlWriter.class.getClassLoader().getResource(YAML_EVENTS_PATH + "/" + name + ".yaml");
            if (resource == null) {
                log.warn("unable to read {}", name);
                return;
            }
            Path path = Paths.get(resource.toURI());
            Event event = mapper.readValue(path.toFile(), Event.class);

            event.setEventbrite(id);

            try (Writer out = new FileWriter(path.toFile())) {
                mapper.writeValue(out, event);
            }

        } catch (Exception e) {
            log.error("unable to read {}", name, e);
            return;
        }
    }

}
