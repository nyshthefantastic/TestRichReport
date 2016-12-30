/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.payroll;
import common.dbconnct;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;
/**
 *
 * @author Achala Kavinda
 */
public class salaryMiddle {

    Connection conn=null;
    Statement st;
    int id=0;
    
    
    
    public salaryMiddle() {
        conn = dbconnct.connect();
    }
    
    
    
    
    public ResultSet getQuery(String sql){    
    try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
