package mlab.order.management.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeConvertUtil {
    public static final String DB_DATE_FORMAT = "yyyy-MM-dd";
    public static final String API_DATE_FORMAT = "MM-dd-yyyy";


    public static String convertApiToDbDate(String issueDate) {
        LocalDate localDate = LocalDate.parse(issueDate,
                DateTimeFormatter.ofPattern(API_DATE_FORMAT));
        return localDate.format(DateTimeFormatter.ofPattern(DB_DATE_FORMAT));
    }

    public static String convertDbToApiDate(String issueDate) {
        LocalDate localDate = LocalDate.parse(issueDate,
                DateTimeFormatter.ofPattern(DB_DATE_FORMAT));
        return localDate.format(DateTimeFormatter.ofPattern(API_DATE_FORMAT));
    }
}
