package com.jack.rms.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lucky on 15/04/25.
 */
public class DateUtils {

    /**
     * 转换时间
     *
     * @param format
     * @param date
     * @return
     */
    public static String parseDate2Str(String format, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static Date parseStr2Date(String format, String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }

}
