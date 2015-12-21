package security.passwords;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * Created by hossein on 12/21/15.
 */
public class BasicPasswordEncryptor implements PasswordEncryptor {

    private BasicTextEncryptor textEncryptor;

    public BasicPasswordEncryptor() {
        textEncryptor = new BasicTextEncryptor();
    }


    @Override
    public String getEncrypted(String password) {
        return textEncryptor.encrypt(password);
    }

    @Override
    public String getDecrypted(String encrypted) {
        return textEncryptor.decrypt(encrypted);
    }
}
