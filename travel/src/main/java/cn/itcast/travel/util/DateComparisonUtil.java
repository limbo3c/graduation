package cn.itcast.travel.util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
public class DateComparisonUtil {
    public static int getDays(String date1,String date2){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse(date1);
            endDate = format.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int days = (int) ((endDate.getTime() - startDate.getTime()) / (1000*3600*24));
        return days;

    }
}
