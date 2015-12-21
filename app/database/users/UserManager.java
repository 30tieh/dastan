package database.users;

import database.DBManager;
import database.users.hibernate.HibernateQueries;
import models.entities.users.User;
import java.util.List;

/**
 * Created by hossein on 11/22/15.
 */
public class UserManager {

    private static String entityName = "User";

    public static void insert(User user) {
        DBManager.insert(user);
    }

    public static List<User> getUsersByUsername(String username) {
        //String hql = "FROM " + entityName + " U WHERE U.username = " + "'" + username + "'";
        String attribute = "username";
        String query = HibernateQueries.getSelectFromQuery(attribute, username);
        List<User> users = DBManager.getQueryResult(query);
        return users;
    }
}

