package br.ufscar.dc.dsw.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Util {
    public static Timestamp convertStringToTimestamp(String strDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(strDate);
        
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            System.out.println("Exception: " + e);
            return null;
        }
    }

    public static String convertTimestampToString(Timestamp date) {
        LocalDateTime localDate = date.toLocalDateTime();
        return localDate.getDayOfMonth() + "/" + localDate.getMonth().getValue() + "/" + localDate.getYear();
    }
}
