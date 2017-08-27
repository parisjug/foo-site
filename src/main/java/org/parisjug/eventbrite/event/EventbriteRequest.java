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
package org.parisjug.eventbrite.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventbriteRequest {
    private Event event;
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