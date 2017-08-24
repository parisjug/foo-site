package org.parisjug;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.parisjug.eventbrite.event.EventbriteResponse;
import org.parisjug.generator.EventbriteGenerator;
import org.parisjug.generator.MdGenerator;
import org.parisjug.generator.HtmlGenerator;
import org.parisjug.generator.YamlReader;
import org.parisjug.model.Event;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class MainEventbrite {

    public static final String EVENTBRITE_OAUTH_PERSONAL_TOKEN = System.getenv("EVENTBRITE_OAUTH_PERSONAL_TOKEN");
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String... args) {
        YamlReader reader = new YamlReader();
        EventbriteGenerator eventbriteGenerator = new EventbriteGenerator();
        HtmlGenerator htmlGenerator = new HtmlGenerator();
        MdGenerator mdGenerator = new MdGenerator();

        Optional<Event> event = reader.readEvent("20170613-docker");

        MainEventbrite mainEventbrite = new MainEventbrite();

        String htmlContent = htmlGenerator.generateHtmlForEvent(mdGenerator, "20170613-docker");

        mainEventbrite.createAndPublishEvent(eventbriteGenerator, event.get(), htmlContent);
    }


    public boolean createAndPublishEvent(EventbriteGenerator eventbriteGenerator, Event event, String htmlContent) {
        String jsonRequest = eventbriteGenerator.generateRequest(event, htmlContent);

        Optional<String> responseBody = getResponseBodyFromEventbrite(jsonRequest, "https://www.eventbriteapi.com/v3/events/");

        if (!responseBody.isPresent()) {
            log.error("unable to continue");
            return false;
        }

        Optional<EventbriteResponse> eventbriteResponse = eventbriteGenerator.parseResponse(responseBody.get());

        Optional<String> ticketClassResponseBody = Optional.empty();
        String id = null;
        if (eventbriteResponse.isPresent()) {
            id = eventbriteResponse.get().getId();

            String ticketClassJsonRequest = eventbriteGenerator.generateTicketClassRequest(event);
            ticketClassResponseBody = getResponseBodyFromEventbrite(ticketClassJsonRequest, "https://www.eventbriteapi.com/v3/events/" + id + "/ticket_classes/");
        } else {
            log.error("an error occured during the unmarshalling");
            return false;
        }

        Optional<String> publishResponseBody = Optional.empty();
        if (ticketClassResponseBody.isPresent()) {
            publishResponseBody = getResponseBodyFromEventbrite("", "https://www.eventbriteapi.com/v3/events/" + id + "/publish/");
        }

        if (publishResponseBody.isPresent()) {
            log.debug("response from publish request {}", publishResponseBody.get());
            return true;
        }
        return false;
    }

    private Optional<String> getResponseBodyFromEventbrite(String jsonRequest, String url) {
        RequestBody body = RequestBody.create(JSON, jsonRequest);
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + EVENTBRITE_OAUTH_PERSONAL_TOKEN)
                .header("Content-Type", "application/json")
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            log.error("unable to send the eventbrite request", e);
            return Optional.empty();
        }

        if (response.code() != 200) {
            try {
                log.error("error while sending. Status code {}: {}", response.code(), response.body().string());
            } catch (IOException e) {
                log.error("error while sending. Status code {}: unable to read content", response.code());
            }
            return Optional.empty();
        }

        String responseBody = null;
        try {
            responseBody = response.body().string();
            if (response.code() != 200) {
                log.error("error while sending. Status code {}: {}", response.code(), responseBody);
                return Optional.empty();
            }
        } catch (IOException e) {
            log.error("unable to read the response. Status code: {}", response.code(), e);
            return Optional.empty();
        }
        log.debug("response from {}: {}", url, responseBody);
        return Optional.ofNullable(responseBody);
    }
}
