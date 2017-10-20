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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
public class Talk implements Comparable<Talk> {

    private String title;
    private String date;
    private Calendar dateObject;
    private String description = "Aucune description";
    private String youtubeVideoUrl;
    private String startTime;
    private String endTime;
    private List<String> speakers = new ArrayList<>();
    private List<String> keywords = new ArrayList<>();

    private List<Speaker> speakersObject = new ArrayList<>();
    private String internalUrl;
    private String externalUrl;

    private void initDate() {
        if (dateObject == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("y/M/d");
            try {
                dateObject = Calendar.getInstance();
                dateObject.setTime(sdf.parse(date));
            } catch (ParseException ex) {
                Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
                dateObject = Calendar.getInstance();
            }
        }
    }

    public int getEventYear() {
        initDate();
        return dateObject.get(Calendar.YEAR);
    }

    public int getEventMonth() {
        initDate();
        return dateObject.get(Calendar.MONTH) + 1;
    }

    public int getEventDay() {
        initDate();
        return dateObject.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int compareTo(Talk o) {
        return title.compareTo(o.title);
    }
}
