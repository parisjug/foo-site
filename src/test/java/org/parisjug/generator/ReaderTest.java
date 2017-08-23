package org.parisjug.generator;

import org.junit.Test;
import org.parisjug.model.Event;
import org.parisjug.model.Speaker;
import org.parisjug.model.Talk;

import java.net.URISyntaxException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ReaderTest {

    private YamlReader reader = new YamlReader();

    @Test
    public void read_existing_event_should_success() throws URISyntaxException {
        Optional<Event> event = reader.readEvent("20170214-jigsaw");

        assertThat(event).isNotEmpty();
        assertThat(event.get().getTitle()).isEqualTo("Soirée Java is going nut's (JDK 9 - jigsaw)");
        assertThat(event.get().getDate()).isEqualTo("2017/02/14");
        assertThat(event.get().getTalks().get(0)).isEqualTo("20170214-jigsaw");
        assertThat(event.get().getTalksObject().get(0).getTitle()).isEqualTo("Java is going nuts | native");
    }

    @Test
    public void read_non_existing_event_should_success() throws URISyntaxException {
        Optional<Event> event = reader.readEvent("none");

        assertThat(event).isEmpty();
    }

    @Test
    public void read_existing_speaker_should_success() throws URISyntaxException {
        Optional<Speaker> speaker = reader.readSpeaker("forax-remi");

        assertThat(speaker).isNotEmpty();
        assertThat(speaker.get().getFirstName()).isEqualTo("Remi");
        assertThat(speaker.get().getLastName()).isEqualTo("Forax");
        assertThat(speaker.get().getTalks().get(0)).isEqualTo("20170214-jigsaw");
        assertThat(speaker.get().getDescription()).isEqualTo("Maitre de conférence à l'université Paris Est - Marne la Vallée,\n" +
                "Expert pour les JSRs 292 et 335.\n" +
                "Tombé dans le Java quand il était petit.\n");
        assertThat(speaker.get().getTalksObject().get(0).getTitle()).isEqualTo("Java is going nuts | native");

    }

    @Test
    public void read_non_existing_speaker_should_success() throws URISyntaxException {
        Optional<Speaker> speaker = reader.readSpeaker("none");

        assertThat(speaker).isEmpty();
    }

    @Test
    public void read_existing_talk_should_success() throws URISyntaxException {
        Optional<Talk> talk = reader.readTalk("20170214-jigsaw");

        assertThat(talk).isNotEmpty();
        assertThat(talk.get().getDescription()).isEqualTo("On a tout cassé ton Java, mais c'est pour ton bien, je t'assure.\n" +
                "Lors du planning de Java 6, il y avait déjà écrit, virer le classpath et améliorer la sécurité en introduisant un système de modules, connu sous le nom de jigsaw, cette feature a été décalée à la version 7, puis à la 8, et arrive enfin dans la version 9.\n" +
                "Bon alors c'est quoi jigsaw, je commencerai par rappeler pourquoi Java a besoin d'un système de modules, puis j'expliquerai comment les modules fonctionnent, pourquoi des applis qui marchent avec Java 8 ne vont pas marcher avec Java 9 et enfin ce que les modules apportent en plus comme nouvelles fonctionnalités à savoir les services, la création d'images et la compilation ahead of time.\n"
                );
    }

    @Test
    public void read_non_existing_talk_should_success() throws URISyntaxException {
        Optional<Talk> talk = reader.readTalk("none");

        assertThat(talk).isEmpty();
    }
}
