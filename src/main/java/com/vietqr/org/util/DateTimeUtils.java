package com.vietqr.org.util;

import org.apache.log4j.Logger;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final String DateTimeYYYYMM = "yyyyMM";
    private static final String DateTimeYYYY_MM = "MM-yyyy";
    private static final String DateTimeYYYYMMdd = "yyyyMMdd";
    private static final String DateTimeYYYY_MM_dd = "dd-MM-yyyy";
    private static final Logger logger = Logger.getLogger(DateTimeUtils.class);

    public static String parseToStringFormat(String time) {
        String result = "";
        SimpleDateFormat inputFormat;
        SimpleDateFormat outputFormat = new SimpleDateFormat();
        try {
            Date date = null;
            switch (time.length()) {
                case 6:
                    inputFormat = new SimpleDateFormat(DateTimeYYYYMM);
                    outputFormat = new SimpleDateFormat(DateTimeYYYY_MM);
                    date = inputFormat.parse(time);
                    break;
                case 8:
                    inputFormat = new SimpleDateFormat(DateTimeYYYYMMdd);
                    outputFormat = new SimpleDateFormat(DateTimeYYYY_MM_dd);
                    date = inputFormat.parse(time);
                    break;
                default:
                    result = "";
                    break;
            }
            if (date != null) {
                result = outputFormat.format(date);
            }
        } catch (Exception e) {
            result = "";
        }
        return result;
    }
}
