import domain.Actor;
import domain.Publication;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PublicationTest {

    @Test
    public void shouldCreatePublication() {
        String photo = "C:/Users/newPhoto.png";
        String description = "new descr";
        Actor actor = Mockito.mock(Actor.class);
        Publication publication = new Publication();
        publication.create(new Publication(actor,photo, description));

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(publication).hasFieldOrPropertyWithValue("photo", photo);
        soft.assertAll();
    }
}
