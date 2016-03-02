package uk.ac.bath.Security;

<<<<<<< HEAD
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Sam on 25/02/2016.
 */
public final class Encryption {

    public String encryptPassword(String password) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            md.update(password.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        byte raw[] = md.digest();
        String encryptedPassword = (new BASE64Encoder()).encode(raw);
        return encryptedPassword;
    }
=======
/**
 * Created by Sam on 25/02/2016.
 */
public class Encryption {
>>>>>>> 3d795b12caa70a6565a8a6715888accb7a676143
}
