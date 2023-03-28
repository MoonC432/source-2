package au.ntcrs6.trackiffy.driver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateSerializer {

    private Date parsedDate;

    DateSerializer(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        parsedDate = dateFormat.parse(date);

    }

    public Date getParsedDate() {
        return parsedDate;
    }
}
