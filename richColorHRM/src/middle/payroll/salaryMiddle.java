/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.payroll;

import common.dbconnct;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Achala Kavinda
 */
public class salaryMiddle {

    Connection conn = null;
    Statement st;
    ResultSet rs = null;
    int id = 0;
    String[] dataS = new String[20];
    int count = 0;
    double cou = 0;

    public salaryMiddle() {
        conn = dbconnct.connect();
    }

    public ResultSet getQuery(String sql) {
        try {
            Statement statement = conn.createStatement();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String populateFields(String eid, String mandyear) {
        dataS[0] = getBsalary(eid);
        dataS[1] = getBRA();
        dataS[2] = getAdvance(eid, mandyear);
        dataS[3] = getNoPay(eid, mandyear);
        dataS[4] = getArrears(eid, mandyear);
        dataS[5] = getDaysWorked(eid, mandyear);
        dataS[6] = getProductionIncentive(eid, mandyear);
        dataS[7] = getSpecialIncentive(eid, mandyear);
        dataS[8] = getAttendanceIncentive(eid, mandyear);
        dataS[9] = getNormalOt(eid, mandyear);
        dataS[10] = getDoubleOt(eid, mandyear);
        dataS[10] = getLoan(eid, mandyear);
        //deduct duration by one in loan_payroll when paying
        dataS[11] = getTravelling(eid, mandyear);
        dataS[12] = getProduction(eid, mandyear);
        dataS[13] = getFestival(eid, mandyear);
        dataS[14] = getAttendence(eid, mandyear);

        dataS[15] = getsick(eid, mandyear);
        dataS[16] = getannual(eid, mandyear);
        dataS[17] = getcasual(eid, mandyear);

    }

    private String getLoan(String eid, String modifiedDate) {

        cou = 0;

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT Date,installement,duration FROM loan_payroll WHERE employeeId='" + eid + "' ");
        try {
            while (rs.next()) {
                if (Double.parseDouble(rs.getString("duration")) > 0) {
                    Date date1 = (Date) myFormat.parse(rs.getString("Date"));
                    String dx = String.valueOf(date1);
                    if (dx.equals(modifiedDate)) {
                        cou = cou + Double.parseDouble("installement");
                    }

                }

            }
            return String.valueOf(cou);

        } catch (Exception e) {

        }
        return "0";

    }

    private String getNoPay(String eid, String modifiedDate) {

        cou = 0;

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");
        rs = getQuery("SELECT Date,numberOfDays,Date FROM nopay WHERE empNo='" + eid + "' ");
        try {
            while (rs.next()) {

                Date date1 = (Date) myFormat.parse(rs.getString("Date"));
                String dx = String.valueOf(date1);
                if (dx.equals(modifiedDate)) {
                    cou = cou + Double.parseDouble(rs.getString("numberOfDays"));

                }

            }
            return String.valueOf(cou);
        } catch (Exception e) {

        }
        return "0";

    }

    private String getArrears(String eid, String modifiedDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT Date,amount  FROM arrears_payroll WHERE empNo='" + eid + "' ");
        try {
            while (rs.next()) {

                Date date1 = (Date) myFormat.parse(rs.getString("Date"));
                String dx = String.valueOf(date1);
                if (dx.equals(modifiedDate)) {
                    String samt = rs.getString("amount");
                    return samt;
                }

            }
        } catch (Exception e) {

        }
        return "0";

    }

    private String getcasual(String eid, String modifiedDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT Date,noOfDays FROM leavez_payroll WHERE employeeId='" + eid + "' AND type='" + "casual" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("Date"));
                String dx = String.valueOf(date1);
                if (dx.equals(modifiedDate)) {
                    String samt = rs.getString("noOfDays");
                    return samt;
                }
            }
        } catch (Exception e) {

        }
        return "0";

    }

    private String getannual(String eid, String modifiedDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT Date,noOfDays FROM leavez_payroll WHERE employeeId='" + eid + "' AND type='" + "annual" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("Date"));
                String dx = String.valueOf(date1);
                if (dx.equals(modifiedDate)) {
                    String samt = rs.getString("noOfDays");
                    return samt;
                }

            }
        } catch (Exception e) {

        }
        return "0";

    }

    private String getsick(String eid, String modifiedDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT Date,noOfDays FROM leavez_payroll WHERE employeeId='" + eid + "' AND type='" + "sick" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("Date"));
                String dx = String.valueOf(date1);
                if (dx.equals(modifiedDate)) {
                    String samt = rs.getString("noOfDays");
                    return samt;
                }

            }
        } catch (Exception e) {

        }
        return "0";

    }

    private String getAttendence(String eid, String modifiedDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT Date,amount FROM allowance_payroll WHERE employeeId='" + eid + "' AND type='" + "Attendence" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("Date"));
                String dx = String.valueOf(date1);
                if (dx.equals(modifiedDate)) {
                    String samt = rs.getString("amount");
                    return samt;
                }

            }
        } catch (Exception e) {

        }
        return "0";

    }

    private String getFestival(String eid, String modifiedDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT Date,amount FROM allowance_payroll WHERE employeeId='" + eid + "' AND type='" + "Festival" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("Date"));
                String dx = String.valueOf(date1);
                if (dx.equals(modifiedDate)) {
                    String samt = rs.getString("amount");
                    return samt;
                }

            }
        } catch (Exception e) {

        }
        return "0";

    }

    private String getProduction(String eid, String modifiedDate) {

        rs = getQuery("SELECT Date,amount FROM allowance_payroll WHERE employeeId='" + eid + "' AND type='" + "Production" + "'");
        try {
            while (rs.next()) {
                String samt = rs.getString("amount");
                return samt;

            }
        } catch (Exception e) {

        }
        return "0";

    }

    private String getTravelling(String eid, String modifiedDate) {

        rs = getQuery("SELECT Date,amount FROM allowance_payroll WHERE employeeId='" + eid + "' AND type='" + "Travelling" + "'");
        try {
            while (rs.next()) {
                String samt = rs.getString("amount");
                return samt;

            }
        } catch (Exception e) {

        }
        return "0";

    }

    private String getDoubleOt(String eid, String modifiedDate) {

        rs = getQuery("SELECT Date,dot FROM ot_payroll WHERE empId='" + eid + "'");
        try {
            while (rs.next()) {
                String samt = rs.getString("dot");
                return samt;

            }
        } catch (Exception e) {

        }
        return "0";

    }

    private String getNormalOt(String eid, String modifiedDate) {

        rs = getQuery("SELECT Date,not FROM ot_payroll WHERE empId='" + eid + "'");
        try {
            while (rs.next()) {
                String samt = rs.getString("not");
                return samt;

            }
        } catch (Exception e) {

        }
        return "0";

    }

    private String getSpecialIncentive(String eid, String modifiedDate) {

        rs = getQuery("SELECT SUM(amount) AS amountt FROM intensive_payroll WHERE employeeId='" + eid + "' AND type='" + "Special" + "'");
        try {
            while (rs.next()) {
                String samt = rs.getString("amountt");
                return samt;

            }
        } catch (Exception e) {

        }
        return "0";

    }

    private String getAttendanceIncentive(String eid, String modifiedDate) {

        rs = getQuery("SELECT SUM(amount) AS amountt FROM intensive_payroll WHERE employeeId='" + eid + "' AND type='" + "Attendance" + "'");
        try {
            while (rs.next()) {
                String samt = rs.getString("amountt");
                return samt;

            }
        } catch (Exception e) {

        }
        return "0";
    }

    private String getProductionIncentive(String eid, String modifiedDate) {

        rs = getQuery("SELECT SUM(amount) AS amountt FROM intensive_payroll WHERE employeeId='" + eid + "' AND type='" + "Production" + "'");
        try {
            while (rs.next()) {
                String samt = rs.getString("amountt");
                return samt;

            }
        } catch (Exception e) {

        }
        return "0";
    }

    private String getDaysWorked(String eid, String modifiedDate) {

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");
        rs = getQuery("SELECT * FROM timestamp WHERE empNo='" + eid + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("indate"));
                String dx = String.valueOf(date1);
                if (dx.equals(modifiedDate)) {
                    count++;
                }
            }
            String rcoun = String.valueOf(count);
            count = 0;
            return rcoun;
        } catch (Exception e) {

        }
        return String.valueOf(count);
    }

    private String getAdvance(String eid, String modifiedDate) {

        rs = getQuery("SELECT SUM(outstanding) AS outstanding FROM advance WHERE employeeId='" + eid + "'");
        try {
            if (rs.next()) {
                String bsalary = rs.getString("outstanding");
                return bsalary;

            }
        } catch (Exception e) {

        }
        return "0";
    }

    private String getEpf(String eid, String modifiedDate) {
        rs = getQuery("SELECT Date,epf FROM employee WHERE epfNo='" + eid + "'");
        try {
            if (rs.next()) {
                String bsalary = rs.getString("bSalary");
                return bsalary;

            }
        } catch (Exception e) {

        }
        return null;
    }

    private String getBRA() {
        rs = getQuery("SELECT amount FROM rates '");
        try {
            if (rs.next()) {
                String id = rs.getString("id");
                if (id.equals("1")) {
                    String amount = rs.getString("amount");
                    return amount;
                }

            }
        } catch (Exception e) {

        }
        return "0";
    }

    private String getBsalary(String eid) {
        rs = getQuery("SELECT bSalary FROM employee WHERE epfNo='" + eid + "'");
        try {
            if (rs.next()) {
                String bsalary = rs.getString("bSalary");
                return bsalary;

            }
        } catch (Exception e) {

        }
        return "0";
    }
}
