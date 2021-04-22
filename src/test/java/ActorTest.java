import domain.Actor;
import domain.UserRole;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class ActorTest {

    @Test
    public void shouldCreateActor() {
        String email = "zero@cocacola.com";
        UserRole role = UserRole.OWNER;
        Actor actor = new Actor(email, role, null);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(actor).hasFieldOrPropertyWithValue("email", email);
        soft.assertAll();
    }

    @Test
    public void shouldCheckActorIsAuthed() {
        String email = "zero@cocacola.com";
        UserRole role = UserRole.ADMIN;
        Actor actor = new Actor(email, role, null);

        boolean result = actor.isAuthed();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(result).isFalse();
        soft.assertAll();
    }
}
