package uk.ac.bath.Security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sam on 25/02/2016.
 */
public class ValidInput {

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
}
