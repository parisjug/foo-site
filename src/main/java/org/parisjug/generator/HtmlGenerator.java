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
