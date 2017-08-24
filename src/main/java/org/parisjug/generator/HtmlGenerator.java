package org.parisjug.generator;

import lombok.extern.slf4j.Slf4j;
import org.parisjug.model.Event;
import org.tautua.markdownpapers.Markdown;
import org.tautua.markdownpapers.parser.ParseException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.parisjug.ConfigUtils.MARKDOWN_DEST_PATH;
import static org.parisjug.ConfigUtils.MD_EVENTS_PATH;

@Slf4j
public class HtmlGenerator {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    final private YamlReader yamlReader;

    public HtmlGenerator() {
        yamlReader = new YamlReader();
    }

    public String generateHtmlForEvent(MdGenerator mdGenerator, String name) {
        log.debug("generating html file for {}", name);
        mdGenerator.generateEventMdForExternalStuff(name);
        Event event = yamlReader.readEvent(name).get();
        LocalDate date = LocalDate.parse(event.getDate(), formatter);

        Path dest = Paths.get(MARKDOWN_DEST_PATH + "/" + MD_EVENTS_PATH + "/" + date.getYear() + "/" + name + ".md");

        Writer out = new StringWriter();

        try (Reader in = new FileReader(dest.toString())) {

                Markdown md = new Markdown();
                md.transform(in, out);
        } catch (ParseException | IOException e) {
            log.error("unable to generate html output for {}", name, e);
        }
        return out.toString();
    }
}
