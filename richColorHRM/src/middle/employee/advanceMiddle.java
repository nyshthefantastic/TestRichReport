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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nuwan-PC
 */
public class advanceMiddle {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
   
     
    //Constructor
    public advanceMiddle(){
        con = dbconnct.connect();
    }
    
    //Add advance
    public void addAdavance(String empNo , String amount, String date , String maxAmt){
        
        //if(validateAdavance(empNo, amount, date, maxAmt )){
            if(true){
            
            
 
                try {
                     // int eid = Integer.parseInt(empNo);
                      //Double amountX = Double.valueOf(amount);
                  
                 //  sqlN = "INSERT INTO advance(amount , date, employeeId ) VALUES ('"+amountX"',  '"+date+"', '"+eid+"')" ;
                    String sql = "insert into advance( type, amount , date, employeeId ) values ('general',  '"+amount+"',  '"+date+"', '"+empNo+"')";
                    System.out.println(sql);
                    pst = con.prepareStatement(sql);
                    pst.execute();

                    
                    
                  //  System.out.println("INSERT INTO advance( type, amount , date, employeeId , empNo) VALUES ('general',  '"+amountX+"',  '"+date+"',  '"+maxAmt+"',  '"+eid+"')");
                } catch (SQLException ex) {
                    System.out.println("error SQL");
                }
                
        }
    }
    
    public void updateAdvance( String amount, String date){
        
    }
    
    public boolean validateAdavance(String empNo , String amount, String date , String maxAmt) {
        if((empNo.isEmpty() || amount.isEmpty() || date.isEmpty())==false){
            
            Double amt = Double.valueOf(amount);
            Double mAmt = Double.valueOf(maxAmt);
            
            if((amt<=mAmt)){
                return true;
            }
            
        }
        return false;
    }
    
    public boolean executeQuery(String query){
        try{
            
            pst = con.prepareStatement(query);
            pst.execute(query);
            return true;
        }
        catch(Exception e){
            System.out.println("error in execution");
            return false;
        }
    }
    
    public String searchQuery(String X ,String eid , String table){
             String out = null;
       try {
           String queryy="SELECT "+X+" FROM "+table+" WHERE id =" +eid+ " ";
           System.out.println(queryy);
           
            pst = con.prepareStatement(queryy);
            rs = pst.executeQuery();
            if (rs.next()) {
                out=rs.getString(X);
              return out;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
       return out;
    }
}
