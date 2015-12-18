package database.users;

import database.DBManager;
import models.entities.User;
import java.util.List;

/**
 * Created by hossein on 11/22/15.
 */
public class UserManager {

    private static String entityName = "User";

    public static void insert(User user) {
        DBManager.insert(user);
    }

    public static User getUserByUsername(String username) {
        String hql = "FROM " + entityName + " U WHERE U.username = " + "'" + username + "'";
        List<User> users = DBManager.getQueryResult(hql);
        if (users.size() == 0) {
            return null;
        } else if (users.size() > 1) {
            throw new AssertionError("The username field in Users has to be uniqe");
        }
        return users.get(0);
    }
}

