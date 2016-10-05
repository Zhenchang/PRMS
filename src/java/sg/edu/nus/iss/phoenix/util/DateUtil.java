/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Liu Zhenchang
 */
public class DateUtil {

    /**
     *
     * @param date "mm/dd/yyyy"
     * @return week number
     */
    public static int getWeekByDate(String date) {
        String[] strs = date.split("-");
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]) - 1, Integer.parseInt(strs[2]));
        int i = cal.get(Calendar.DAY_OF_YEAR);
        return (int) Math.ceil(i / 7.0);
    }

    public static String[] getDateInWeek(int week, int year) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int day = (week - 1) * 7 + 1;
        cal.set(Calendar.YEAR, year);
        String[] strs = new String[7];
        for (int i = 0; i < 7; i++) {
            cal.set(Calendar.DAY_OF_YEAR, day);
            strs[i] = df.format(cal.getTime());
            day++;
        }
        return strs;
    }

    public static void main(String[] args) {
        DateUtil.getWeekByDate("2016-01-15");
        DateUtil.getDateInWeek(3, 2016);
    }
}
