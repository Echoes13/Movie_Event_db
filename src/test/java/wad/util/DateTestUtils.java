package wad.util;

import java.sql.Timestamp;
import java.util.Date;

public class DateTestUtils {


    public static Date getRandomDate() {
        long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
        long end = Timestamp.valueOf("2018-01-01 00:00:00").getTime();
        long diff = end - offset + 1;
        return new Timestamp(offset + (long)(Math.random() * diff));
    }
}