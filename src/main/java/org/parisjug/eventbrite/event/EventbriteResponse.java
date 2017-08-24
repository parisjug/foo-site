package org.parisjug.eventbrite.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventbriteResponse {
    private Name name;
    private Description description;
    private Start start;
    private End end;

    private int capacity;
    private String currency = "EUR";
    private boolean listed = true;
    private boolean shareable = true;
    private boolean invite_only = false;
    private boolean online_event = false;
    private boolean show_remaining = true;
    private boolean hide_start_date = false;
    private boolean hide_end_date = false;
    private String organizer_id = "880413051";
    private String venue_id = "12251888";
    private String category_id = "102";
    private String subcategory_id = null;
    private String format_id = "1";

    private String id;
    private String url;
    private String created;
    private String changed;
    private boolean capacity_is_custom;
    private String status;
    private int tx_time_limit;
    private String locale;
    private boolean is_locked;
    private String privacy_setting;
    private boolean is_series;
    private boolean is_series_parent;
    private boolean is_reserved_seating;
    private String source;
    private boolean is_free;
    private String version;
    private String logo_id;
    private String resource_uri;
    private String logo;
}






/*
response
{
  "name": {
    "text": "Soirée test (09/05/17)",
    "html": "Soirée test (09/05/17)"
  },
  "description": {
    "text": "\r\n\r\n19h15 à 19h30 : Accueil\r\n19h30 - Présentation de JHipster 4\r\nPrésentation de FUN par Charles Sabourdin    ,     avec le développement et le déploiement d’une application Spring Boot + Angular 2 en moins d’une heure\r\npar Julien Dubois\r\n20h25 - JHipster Domain Language\r\nGénérer visuellement son application avec le JHipster Domain Language    ,     par Mathieu Abou Aichi\r\npar Mathieu Abou Aichi\r\n20h45 à 21h15 : Buffet 21h15 - Docker et JHipster\r\nDevops facile avec Docker et JHipster par Pascal Grimaud\r\npar Pascal Grimaud\r\n21h35 - Angular 2 et JHipster\r\nUtilisation d’Angular 2 avec JHipster: risques    ,     difficultés et bénéfices par William Marques\r\npar William Marques\r\n21h45 - Docker et JHipster\r\nUne architecture microservices “out of the box” avec JHipster\r\npar Julien Dubois\r\n22h10 à ... : 3ème mi-temps des juggers dans un lieu sélectionné par votre Crew ;-)\r\nSponsors de l'association\r\n\r\nPlatinium\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n \r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n       \r\n\r\nGold\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n",
    "html": "\r\n\r\n19h15 à 19h30 : Accueil\r\n19h30 - Présentation de JHipster 4\r\nPrésentation de FUN par Charles Sabourdin    ,     avec le développement et le déploiement d’une application Spring Boot + Angular 2 en moins d’une heure\r\npar Julien Dubois\r\n20h25 - JHipster Domain Language\r\nGénérer visuellement son application avec le JHipster Domain Language    ,     par Mathieu Abou Aichi\r\npar Mathieu Abou Aichi\r\n20h45 à 21h15 : Buffet 21h15 - Docker et JHipster\r\nDevops facile avec Docker et JHipster par Pascal Grimaud\r\npar Pascal Grimaud\r\n21h35 - Angular 2 et JHipster\r\nUtilisation d’Angular 2 avec JHipster: risques    ,     difficultés et bénéfices par William Marques\r\npar William Marques\r\n21h45 - Docker et JHipster\r\nUne architecture microservices “out of the box” avec JHipster\r\npar Julien Dubois\r\n22h10 à ... : 3ème mi-temps des juggers dans un lieu sélectionné par votre Crew ;-)\r\nSponsors de l'association\r\n\r\nPlatinium\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n \r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n       \r\n\r\nGold\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n"
  },
  "id": "37301399490",
  "url": "https://www.eventbrite.fr/e/soiree-test-090517-tickets-37301399490",
  "start": {
    "timezone": "Europe/Paris",
    "local": "2017-12-24T18:30:00",
    "utc": "2017-12-24T17:30:00Z"
  },
  "end": {
    "timezone": "Europe/Paris",
    "local": "2017-12-24T21:30:00",
    "utc": "2017-12-24T20:30:00Z"
  },
  "created": "2017-08-24T07:26:50Z",
  "changed": "2017-08-24T07:26:50Z",
  "capacity": 210,
  "capacity_is_custom": true,
  "status": "draft",
  "currency": "EUR",
  "listed": true,
  "shareable": true,
  "invite_only": false,
  "online_event": false,
  "show_remaining": true,
  "tx_time_limit": 480,
  "hide_start_date": false,
  "hide_end_date": false,
  "locale": "en_US",
  "is_locked": false,
  "privacy_setting": "unlocked",
  "is_series": false,
  "is_series_parent": false,
  "is_reserved_seating": false,
  "source": "api",
  "is_free": true,
  "version": "3.0.0",
  "logo_id": null,
  "organizer_id": "880413051",
  "venue_id": "12251888",
  "category_id": "102",
  "subcategory_id": null,
  "format_id": "1",
  "resource_uri": "https://www.eventbriteapi.com/v3/events/37301399490/",
  "logo": null
}

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