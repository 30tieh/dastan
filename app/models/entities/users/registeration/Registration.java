package models.entities.users.registeration;

import database.users.UserManager;
import models.entities.users.User;
import security.passwords.BasicPasswordEncryptor;
import security.passwords.PasswordEncryptor;
import java.util.List;

/**
 * Created by hossein on 12/21/15.
 */
public class Registration {

    private static PasswordEncryptor encryptor = new BasicPasswordEncryptor();

    public static User signUp(String name, String username, String password) throws RegistrationException {
        if (!hasValidNameFormat(name)) {
            throw new InvalidNameFormatException();
        } else if (!hasValidUsernameFormat(username)) {
            throw new InvalidUsernameFormatException();
        } else if (!hasValidPasswordFormat(password)) {
            throw new InvalidPasswordFormatException();
        }
        List<User> users = getUsersByUsername(username);
        if (users.size() == 0) {
            encryptor = new BasicPasswordEncryptor();
            User registeringUser = new User(name, username, encryptor.getEncrypted(password));
            UserManager.insert(registeringUser);
            return registeringUser;
        }
        return null;
    }

    public static User signIn(String username, String password) throws RegistrationException {
        if (!hasValidUsernameFormat(username)) {
            throw new InvalidUsernameFormatException();
        } else if (!hasValidPasswordFormat(password)) {
            throw new InvalidPasswordFormatException();
        }
        List<User> users = getUsersByUsername(username);
        encryptor = new BasicPasswordEncryptor();
        for (User user : users) {
            if (encryptor.getDecrypted(user.getPassword()).equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static List<User> getUsersByUsername(String username) {
        List<User> foundUsers = UserManager.getUsersByUsername(username);
        return foundUsers;
    }

    public static boolean hasValidUsernameFormat(String username) {
        return true;
    }

    public static boolean hasValidNameFormat(String name) {
        return true;
    }

    public static boolean hasValidPasswordFormat(String password) {
        return true;
    }
}
