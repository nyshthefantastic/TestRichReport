/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.attendance;

import middle.attendance.manualAttendanceMiddle;
import common.dbconnct;
import common.helperFunctions;
import common.validations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nuwan-PC
 */
public class manualAttendanceMiddle {
    
    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    helperFunctions h = null;
    
   public manualAttendanceMiddle() {
         con = dbconnct.connect();
    }
    
    public void manualAttendance(String id, String name , Date date ,String inTime , String outTime) {
       
        System.out.println(id+name+date+inTime+outTime);
       
        
        if(checkEmpty(id, name, date, inTime, outTime)){
          System.out.println("GO");
           //Integer eid = Integer.parseInt(id);
             
           String str = "INSERT INTO timestamp(checkIn, checkOut, date, status, employeeId) VALUES ('"+ inTime +"' , '"+ outTime +"' , '"+ h.formatDate(date)+"' , ' manual ' , '" +id + "')" ;
            try {
                pst = con.prepareStatement(str);
                pst.execute();
                System.out.println("GO");
            } catch (SQLException ex) {
                Logger.getLogger(manualAttendanceMiddle.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             
        }
        else{
            JOptionPane.showMessageDialog(null, "Empty Fields");
         
        }  
    }
    
    public boolean checkEmpty(String id, String name , Date date ,String inTime , String outTime){
        return !(id.isEmpty() || date.toString().isEmpty() || inTime.isEmpty() || outTime.isEmpty());
    }
    
    /*
     public boolean validateTimestampRecord(String id, String name , Date date ,String inTime , String outTime){
            validations v= new validations();
            
            
        }
    */
    
}
