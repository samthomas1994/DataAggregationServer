package uk.ac.bath.Security;

<<<<<<< HEAD
import java.util.regex.Matcher;
import java.util.regex.Pattern;

=======
>>>>>>> 3d795b12caa70a6565a8a6715888accb7a676143
/**
 * Created by Sam on 25/02/2016.
 */
public class ValidInput {
<<<<<<< HEAD

    public boolean validateName(String txt) {
        String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();
    }

    public boolean validateUsername(String txt) {
        String regx = "^[\\w.-]+$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();
    }
=======
>>>>>>> 3d795b12caa70a6565a8a6715888accb7a676143
}
