package org.parisjug.generator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

public class HtmlGeneratorTest {

    private HtmlGenerator htmlGenerator;
    private MdGenerator mdGenerator;

    @Before
    public void setup() throws IOException {
        htmlGenerator = new HtmlGenerator();
        mdGenerator = new MdGenerator();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void event_htmlGenerator_should_success() throws IOException, URISyntaxException {
        String output = htmlGenerator.generateHtmlForEvent(mdGenerator, "20170613-docker");
        assertThat(output).isNotEmpty();
    }

}
