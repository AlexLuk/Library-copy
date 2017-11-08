package org.library.misc;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

    /**
     * Constructs full name
     *
     * @return - full name
     */
    public static String getFullName(String firstName, String lastName, String patronimic) {
        StringBuilder name = new StringBuilder();
        name.append(firstName).append(" ");
        if (patronimic != null && !patronimic.equals("")) {
            name.append(patronimic).append(" ");
        }
        name.append(lastName).append(" ");
        return name.toString();
    }

    /**
     * Converts date to LocalDate object
     *
     * @return - converted date
     */
    public static LocalDate convertLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
