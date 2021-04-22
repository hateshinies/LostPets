import domain.Actor;
import domain.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ResponseTest {

    @Test
    public void shouldCreateResponse() {
        long pubId = 31L;
        String photo = "C:/Users/photo.jpg";
        String description = "very short description";
        Response response = new Response();

        response.create(pubId, photo, description);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(response).hasFieldOrPropertyWithValue("photo", photo);
        soft.assertAll();
    }


    @Test
    public void shouldCreateResponseUsingActor() {
        long pubId = 31L;
        String photo = "C:/Users/photo.jpg";
        String description = "very short description";
        Actor actor = Mockito.mock(Actor.class);
        Response response = new Response(actor);

        response.create(pubId, photo, description);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(response).hasFieldOrPropertyWithValue("actor", actor);
        soft.assertAll();
    }
}
