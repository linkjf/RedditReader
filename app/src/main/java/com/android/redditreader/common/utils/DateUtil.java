package com.android.redditreader.common.utils;

import android.text.format.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final int SECONDS_NOW = 10;
    public static final int SECONDS_IN_HALF_MINUTE = 60;
    public static final int SECONDS_IN_MINUTE = 60;
    public static final int MINUTES_IN_HOUR = 60;
    public static final int HOURS_IN_DAY = 24;
    public static final int DAYS_IN_MONTH = 31;
    public static final int DAYS_IN_YEAR = 365;

    public static final String NOW_PREFIX = "Now";
    public static final String SECONDS_PREFIX = "s";
    public static final String MINUTES_PREFIX = "m";
    public static final String HOURS_PREFIX = "h";
    public static final String DAYS_PREFIX = "d";
    public static final String MONTHS_PREFIX = "mo";
    public static final String YEARS_PREFIX = "y";

    public static String getRelativeTimeString(long fromdate) {

        Date date = new Date(fromdate * 1000); // Reddit long are not in millis, this make it so.

        StringBuilder dateStr = new StringBuilder();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar now = Calendar.getInstance();

        int days = daysBetween(calendar.getTime(), now.getTime());
        int minutes = hoursBetween(calendar.getTime(), now.getTime());
        int hours = minutes / MINUTES_IN_HOUR;
        if (days == 0) {

            int second = minuteBetween(calendar.getTime(), now.getTime());
            if (minutes > MINUTES_IN_HOUR) {

                if (hours >= 1 && hours <= HOURS_IN_DAY) {
                    dateStr.append(hours).append(HOURS_PREFIX);
                }

            } else {

                if (second <= SECONDS_NOW) {
                    dateStr.append(NOW_PREFIX);
                } else if (second > SECONDS_IN_HALF_MINUTE && second <= SECONDS_IN_MINUTE) {
                    dateStr.append(second).append(SECONDS_PREFIX);
                } else if (second >= SECONDS_IN_MINUTE && minutes <= MINUTES_IN_HOUR) {
                    dateStr.append(minutes).append(MINUTES_PREFIX);
                }
            }
        } else if (hours > HOURS_IN_DAY && days <= DAYS_IN_MONTH) {
            dateStr.append(days).append(DAYS_PREFIX);
        } else if (days >= DAYS_IN_MONTH && days <= DAYS_IN_YEAR) {
            dateStr.append(monthsBetween(calendar.getTime(), now.getTime())).append(MONTHS_PREFIX);
        } else {
            dateStr.append(yearsBetween(calendar.getTime(), now.getTime())).append(YEARS_PREFIX);
        }

        return dateStr.toString();
    }

    public static int minuteBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / DateUtils.SECOND_IN_MILLIS);
    }

    public static int hoursBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / DateUtils.MINUTE_IN_MILLIS);
    }

    public static int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / DateUtils.DAY_IN_MILLIS);
    }

    public static int monthsBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (DateUtils.DAY_IN_MILLIS * DAYS_IN_MONTH));
    }

    public static int yearsBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (DateUtils.DAY_IN_MILLIS * DAYS_IN_YEAR));
    }

}
