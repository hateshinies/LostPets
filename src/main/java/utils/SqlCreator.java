package utils;

import java.util.Map;

/**
 * Класс, который создает каркас базовых sql-запросов, на основе имени таблицы и полей.
 * Его паблик поля *Query - это по сути PreparedStatement, куда остается подставить конкретные значения.
 */
public class SqlCreator {
    private static String entityName;

    public String createQuery;
    public String updateQuery;
    public String deleteQuery;
    public String getOneQuery;
    public String getAllQuery;

    public SqlCreator(Map<String, String> map) {
        prepareQueries(map);
    }

    private void prepareQueries(Map<String, String> map) {
        char[] letters = map.get("entityName").toCharArray();
        letters[0] = Character.toLowerCase(letters[0]);
        entityName = new String(letters);
        map.remove("entityName");

        createQuery = prepareCreateQuery(map);
        updateQuery = prepareUpdateQuery(map);
        deleteQuery = prepareDeleteQuery(map);
        getOneQuery = prepareGetOneQuery(map);
        getAllQuery = prepareGetAllQuery();
    }

    private String prepareCreateQuery(Map<String, String> map) {
        //t
        String createStartQuery = "insert into %s (";
        String createMiddleQuery = ") values (";
        String createEndQuery = ")";

        for (Map.Entry<String, String> entry : map.entrySet()) {
            //тут ID еще не должно быть в мапе
            if (entry.getKey().equals("id"))
                continue;
            createStartQuery = createStartQuery + "\"" + entry.getKey() + "\",";
            createMiddleQuery = createMiddleQuery + "'" + entry.getValue() + "',";
        }

        String preparedQuery = createStartQuery.replaceAll(".$", "") +
                createMiddleQuery.replaceAll(".$", "") +
                createEndQuery;

/*
        PreparedStatement statement = new Connection().prepareStatement(preparedQuery);
        statement.setObject(1, UserRole.USER.,java.sql.Types.OTHER);*/


        return String.format(preparedQuery, entityName);
    }

    private String prepareUpdateQuery(Map<String, String> map) {
        String updateStart = "update %s set ";
        String updateEnd = " where \"id\" = ";

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().contains("id"))
                updateEnd += entry.getValue();
            else if (entry.getKey().contains("email") && !map.containsKey("id"))
                updateEnd = updateEnd.replace("\"id\"", "\"email\"") + "'" + entry.getValue() + "'";
            else
                updateStart = updateStart + "\"" + entry.getKey() + "\" = '" + entry.getValue() + "',";

/*
            if (entry.getKey().contains("id")) {
                if (entry.getKey().equals("id"))
                    updateEnd += entry.getValue();
            } else {
                if (entry.getKey().equals("email"))
                    updateEnd += entry.getValue();
                else
                    updateStart += entry.getKey() + " = '" + entry.getValue() + "',";
            }*/
        }
        String preparedQuery = updateStart.replaceAll(".$", "") + updateEnd;
        return String.format(preparedQuery, entityName);

    }

    private String prepareDeleteQuery(Map<String, String> map) {
        String deleteQuery = "delete from %s where \"id\" = ";
        if (map.containsKey("id"))
            deleteQuery += map.get("id");
        else if (map.containsKey("email"))
            deleteQuery += map.get("email");
        else
//            throw new IllegalStateException("pk not found");
            return null;
        return String.format(deleteQuery, entityName);
    }

    private String prepareGetOneQuery(Map<String, String> map) {
        String getOneQuery = "select * from %s where \"id\" = ";
        if (map.containsKey("id"))
            getOneQuery += map.get("id");
        else if (map.containsKey("email"))
            getOneQuery = getOneQuery.replace("\"id\"", "\"email\"") + "'" + map.get("email") + "'";
        else
//            throw new IllegalStateException("pk not found");
            return null;
        return String.format(getOneQuery, entityName);
    }

    private String prepareGetAllQuery() {
        String getAllQuery = "select * from %s";
        return String.format(getAllQuery, entityName);
    }
}
