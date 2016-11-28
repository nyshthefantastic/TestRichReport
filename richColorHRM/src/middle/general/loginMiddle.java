/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.general;

import client.general.dashboardClient;
import common.dbconnct;
import common.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Nuwan-PC
 */
public class loginMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public loginMiddle() {
        con = dbconnct.connect();
    }

    public boolean verifyLogin(String userName, String passWord) {
        try {
            String load = "SELECT userName,password FROM login";

            pst = con.prepareStatement(load);
            rs = pst.executeQuery();
            while (rs.next()) {
                String uNameDB = rs.getString("userName");
                String pWordDB = rs.getString("password");

                if (uNameDB.equals(userName) && pWordDB.equals(passWord)) {

                    return true;

                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        message m = new message();
        m.messageBox("INCORRECT LOGIN DETAILS.PLEASE TRY AGAIN !");

        return false;
    }

}
