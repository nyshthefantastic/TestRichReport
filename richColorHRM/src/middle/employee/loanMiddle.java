/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.employee;

import common.dbconnct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author U Computers
 */
public class loanMiddle {
     Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
     String empNumber,remainLoan;
     double totRemainLoan=0;

    public loanMiddle() {
        con = dbconnct.connect();
    }
    
    public double checkLoan(String empNo,String totLoan,String loanTerm,double perMonth ){
       
         try {
            String load = "SELECT * FROM employeeloan WHERE empNo='" + empNo + "'";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                
                 empNumber = rs.getString("empNo");
                 if(empNumber.equals(empNo)){
                   remainLoan=rs.getString("remainingAmount");
                   totRemainLoan=Double.parseDouble(remainLoan)+totRemainLoan;
                 }
                   return totRemainLoan;

            }

        } catch (Exception e) {
            System.out.println(e);
        }
           return 0;
    
    }
    
    public void addLoan(String empNo,String totLoan,String loanTerm,double perMonth ){
         try {
                String q = "INSERT INTO employeeloan(empNo,totalLoan,noOfTerms,monthlyAmount) VALUES ('" + empNo + "','" + totLoan + "','" + loanTerm + "','" + perMonth + "')";

                pst = con.prepareStatement(q);
                pst.execute();

            } catch (Exception e) {
                System.out.println(e);
            }
    
    
    }
    
}
