/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.reports;

import common.dbconnct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author U Computers
 */
public class reportMiddle {
     Connection conn = null;
    PreparedStatement pst;
    Statement st;
    ResultSet rs = null;

    public reportMiddle() {
        conn = dbconnct.connect();
    }
    
}
