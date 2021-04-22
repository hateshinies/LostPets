/*
package dao;

import domain.User;
import utils.SqlExecutor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDao {
    private final SqlExecutor executor;

    public UserDao(Connection connection) {
        this.executor = new SqlExecutor(connection);
    }

    //проблема 1 - нет ID, нет MetaData
    public String create(User user) throws Exception {
        if (user.isUnPacked())
            user.pack();
        return executor.simpleStringQuery(user.queries.createQuery);
    }

    public String update(User user) throws Exception {
        if (user.isUnPacked())
            user.pack();
        Map<String, String> object = user.getCargo();

        if (object.isEmpty())
            throw new IllegalStateException("nothing to update!");

        return executor.simpleStringQuery(user.queries.updateQuery);
    }

    public String delete(User user) throws SQLException {
        return executor.simpleStringQuery(user.queries.deleteQuery);
    }

    public Optional<User> getOne(User user) throws Exception {
        if (user.isUnPacked())
            user.pack();
        Map<String, String> object = executor.getValuesQuery(user.queries.getOneQuery).get(0);
        if (object.isEmpty())
            return Optional.empty();
        else {
            User newUser = new User();
            newUser.setCargo(object);
            newUser.unpack(User.class);
            return Optional.of(newUser);
        }
    }

    public List<User> getAll() throws Exception {
        User publication = new User();
        publication.pack();
        List<Map<String, String>> objectList = executor.getValuesQuery(publication.queries.getAllQuery);

        if (objectList.isEmpty())
            throw new IllegalStateException("table is empty!");

        List<User> adsList = new ArrayList<>();
        for (Map<String, String> object : objectList) {
            User newUser = new User();
            newUser.setCargo(object);
            newUser.unpack(User.class);
            adsList.add(newUser);
        }
        return adsList;
    }
}

*/
