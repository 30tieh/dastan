package database.users.hibernate;

/**
 * Created by hossein on 12/21/15.
 */
public class HibernateQueries {

    private static String entityName = "User";
    public static String getSelectFromQuery(String attributeName, String key) {
        return "FROM " + entityName + " X WHERE X." + attributeName + " = " + "'" + key + "'";
    }
}
