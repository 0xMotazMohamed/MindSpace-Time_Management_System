package org.example.data.dto.features;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Reminder {
    private Timer timer;
    private String message;
    public Reminder(){
        timer = new Timer();
    }
    //====Getting the Date from the User====//
    private Date getUserDate(){
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date userDate = null;
        while (userDate==null){
            String dateInput = JOptionPane.showInputDialog(null,"Enter your Desired Time for the Habit (dd/MM/yyyy HH:mm):","Habit Time",JOptionPane.QUESTION_MESSAGE);
            if(dateInput==null){
                return null;
            }
            try {
                userDate = date.parse(dateInput);
                if(userDate.before(new Date())){
                    JOptionPane.showMessageDialog(null,"This was the past my friend!\nEnter a Future Date","Invalid Date",JOptionPane.ERROR_MESSAGE);
                    userDate = null;
                }
            }catch(ParseException e){
                JOptionPane.showMessageDialog(null,"Invalid Date Format!\nPlease use dd/MM/yyyy HH:mm","Wrong Format",JOptionPane.ERROR_MESSAGE);
            }
        }
        return userDate;
    }
    //=====Setting the REMINDER=====//
    public void createReminder(){
        message = JOptionPane.showInputDialog(null,"Enter a Message to Remind You:","Habit Tracker",JOptionPane.QUESTION_MESSAGE);
        if(message==null){return;}

        Date startDate = getUserDate();
        if(startDate==null){return;}

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null,message,"Habit Tracker",JOptionPane.INFORMATION_MESSAGE);
            }
        };
        //======Scheduling the Task======//
        timer.scheduleAtFixedRate(timerTask, startDate,24*60*60*1000);
        JOptionPane.showMessageDialog(null,"You are All Set","Confimation Message",JOptionPane.INFORMATION_MESSAGE);
    }
    public void stopReminder(){
        if(timer != null){
            timer.cancel();
            timer = new Timer();
            JOptionPane.showMessageDialog(null,"You Broke the Wheel","Habit Cancelling",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
