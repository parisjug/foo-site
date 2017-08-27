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
package org.parisjug.model;

import lombok.Data;

@Data
public class TeamMember implements Comparable<TeamMember> {
    private String lastName;
    private String firstName;
    private String description;
    private String photo;
    private String tweet;
    private String url;

    private String internalUrl;
    private String externalUrl;

    @Override
    public int compareTo(TeamMember o) {
        return lastName.compareTo(o.lastName);
    }
}
