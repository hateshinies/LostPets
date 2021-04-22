/*
package dao;

import domain.Request;
import domain.User;
import org.postgresql.jdbc.PreferQueryMode;
import utils.SqlExecutor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RequestDao {
    private final SqlExecutor executor;

    public RequestDao(Connection connection) {
        this.executor = new SqlExecutor(connection);
    }

    public long create(Request publication) throws Exception {
        if (publication.isUnPacked())
            publication.pack();
        return executor.simpleQuery(publication.queries.createQuery);
    }

    public long update(Request publication) throws Exception {
        if (publication.isUnPacked())
            publication.pack();
        Map<String, String> object = publication.shuttle.cargo;

        if (object.isEmpty())
            throw new IllegalStateException("nothing to update!");

        return executor.simpleQuery(publication.queries.updateQuery);
    }

    public long delete(Request publication) throws SQLException {
        return executor.simpleQuery(publication.queries.deleteQuery);
    }

    public Optional<Request> getOne(Request publication) throws Exception {
        if (publication.isUnPacked())
            publication.pack();
        Map<String, String> object = executor.getValuesQuery(publication.queries.getOneQuery).get(0);
        if (object.isEmpty())
            return Optional.empty();
        else {
            Request newRequest = new Request();
            newRequest.shuttle.cargo = object;
            newRequest.unpack(Request.class);
            return Optional.of(newRequest);
        }
    }

    public List<Request> getAll() throws Exception {
        User publication = new User();
        if (publication.isUnPacked())
            publication.pack();
        List<Map<String, String>> objectList = executor.getValuesQuery(publication.queries.getAllQuery);

        if (objectList.isEmpty())
            throw new IllegalStateException("table is empty!");

        List<Request> adsList = new ArrayList<>();
        for (Map<String, String> object : objectList) {
            Request newRequest = new Request();
            newRequest.shuttle.cargo = object;
            newRequest.unpack(Request.class);
            adsList.add(newRequest);
        }
        return adsList;
    }

}

*/



