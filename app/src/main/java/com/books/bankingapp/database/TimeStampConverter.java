package com.books.bankingapp.database;

import android.util.Log;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.TimeZone;

public class TimeStampConverter {
    private static final String TAG = "TimeStampConverter";
    private Calendar calendar=Calendar.getInstance();
//    private static DateFormat df=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
//
//    public static Date fromTimeStamp(String value){
//        if(value!=null){
//            try{
//                TimeZone timeZone=TimeZone.getTimeZone("IST");
//                df.setTimeZone(timeZone);
//                Date d= df.parse(value);
//                return d;
//            }
//            catch(Exception e){
//                Log.d(TAG, "fromTimeStamp: ");
//            }
//
//            return null;
//        }
//        return null;
//    }
//
//    @TypeConverter
//    public static String dateToTimestamp(Date value){
//        TimeZone timeZone=TimeZone.getTimeZone("IST");
//        df.setTimeZone(timeZone);
//        return value==null?null:df.format(value);
//    }
//

    @TypeConverter
    public static Calendar toCalendar(Long l){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(l);
        return calendar;
    }

    @TypeConverter
    public static String fromCalendar(Calendar c){
        return c==null?null:String.valueOf(c.getTimeInMillis());
    }

}
