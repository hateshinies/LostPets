import domain.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.BasicConnectionPool;
import utils.DbProperties;
import utils.SqlCreator;
import utils.SqlExecutor;

import java.sql.Connection;
import java.util.Properties;

public class SqlCreatorTest {
    //подготовить для тестов - тестовую таблицу
    private static Connection connection;
    private static SqlExecutor executor;
    private static SoftAssertions soft;
    private static SqlCreator sqlCreator;


    @BeforeAll
    public static void connect() throws Exception {
        Properties props = DbProperties.getProps();
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        connection = BasicConnectionPool.create(url, username, password).getConnection();
        executor = new SqlExecutor(connection);
        soft = new SoftAssertions();
    }

    @AfterEach
    public void assertAll(){
        soft.assertAll();
    }

    @Test
    public void shouldExecuteInsertQuery() throws Exception {
        User user = new User();
        user.pack();

        long createdId = executor.simpleLongQuery(user.queries.createQuery);

        soft.assertThat(createdId).isInstanceOf(Long.class);
    }
/*
    @Test
    public void shouldExecuteUpdateQuery() throws SQLException {
        long id = executor.insert(sqlCreator.createQuery, new String[]{"Jacob", "8-900-390-9248", "mostbeautiful@mail.com"});

        long result = executor.update(sqlCreator.updateQuery, new String[]{"Bob", "8-998-390-9248", "thebest@mail.com", Long.toString(id)});

        soft.assertThat(result).isInstanceOf(Long.class);
    }

    @Test
    public void shouldExecuteDeleteQuery() throws SQLException {
        long id = executor.insert(sqlCreator.createQuery, new String[]{"Jacob", "8-900-390-9248", "mostbeautiful@mail.com"});

        long result = executor.delete(sqlCreator.deleteQuery, id);

        soft.assertThat(result).isInstanceOf(Long.class);
    }

    @Test
    public void shouldExecuteGetOneQuery() throws SQLException {
        long id = executor.insert(sqlCreator.createQuery, new String[]{"Jacob", "8-900-390-9248", "mostbeautiful@mail.com"});

        Map<String, String> fields = executor.getById(sqlCreator.getOneQuery, id);

        soft.assertThat(fields).hasNoNullFieldsOrProperties();
    }

    @Test
    public void shouldExecuteGetAllQuery() throws SQLException {
        List<Map<String, String>> fieldsList = executor.getAll(sqlCreator.getAllQuery);

        soft.assertThat(fieldsList).hasAtLeastOneElementOfType(String[].class);
    }*/
}
