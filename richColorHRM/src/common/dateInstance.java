/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import common.queryHelper;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author Achala Kavinda
 */
public class dateInstance {
    DateFormat dateFormat;
    Date date;
    Calendar cal = Calendar.getInstance();
    queryHelper queryHelper;

    public dateInstance() {
        queryHelper = new queryHelper();
        dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        date = new Date();
    }
    
    //get today with current time
    
    public String getTodayDateTime(){
        return dateFormat.format(this.date);
    }
    
    public Date dateBeforToday(){
        Date cuDate = new  Date();
        return cuDate;
    }
    
    // string to date
    
    public Date stringToDateFormat(String date) throws ParseException{
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date startDate = null;
        try {
            startDate = df.parse(date);
        } catch (Exception e) {
            
        }
        return  startDate;
    }
    
    //get today
    
    public String getToday(){
        String [] part = dateFormat.format(this.date).split(" ");
        return part[0];
    }
    
    //get yeasterday 
    
    public String getYesterDay(){
         DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
         Date date = new Date(System.currentTimeMillis() - (24 * 60 *60 * 1000L));
         String X = df.format(date);         
         return X; 
    }
    
    
    public static long minuteDifference(String dateStop ,String dateStart){
        long minutes =0;
            // Custom date format
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  

            Date d1 = null;
            Date d2 = null;
            try {
                d1 = format.parse(dateStart);
                d2 = format.parse(dateStop);
            } 
            catch (ParseException e) {
            e.printStackTrace();
            }     
            long diff = d2.getTime()-d1.getTime();
            minutes =(long) (diff / (60 * 1000)) ;   
            return minutes;
    }
    
    // get all holidays in system
    
    public ResultSet getHolidays(String sql){
        ResultSet rs = null;
        if(sql==null){
            sql="SELECT * FROM `holidays` ORDER BY date";
        }
        try {
            rs=queryHelper.getResultSet(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;    
    }
    
    
}