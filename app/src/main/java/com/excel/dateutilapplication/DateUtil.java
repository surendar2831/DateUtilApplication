package com.excel.dateutilapplication;

import android.support.annotation.NonNull;

import com.stfalcon.chatkit.utils.DateFormatter;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static com.stfalcon.chatkit.utils.DateFormatter.isSameDay;

public class DateUtil {

    //check this

    public JSONObject getOnlyDateFromTimeStamp(@NonNull String dateTimeStamp) {
        Date date = null;
        String dayString = "", day = "", monthName = "", month = "", year = "";
        JSONObject dateDetailsObj = new JSONObject();
        try {
            String strCurrentDate = dateTimeStamp;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            date = format.parse(strCurrentDate);
            SimpleDateFormat formatDayString = new SimpleDateFormat("EEE");
            dayString = formatDayString.format(date);
            SimpleDateFormat formatDay = new SimpleDateFormat("dd");
            day = formatDay.format(date);
            SimpleDateFormat monthString = new SimpleDateFormat("MMM");
            monthName = monthString.format(date);
            SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
            month = formatMonth.format(date);
            SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
            year = formatYear.format(date);
            dateDetailsObj.put("dayString", dayString);
            dateDetailsObj.put("date", day);
            dateDetailsObj.put("monthName", monthName);
            dateDetailsObj.put("month", month);
            dateDetailsObj.put("year", year);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateDetailsObj;
    }

    public JSONObject getDateFromGMTTimeStamp(@NonNull String dateTimeStamp) {
        Date date = null;
        String dayString = "", day = "", monthName = "", month = "", year = "";
        JSONObject dateDetailsObj = new JSONObject();
        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            date = format.parse(dateTimeStamp);
            SimpleDateFormat formatDayString = new SimpleDateFormat("EEE");
            dayString = formatDayString.format(date);
            SimpleDateFormat formatDay = new SimpleDateFormat("dd");
            day = formatDay.format(date);
            SimpleDateFormat monthString = new SimpleDateFormat("MMM");
            monthName = monthString.format(date);
            SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
            month = formatMonth.format(date);
            SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
            year = formatYear.format(date);
            dateDetailsObj.put("dayString", dayString);
            dateDetailsObj.put("date", day);
            dateDetailsObj.put("monthName", monthName);
            dateDetailsObj.put("month", month);
            dateDetailsObj.put("year", year);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateDetailsObj;
    }

    public String getDateAndTimeFromTimeStamp(@NonNull String dateTimeStamp, boolean isOnlyTime) {
        String strCurrentDate = dateTimeStamp;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        String time = "";
        try {
            date = format.parse(strCurrentDate);
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
            time = formatTime.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (isOnlyTime) {
            return time;
        } else {
            return date.toString();
        }
    }

    public String checkTodayOrTomorrow(String bookedDateTime) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date1 = df.parse(bookedDateTime);

            if (DateFormatter.isToday(date1)) {
                return "Today";
            } else if (isTommorrow(date1)) {
                return "Tomorrow";
            } else if (isYesterday(date1)) {
                return "Yesterday";
            } else {
                return bookedDateTime;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isTommorrow(Date date) {
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, +1);
        return isSameDay(date, yesterday.getTime());
    }

    public static boolean isYesterday(Date date) {
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        return isSameDay(date, yesterday.getTime());
    }
}
