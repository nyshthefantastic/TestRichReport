/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.general;

import common.dbconnct;
import common.helperFunctions;
import common.message;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Achala Kavinda
 */
public class dashboardMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    Date date = null;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<String> ab = new ArrayList<String>();
     ArrayList<String> listm = new ArrayList<String>();
    ArrayList<String> abm = new ArrayList<String>();
    helperFunctions hf;
    String[] splitted = new String[3];
    message mess;
    int x;

    public dashboardMiddle() {

        con = dbconnct.connect();
        mess=new message();
    }
    public void afterThreeMonth(){
    date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int Year = localDate.getYear();
        int Monthh = localDate.getMonthValue();
        int dayy = localDate.getDayOfMonth();
         listm = getEligiblex(Year,Monthh,dayy);
        for(int x=0;x<listm.size();x++){
        addToTableX(listm.get(x));
        }
    
    
    }
    public void afterSixMonth() {
        date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int Year = localDate.getYear();
        int Monthh = localDate.getMonthValue();
        int dayy = localDate.getDayOfMonth();
        list = getEligible(Year,Monthh,dayy);
        for(int x=0;x<list.size();x++){
        addToTable(list.get(x));
        }
    }
    private void addToTableX(String lx){
    
     try {
                String q = "UPDATE employee SET bSalary ='" + "18000.00" + "' WHERE epfNo= '" + lx + "'";

                pst = con.prepareStatement(q);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }


    
    
    
    
    
    
    }
    
    
    
    
    
private void addToTable(String lx){

 try {
                String q = "UPDATE employee SET permanant ='" + "true" + "' WHERE epfNo= '" + lx + "'";

                pst = con.prepareStatement(q);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }




}

  private ArrayList<String> getEligiblex(int Year,int Monthh,int dayy) {
        hf = new helperFunctions();
        String sql = "select joinDate,epfNo,fName,lName from employee where permanent='" + "false" + "' ";
        rs = hf.getQuery(sql);
        try {
            while (rs.next()) {
                String joinDate = rs.getString("joinDate");
                splitted = joinDate.split("-");
                String yy = splitted[0];
                String ym = splitted[1];

                String yd = splitted[2];

                splitted[0] = "";
                splitted[1] = "";
                splitted[2] = "";
                x=Integer.parseInt(ym);
                if(x>9){
                    x=x-9;
                if((x<=Monthh)&&(Integer.parseInt(yy)<=Year+1)){
                 
                    boolean bool=mess.alertBox("Are you Sure you want to Make employee "+rs.getString("fName")+" "+rs.getString("lName")+" "+rs.getString("epfNo")+" salary increase to Rs.18000.00");
                    if(bool==true){
                       abm.add(rs.getString("epfNo"));
                    
                    }
                
                }
                }
                  else{
                    if(x<=Monthh+3){
                      boolean bool=mess.alertBox("Are you Sure you want to Make employee "+rs.getString("fName")+" "+rs.getString("lName")+" "+rs.getString("epfNo")+" salary increase to Rs.18000.00");
                    if(bool==true){
                       abm.add(rs.getString("epfNo"));
                    
                    }
                    
                    
                    
                    }
                    
                  
                
                    
                    
                    
                    }

            }
          
        } catch (Exception e) {
            System.out.println(e);
        }
        return abm;
    }


    private ArrayList<String> getEligible(int Year,int Monthh,int dayy) {
        hf = new helperFunctions();
        String sql = "select joinDate,epfNo,fName,lName from employee where permanent='" + "false" + "' ";
        rs = hf.getQuery(sql);
        try {
            while (rs.next()) {
                String joinDate = rs.getString("joinDate");
                splitted = joinDate.split("-");
                String yy = splitted[0];
                String ym = splitted[1];

                String yd = splitted[2];

                splitted[0] = "";
                splitted[1] = "";
                splitted[2] = "";
                x=Integer.parseInt(ym);
                if(x>6){
                    x=x-6;
                if((x<=Monthh)&&(Integer.parseInt(yy)<=Year+1)){
                 
                    boolean bool=mess.alertBox("Are you Sure you want to Make employee "+rs.getString("fName")+" "+rs.getString("lName")+" "+rs.getString("epfNo")+" Permanant");
                    if(bool==true){
                       ab.add(rs.getString("epfNo"));
                    
                    }
                
                }
                }
                  else{
                    if(x<=Monthh+6){
                      boolean bool=mess.alertBox("Are you Sure you want to Make employee "+rs.getString("fName")+" "+rs.getString("lName")+" "+rs.getString("epfNo")+" Permanant");
                    if(bool==true){
                       ab.add(rs.getString("epfNo"));
                    
                    }
                    
                    
                    
                    }
                    
                  
                
                    
                    
                    
                    }

            }
          
        } catch (Exception e) {
            System.out.println(e);
        }
        return ab;
    }
}
