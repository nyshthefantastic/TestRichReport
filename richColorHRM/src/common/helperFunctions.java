/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nuwan-PC
 */
public  class helperFunctions {
    //----------Attendance--------------------------------------------
      public static String formatDate(Date date){
          DateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
          String dateF = oDateFormat.format(date);
          return dateF;
      }
      
      public static Date formatTime(String time){
          DateFormat oTimeFormat = new SimpleDateFormat("HH:mm");
          Date dateF = null;
          try {
              dateF = oTimeFormat.parse(time);
          } catch (ParseException ex) {
              Logger.getLogger(helperFunctions.class.getName()).log(Level.SEVERE, null, ex);
          }
          return dateF;
      }
      
      public long workingHours(String t1, String t2){
          long X = formatTime(t2).getTime()-formatTime(t1).getTime();
          return X;
          
      }
      
      public boolean compareInOut(String t1,String t2){
          return workingHours(t1, t2)>0;
      }
      
    //validate attendance
     // public boolean validateAttendance(String id, String name, Date dateT, )
}
