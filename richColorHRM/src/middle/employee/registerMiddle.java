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
import java.util.Calendar;

/**
 *
 * @author U Computers
 */
public class registerMiddle {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public registerMiddle() {

        con = dbconnct.connect();
    }

    public void registerEmployee(String fName, String lName, String cNum, String nic, String dob, String gender, String bank, String acNum, double bSalary, String category, String designation, String department, String year) {

        String nyear = getAge(year);
        try {
            String q = "INSERT INTO employeeregister(fName,lName,contactNo,nic,dob,gender,bank,accountNo,bSalary,category,designation,department,age) VALUES ('" + fName + "','" + lName + "','" + cNum + "','" + nic + "','" + dob + "','" + gender + "','" + bank + "','" + acNum + "','" + bSalary + "','" + category + "','" + designation + "','" + department + "','" + nyear + "')";

            pst = con.prepareStatement(q);
            pst.execute();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private String getAge(String year) {

        Calendar now = Calendar.getInstance();
        int yeare = now.get(Calendar.YEAR);
        String yearInString = String.valueOf(yeare);
        int x = Integer.parseInt(yearInString) - Integer.parseInt(year);
        String xyear = String.valueOf(x);
        return xyear;
    }

}
