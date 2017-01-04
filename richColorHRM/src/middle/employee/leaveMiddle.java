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
    private double tot = 0;
    Double[] san = new Double[4];

    public leaveMiddle() {
        con = dbconnct.connect();
    }

    public Double[] setLeaves(String empId, int Year) {

        try {
            String sql = "select SUM(noOfDays) AS noOfDays,type from leavez  WHERE employeeId='" + Integer.parseInt(empId) + "' AND yearr='" + String.valueOf(Year) + "' GROUP BY type ";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                String typee = rs.getString("type");
                System.out.println(typee);
                String numdays = rs.getString("noOfDays");
                tot = tot + Double.parseDouble(numdays);
                if (typee.equals("sick")) {

                    san[0] = Double.parseDouble(numdays);
                } else if (typee.equals("annual")) {

                    san[1] = Double.parseDouble(numdays);
                } else {

                    san[2] = Double.parseDouble(numdays);
                }
                numdays = "";
                san[0] = 0.0;
                san[1] = 0.0;
                san[2] = 0.0;

            }

            san[3] = tot;
            tot = 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return san;

    }

    //fill leaves
    public boolean fillleave(String eid) throws SQLException {

        String allLeaves = "select count(id) from leave where  id='" + eid + "' ";
        String sickLeave = "select count(id) from leave where type='sick' and id='" + eid + "' ";
        String annualLeave = "select count(id) from leave where type='annual' id='" + eid + "'  ";
        String casualLeave = "select count(id) from leave where type='casual' id='" + eid + "'  ";

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
