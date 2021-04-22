import domain.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class ShuttleTest {

    @Test
    public void shouldCreateNonEmptyShuttle() throws Exception {
        User user = new User();
        user.pack();

        User newUser = (User) user.unpack(User.class);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(newUser).hasNoNullFieldsOrProperties();
        soft.assertAll();
    }
}
