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
