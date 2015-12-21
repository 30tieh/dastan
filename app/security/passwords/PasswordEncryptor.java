package security.passwords;

/**
 * Created by hossein on 12/21/15.
 */
public interface PasswordEncryptor {

    public String getEncrypted(String password);
    public String getDecrypted(String encrypted);
}
