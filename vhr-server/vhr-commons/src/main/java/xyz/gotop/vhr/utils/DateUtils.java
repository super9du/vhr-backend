package xyz.gotop.vhr.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Demo DataUtil
 *
 * @author Wolf-Liu
 * @date 2020/2/12 11:59
 */
public class DateUtils {

    private DateUtils() {
    }


    //---------- Static Properties -----------

    /**
     * 格式化年月日
     */
    public static final ThreadLocal<SimpleDateFormat> DATE_FORMAT =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    /**
     * 使用 `/` 格式化年月日
     */
    public static final ThreadLocal<SimpleDateFormat> DATE_FORMAT_SLASH =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy/MM/dd"));

    /**
     * 格式化年月日时分秒
     */
    public static final ThreadLocal<SimpleDateFormat> DATE_TIME_FORMAT =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


    //------------ Static Methods -----------------

    public static String dateFormat(Date date) {
        if (date == null)
            return null;
        return DATE_FORMAT.get().format(date);
    }

    public static String dateTimeFormat(Date date) {
        if (date == null)
            return null;
        return DATE_TIME_FORMAT.get().format(date);
    }

    public static Date parse(String date) throws ParseException {
        return DATE_FORMAT.get().parse(date);
    }
}
