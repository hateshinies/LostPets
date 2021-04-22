import domain.Actor;
import domain.User;
import domain.UserRole;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void shouldRegisterUser() {
        String email = "vasko@dagama.com";
        UserRole role = UserRole.OWNER;
        String password = "easypass";
        String name = "Jack";
        String phone = "8-970-421-9812";
        Actor actor = new Actor(email, role, null);
        User user = new User();

        String confirmationCode = user.startTwoStepRegistration(actor);
        User userFromWeb = new User(confirmationCode, password, role, name, phone);
        boolean result = user.finishTwoStepRegistration(userFromWeb);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(result).isTrue();
        soft.assertThat(user).hasFieldOrProperty("token");
        soft.assertAll();
    }

    @Test
    public void shouldAuthenticateUser() {
        String email = "vasko@dagama.com";
        UserRole role = UserRole.VOLUNTEER;
        String password = "easypass";
        String name = "Jack";
        String phone = "8-970-421-9812";
        Actor actor = new Actor(email, role, null);
        User user = new User();

        String confirmationCode = user.startTwoStepRegistration(actor);
        User userFromWeb = new User(confirmationCode, password, role, name, phone);
        user.finishTwoStepRegistration(userFromWeb);
        //вместо этого внести юзера скриптом в базу через executor

        Actor authedActor = user.auth(password);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(authedActor).hasFieldOrProperty("token");
        soft.assertAll();
    }

    @Test
    public void shouldBlockUser() {
        String email = "vasko@dagama.com";
        UserRole role = UserRole.RESPONDER;
        String password = "easypass";
        String name = "Jack";
        String phone = "8-970-421-9812";
        Actor actor = new Actor(email, role, null);
        User user = new User();

        String confirmationCode = user.startTwoStepRegistration(actor);
        User userFromWeb = new User(confirmationCode, password, role, name, phone);
        user.finishTwoStepRegistration(userFromWeb);
        //вместо этого внести юзера скриптом в базу через executor

        user.block();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(user).hasFieldOrPropertyWithValue("token", null).hasFieldOrPropertyWithValue("isActual",false);
        soft.assertAll();
    }
}
