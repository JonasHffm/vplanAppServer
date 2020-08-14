package vplan;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date {

    java.util.Date date = new java.util.Date();
    Calendar calendar = new GregorianCalendar();

    public Date(){
        calendar.setTime(date);
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public int getMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }

    public int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

}
