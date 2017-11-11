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
    public static String getFullName(String lastName, String firstName, String patronymic) {
        StringBuilder name = new StringBuilder();
        name.append(lastName).append(" ");
        name.append(firstName).append(" ");
        if (patronymic != null && !patronymic.equals("")) {
            name.append(patronymic).append(" ");
        }
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
