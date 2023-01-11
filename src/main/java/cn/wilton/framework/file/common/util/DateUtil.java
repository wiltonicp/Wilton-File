package cn.wilton.framework.file.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * 基于 java8 的时间工具类
 * @author Ranger
 * @since 2021/3/15
 * @email wilton.icp@gmail.com
 */
public class DateUtil {

    public static final String FULL_TIME_PATTERN = "yyyyMMddHHmmss";

    public static final String FULLS_TIME_PATTERN = "yyyyMMddhhmmssS";

    public static final String FULL_TIME_SPLIT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String CST_TIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static final long nd = 1000 * 24 * 60 * 60;
    private static final long nh = 1000 * 60 * 60;
    private static final long nm = 1000 * 60;

    public static String formatFullTime(LocalDateTime localDateTime) {
        return formatFullTime(localDateTime, FULL_TIME_PATTERN);
    }

    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    public static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatType, Locale.CHINA);
        return simpleDateFormat.format(date);
    }

    public static String formatCSTTime(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CST_TIME_PATTERN, Locale.US);
        Date usDate = simpleDateFormat.parse(date);
        return DateUtil.getDateFormat(usDate, format);
    }

    public static String formatInstant(Instant instant, String format) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 计算两个时间段时间差，精确到秒
     *
     * @param startTime 2019-04-10 17:16:11
     * @param endTime   2019-04-10 17:28:17
     * @return
     */
    public static String computationTime(String startTime, String endTime) {
        String str = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date endDate = sdf.parse(endTime);
            Date strDate = sdf.parse(startTime);
            long diff = endDate.getTime() - strDate.getTime();
            long day = diff / nd;
            long hour = diff % nd / nh;
            long min = diff % nd % nh / nm;
            long sec = diff % nd % nh % nm / 1000;//秒
            if (day == 0) {
                str = hour + "小时" + min + "分钟";
            } else if (hour == 0) {
                str = min + "分钟";
            } else {
                str = day + "天" + hour + "小时" + min + "分钟";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
