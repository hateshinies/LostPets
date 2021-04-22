import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.BasicConnectionPool;
import utils.SqlCreator;
import utils.SqlExecutor;
import utils.DbProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class SqlExecutorTest {
    static Connection connection;

    @BeforeAll
    public static void connect() throws SQLException {
        Properties props = DbProperties.getProps();
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        connection = BasicConnectionPool.create(url, username, password).getConnection();
    }


    @Test
    public void shouldCreateSqlExecutor() {
        SqlExecutor executor = new SqlExecutor(connection);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(executor).hasNoNullFieldsOrProperties();
        soft.assertAll();
    }

    @Test
    public void shouldExecuteSimpleLongQuery() throws SQLException {
        SqlExecutor executor = new SqlExecutor(connection);

        long id = executor.simpleLongQuery("insert into payment (\"extraId\",amount,\"transactionId\",\"dateTime\") values (48,540,74918741,(48,540,'74918741','1619089'))");

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(id).isInstanceOf(long.class);
        soft.assertAll();
    }

    @Test
    public void shouldExecuteSimpleStringQuery() throws SQLException {
        SqlExecutor executor = new SqlExecutor(connection);

        String email = executor.simpleStringQuery("insert into actor (\"role\",\"email\") values ('RESPONDER','someemail@mail.ru')");

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(email).isInstanceOf(String.class);
        soft.assertAll();
    }

    @Test
    public void shouldExecuteGetValuesQuery() {

    }
}
