/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import common.dbconnct;

/**
 *
 * @author Achala Kavinda
 */
public class queryHelper {
    Connection conn =common.dbconnct.connect();
    
// insert query
     public boolean insertQuery(String sql){
        boolean status=false;
        try{
            Statement st = conn.createStatement();
            int val=st.executeUpdate(sql);
            if(val==1){
                status=true;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return status;
    
    }
     
 //get result set
    public  ResultSet getResultSet(String sql){
        ResultSet rs=null;
        try{
            
         Statement st=conn.createStatement();
            rs =st.executeQuery(sql);            
        }catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
}
