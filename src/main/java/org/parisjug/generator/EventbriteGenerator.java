package org.parisjug.generator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.parisjug.eventbrite.event.End;
import org.parisjug.eventbrite.event.Start;
import org.parisjug.eventbrite.event.*;
import org.parisjug.eventbrite.ticketclasses.TicketClass;
import org.parisjug.eventbrite.ticketclasses.TicketClassRequest;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Slf4j
public class EventbriteGenerator {

    final private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    final private ObjectMapper objectMapper;

    public EventbriteGenerator() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public String generateRequest(org.parisjug.model.Event event) {
        Name name = Name.builder().html(event.getTitle() + " (" + event.getDate() + ")").build();
        Description description = Description.builder().html(event.getDescription()).build();

        LocalDate startDate = LocalDate.parse(event.getDate(), formatter);
        ZoneId france = ZoneId.of("Europe/Paris");

        String[] hourStartTimes = event.getStartTime().split(":");
        LocalDateTime startTime = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), Integer.parseInt(hourStartTimes[0]), Integer.parseInt(hourStartTimes[1]), 0);
        ZonedDateTime dateAndTimeInFrance = ZonedDateTime.of(startTime, france );

        Instant startInstant = dateAndTimeInFrance.toInstant();
        Start start = Start.builder().timezone("Europe/Paris").utc(startInstant.toString()).build();

        ZonedDateTime endTime = dateAndTimeInFrance.plus(event.getDuration(), ChronoUnit.MINUTES);
        Instant endInstant = endTime.toInstant();
        End end = End.builder().timezone("Europe/Paris").utc(endInstant.toString()).build();

        EventbriteRequest eventbriteRequest = EventbriteRequest.builder().event(Event.builder()
                        .name(name)
                        .description(description)
                        .start(start)
                        .end(end)
                        .capacity(event.getCapacity())
//                .currency("EUR)
//                .listed(true)
//                .shareable(true)
//                .invite_only(false)
//                .online_event(false)
//                .show_remaining(true)
//                .hide_start_date(false)
//                .hide_end_date(false)
//                .organizer_id("880413051")
//                .venue_id("12251888")
//                .category_id("102")
//                .subcategory_id(null)
//                .format_id("1")
                        .build()
        ).build();

        try {
            return objectMapper.writeValueAsString(eventbriteRequest);
        } catch (JsonProcessingException e) {
            log.error("unable to parse json", e);
            return "";
        }
    }


    public String generateTicketClassRequest(org.parisjug.model.Event event) {

        LocalDate startDate = LocalDate.parse(event.getDate(), formatter);
        ZoneId france = ZoneId.of("Europe/Paris");

        LocalDateTime startTime = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), 0, 0);
        Instant startInstant = ZonedDateTime.of(startTime, france ).minus(event.getNumberOfDaysSellAreOpenBefore(), ChronoUnit.DAYS).toInstant();


        String[] hourStartTimes = event.getStartTime().split(":");
        LocalDateTime startEventTime = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), Integer.parseInt(hourStartTimes[0]), Integer.parseInt(hourStartTimes[1]), 0);
        ZonedDateTime dateAndTimeInFrance = ZonedDateTime.of(startEventTime, france );
        Instant endInstant = dateAndTimeInFrance.plus(event.getDuration(), ChronoUnit.MINUTES).toInstant();

        TicketClass ticketClass = TicketClass.builder()
                .name(event.getTitle())
                .quantity_total(event.getCapacity())
                .sales_start(startInstant.toString())
                .sales_end(endInstant.toString())
//                .donation()
//                .free()
//                .minimum_quantity()
//                .hidden()
//                .include_fee()
//                .hide_description()
//                .auto_hide()
                .build();

        TicketClassRequest ticketClassRequest = TicketClassRequest.builder().ticket_class(ticketClass).build();

        try {
            return objectMapper.writeValueAsString(ticketClassRequest);
        } catch (JsonProcessingException e) {
            log.error("unable to parse json", e);
            return "";
        }
    }

    public Optional<EventbriteResponse> parseResponse(String json) {
        try {
            EventbriteResponse eventbriteResponse = objectMapper.readValue(json, EventbriteResponse.class);
            return Optional.ofNullable(eventbriteResponse);
        } catch (IOException e) {
            log.error("unable to parse json", e);
            return Optional.empty();
        }
    }

   /*
   request
{
  "event": {
    "name": {
      "html": "Soirée test (09/05/17)"
    },
    "description": {
      "html": "\r\n\r\n19h15 à 19h30 : Accueil\r\n19h30 - Présentation de JHipster 4\r\nPrésentation de FUN par Charles Sabourdin    ,     avec le développement et le déploiement d’une application Spring Boot + Angular 2 en moins d’une heure\r\npar Julien Dubois\r\n20h25 - JHipster Domain Language\r\nGénérer visuellement son application avec le JHipster Domain Language    ,     par Mathieu Abou Aichi\r\npar Mathieu Abou Aichi\r\n20h45 à 21h15 : Buffet 21h15 - Docker et JHipster\r\nDevops facile avec Docker et JHipster par Pascal Grimaud\r\npar Pascal Grimaud\r\n21h35 - Angular 2 et JHipster\r\nUtilisation d’Angular 2 avec JHipster: risques    ,     difficultés et bénéfices par William Marques\r\npar William Marques\r\n21h45 - Docker et JHipster\r\nUne architecture microservices “out of the box” avec JHipster\r\npar Julien Dubois\r\n22h10 à ... : 3ème mi-temps des juggers dans un lieu sélectionné par votre Crew ;-)\r\nSponsors de l'association\r\n\r\nPlatinium\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n \r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n       \r\n\r\nGold\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n"
    },
    "start": {
      "timezone": "Europe/Paris",
      "utc": "2017-12-24T17:30:00Z"
    },
    "end": {
      "timezone": "Europe/Paris",
      "utc": "2017-12-24T20:30:00Z"
    },
    "capacity": 210,
    "currency": "EUR",
    "listed": true,
    "shareable": true,
    "invite_only": false,
    "online_event": false,
    "show_remaining": true,
    "hide_start_date": false,
    "hide_end_date": false,
    "organizer_id": "880413051",
    "venue_id": "12251888",
    "category_id": "102",
    "subcategory_id": null,
    "format_id": "1"
  }
}
    */
}
