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
package org.parisjug;

public class ConfigUtils {

    public static final String URL_EXTERNAL = "https://www.parisjug.org";
    public static final String APPLICATION_CONTEXT = "foo-site";

    public static final String TEMPLATE_PATH = "templates";
    public static final String MARKDOWN_DEST_PATH = "src/site/markdown";

    public static final String YAML_SPONSORS_PATH = "sponsors";
    public static final String YAML_TEAM_PATH = "teams";
    public static final String YAML_TALKS_PATH = "talks";
    public static final String YAML_EVENTS_PATH = "events";
    public static final String YAML_SPEAKERS_PATH = "speakers";

    public static final String MD_SPEAKERS_PATH = "speakers";
    public static final String MD_EVENTS_PATH = "events";
    public static final String MD_TALKS_PATH = "talks";
    public static final String MD_TEAM_PATH = "teams";
    public static final String MD_SPONSORS_PATH = "sponsors";

    public static final String SPEAKERS_TEMPLATE = "speakers.vm";
    public static final String SPEAKER_TEMPLATE = "speaker.vm";
    public static final String EVENT_TEMPLATE = "event.vm";
    public static final String EVENTS_TEMPLATE = "events.vm";
    public static final String TALK_TEMPLATE = "talk.vm";
    public static final String TALKS_TEMPLATE = "talks.vm";
    public static final String TEAM_TEMPLATE = "team.vm";
    public static final String TEAMS_TEMPLATE = "teams.vm";
    public static final String SPONSOR_TEMPLATE = "sponsor.vm";
    public static final String SPONSORS_TEMPLATE = "sponsors.vm";
    public static final String PREVIOUS_EVENTS_TEMPLATE = "previous_events.vm";
    public static final String NEXT_EVENTS_TEMPLATE = "next_events.vm";

    private ConfigUtils() {
    }

}
