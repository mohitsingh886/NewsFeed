package io.github.vishalecho.android.assignment.newsfeed.util;

import android.util.Log;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateTimeUtil {
    private static final String TAG = DateTimeUtil.class.getName();
    private static final String EN_DATE_FORMAT = "dd-MM-yyyy";
    private static final String BACK_END_SERVER_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:MM:ssZ";

    public static Date getFormattedServerDate(String serverDate) {
        try {
            return new SimpleDateFormat(BACK_END_SERVER_DATE_TIME_FORMAT, Locale.ENGLISH).parse(serverDate);
        } catch (Exception e) {
            return new Date();
        }
    }

    public static String convertDateToString(Date date) {
        String dateString = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(EN_DATE_FORMAT, Locale.ENGLISH);
        try{
            dateString = dateFormat.format(date);
        }catch (Exception ex ){
            return "";
        }
        return dateString;
    }
}
