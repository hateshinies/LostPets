package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс осуществляет запросы в базу.
 */
public class SqlExecutor {
    private final Connection connection;

    public SqlExecutor(Connection connection) {
        this.connection = connection;
    }

   /* public long insert(String preparedQuery) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(preparedQuery);

        for (int i = 0; i < fields.length; i++)
            statement.setString(i + 1, fields[i]);
        return simpleQuery(statement.toString());
    }

    public long update(String preparedQuery, String[] fields) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(preparedQuery);

        for (int i = 0; i < fields.length; i++) {
            //последним в массиве идет long id
            if (i == fields.length - 1)
                statement.setLong(i + 1, Long.parseLong(fields[i]));
                //остальные элементы - String
            else
                statement.setString(i + 1, fields[i]);
        }
        return simpleQuery(statement.toString());
    }

    public long delete(String preparedQuery, Long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(preparedQuery);
        statement.setLong(1, id);
        return simpleQuery(statement.toString());
    }

    public Map<String,String> getById(String preparedQuery, long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(preparedQuery);
        statement.setLong(1, id);
        Map<String, String> object = getValuesQuery(statement.toString()).get(0);

        if (object.isEmpty())
            return new HashMap<>();

        return object;
    }

    public List<Map<String,String>> getAll(String preparedQuery) throws SQLException {
        return getValuesQuery(preparedQuery);
    }*/

    /**
     * Метод выполняет простой sql-запрос, подходит для insert, update, delete с id типа long
     *
     * @return ID измененной записи
     */
    public long simpleLongQuery(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        System.out.println(statement);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        long id = 0;
        while (resultSet.next())
            id = resultSet.getLong(1);
        return id;
    }

    /**
     * Метод выполняет простой sql-запрос, подходит для insert, update, delete с id типа String
     *
     * @return ID измененной записи
     */
    public String simpleStringQuery(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        System.out.println(statement);
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        String result = "";
        while (resultSet.next())
            result = resultSet.getString(1);
        return result;
    }

    /**
     * Метод возвращает список значений полей каждой строки. Подходит для всех select'ов.
     */
    public List<Map<String, String>> getValuesQuery(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        System.out.println(statement);
        ResultSet resultSet = statement.executeQuery();
        int columnsCount = resultSet.getMetaData().getColumnCount();

        List<Map<String, String>> objectsList = new ArrayList<>();
        Map<String, String> object = new HashMap<>();
        while (resultSet.next()) {
            for (int i = 0; i < columnsCount; i++) {
                String field = resultSet.getMetaData().getColumnName(i + 1);
                String value = resultSet.getString(i + 1);
                object.put(field, value);
            }
            objectsList.add(object);
        }
        if (objectsList.isEmpty())
            objectsList.add(null);
        return objectsList;
    }
}
