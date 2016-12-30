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

/**
 *
 * @author Nuwan-PC
 */
public class leaveMiddle {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public leaveMiddle(){
        con = dbconnct.connect();
    }
    
    //fill leaves
    public  boolean fillleave(String eid) throws SQLException{
            
        String allLeaves = "select count(id) from leave where  id='"+eid+"' ";
        String sickLeave = "select count(id) from leave where type='sick' and id='"+eid+"' ";
        String annualLeave = "select count(id) from leave where type='annual' id='"+eid+"'  ";
        String casualLeave = "select count(id) from leave where type='casual' id='"+eid+"'  ";
        
        pst = con.prepareStatement(allLeaves);
        ResultSet X = pst.executeQuery();
        
                
        
        
        
      /*  pst = con.prepareStatement(sickLeave);
        pst.executeQuery();
        pst = con.prepareStatement(annualLeave);
        pst.executeQuery();
        pst = con.prepareStatement(casualLeave);
        pst.executeQuery();
        */
        
        //pst.executeQuery(allLeaves);
        
        return true;
    }
}
