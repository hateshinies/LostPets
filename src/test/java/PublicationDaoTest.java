import dao.PublicationDao;
import domain.Actor;
import domain.Publication;
import domain.UserRole;
import org.assertj.core.api.SoftAssertions;
import org.hsqldb.cmdline.SqlFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.BasicConnectionPool;
import utils.DbProperties;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class PublicationDaoTest {
    static Connection connection;
    static Long pubId;
    static String email;

    @BeforeAll
    public static void connect() throws SQLException {
        Properties props = DbProperties.getProps();
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        connection = BasicConnectionPool.create(url, username, password).getConnection();
    }

    @Test
    public void shouldCreatePublication() throws Exception {
        PublicationDao publicationDao = new PublicationDao(connection);

        String photo = "C:/Users/newPhoto.png";
        String description = "new descr";
        email = new Random().nextInt() + "email@mail.ru";
        Actor actor = new Actor(email, UserRole.RESPONDER, null);
        Publication fromWebPublication = new Publication(actor, photo, description);

        Publication publication = new Publication();
        publication.create(fromWebPublication);

        pubId = publicationDao.create(publication);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(pubId).isInstanceOf(Long.class);
        soft.assertAll();
    }

    @Test
    public void shouldUpdatePublication() throws Exception {
        PublicationDao publicationDao = new PublicationDao(connection);
        SqlFile sqlFile = new SqlFile(new File("src/test/pub_test_create.sql"));
        sqlFile.setConnection(connection);
        sqlFile.execute();
        if (pubId == null)
            pubId = 9L;
        Actor actor = new Actor(email, UserRole.ADMIN, "abcdabcd");
        Publication publicationForUpdate = new Publication(pubId);
        publicationForUpdate.create(new Publication(actor, "D:/Users/newPhoto.png", "old descr"));

        Long result = publicationDao.update(publicationForUpdate);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(result).isInstanceOf(Long.class);
        soft.assertAll();
    }

    @Test
    public void shouldDeletePublication() throws Exception {
        PublicationDao publicationDao = new PublicationDao(connection);

        if (pubId == null)
            pubId = 9L;
        Publication fromWebPublication = new Publication(pubId);

        Long result = publicationDao.delete(fromWebPublication);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(result).isInstanceOf(Long.class);
        soft.assertAll();
    }

    @Test
    public void shouldGetOnePublication() throws Exception {
        PublicationDao publicationDao = new PublicationDao(connection);
        if (pubId == null)
            pubId = 4L;

        Publication publication = publicationDao.getOne(new Publication(pubId));

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(publication).hasFieldOrPropertyWithValue("id", pubId);
        soft.assertAll();
    }

    @Test
    public void shouldGetAllPublications() throws Exception {
        PublicationDao publicationDao = new PublicationDao(connection);

        List<Publication> publicationList = publicationDao.getAll();

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(publicationList).hasAtLeastOneElementOfType(Publication.class);
        soft.assertAll();
    }
}
