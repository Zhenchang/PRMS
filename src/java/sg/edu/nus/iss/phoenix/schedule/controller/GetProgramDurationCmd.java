/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.controller;

import at.nocturne.api.Action;
import at.nocturne.api.Perform;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.delegate.ReviewSelectProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.user.delegate.ManageUserDelegate;

/**
 *
 * @author zz
 */
@Action("getProgramDuration")
public class GetProgramDurationCmd implements Perform {

    @Override
    public String perform(String string, HttpServletRequest hsr, HttpServletResponse hsr1) throws IOException, ServletException {
        String name = hsr.getParameter("programName");
        ReviewSelectProgramDelegate programDelegate = new ReviewSelectProgramDelegate();
        List<RadioProgram> programs = programDelegate.reviewSelectRadioProgram();
        for(RadioProgram rp : programs) {
            if(rp.getName().equals(name)) {
                hsr1.setHeader("duration", rp.getTypicalDuration().toString());
                return "";
            }
        }
        return "";
    }

}
