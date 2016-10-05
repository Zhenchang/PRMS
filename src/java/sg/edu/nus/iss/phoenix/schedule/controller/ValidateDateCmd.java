/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.util.DateUtil;

/**
 *
 * @author zz
 */
@Action("validateDate")
public class ValidateDateCmd implements Perform {

    @Override
    public String perform(String string, HttpServletRequest hsr, HttpServletResponse hsr1) throws IOException, ServletException {
        int week = DateUtil.getWeekByDate(hsr.getParameter("date"));
        String[] strs = DateUtil.getDateInWeek(week, Integer.parseInt(hsr.getParameter("date").split("-")[0]));
        String dates = "";
        for(String str: strs) {
            dates = dates + str + ";";
        }
        if (week == 53) {
            hsr1.setHeader("isValid", "false");
        }
        hsr1.setHeader("isValid", "true");
        hsr1.setHeader("week", String.valueOf(week));
        hsr1.setHeader("dates", dates);
        return String.valueOf(DateUtil.getWeekByDate(hsr.getParameter("date")));
    }

}
