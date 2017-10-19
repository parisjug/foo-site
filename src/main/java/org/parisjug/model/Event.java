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
import java.time.Instant;
import lombok.Data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
public class Event implements Comparable<Event> {

    private String title;
    private String date;
    private Calendar dateObject;
    private String description="Aucune description";
    private String flickUrl;
    private String youtubeVideoUrl;
    private List<String> talks = new ArrayList<>();
    private String eventbrite;
    private String startTime = "19:30"; //debut de l'event
    private int duration = 150; //min
    private int numberOfDaysSellAreOpenBefore = 6; //ouverture de l'event sur eventbrite ou sinon date - tant de jour
    private int capacity = 210;

    private List<Talk> talksObject = new ArrayList<>();
    private String internalUrl;
    private String externalUrl;


   
    private void initDate() {
        if (dateObject == null){
            SimpleDateFormat sdf = new SimpleDateFormat("y/M/d");
            try {
                dateObject = Calendar.getInstance();
                dateObject.setTime(sdf.parse(date));                        
            } catch (ParseException ex) {
                Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
                dateObject=Calendar.getInstance();
            }
        }
    }
    
    public int getEventYear() {
        initDate();
        return dateObject.get(Calendar.YEAR);
    }
    
    public int getEventMonth() {
        initDate();
        return dateObject.get(Calendar.MONTH)+1;
    }
    
    public int getEventDay(){
                initDate();
        return dateObject.get(Calendar.DAY_OF_MONTH);
    }
    @Override
    public int compareTo(Event o) {
        return date.compareTo(o.date);
    }
}
