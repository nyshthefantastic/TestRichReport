/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middle.payroll;

import common.dbconnct;
import common.queryHelper;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author Achala Kavinda
 */
public class salaryMiddle {

    Connection conn = null;
    PreparedStatement pst;
    Statement st;
    ResultSet rs = null;
    int id = 0;
    String[] dataS = new String[35];

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
    public void paySalary( ){
            Date date = new Date();
String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
        
            rs = getQuery("SELECT epfNo,empName  FROM employee WHERE isTerminated='" + "0" + "' ");
        try {
            while (rs.next()) {
                String empid=rs.getString("epfNo");
                 String empname=rs.getString("empName");
                populateFields(empid,modifiedDate);
                insertSalary(empid,modifiedDate,empname);
            }

        } catch (Exception e) {

        }
    
    
    
    }
    private void insertSalary(String id,String mdate,String empname){
        queryHelper qh=new queryHelper();
        String sql="INSERT INTO paysheet(empNo,empName,basicSalary,bra,totalBasicSalary,advance,noPayHrs,noPayLeaves,grossSalary,normalOt,doubleOt,arrears,productionIncentive,specialIncentive,attendanceIncentive,loan,epfAmount,etfAmount,allowance,stamp,incomeTax,leavesAnnual,leavesCasual,leavesSick,date) "
                + "VALUES ('" + id + "','" + empname + "','" + dataS[0] + "','" + dataS[1] + "','" + dataS[22] + "','" + dataS[20] + "','" + dataS[19] + "','" + dataS[24] + "','" + dataS[25] + "','" + dataS[4] + "','" + dataS[12] + "','" + dataS[29] + "','" + dataS[14] + "','" + dataS[10] + "','" + dataS[21] + "','" + dataS[30] + "','" + dataS[31] + "','" + dataS[26] + "','" + dataS[27] + "','" + dataS[16] + "','" + dataS[17] + "','" + dataS[15] + "','" + mdate + "') ";
        qh.insertQuery(sql);
        deductLoan(id, mdate);
    
    
    
    
    
    
    }
    private void deductLoan(String id,String mdate){
    
       try { 
                String q = "UPDATE loan_payroll SET duration=duration-'" + "1" + "'WHERE employeeId= '" + id + "' AND duration > 0  ";

                pst = conn.prepareStatement(q);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }
    
    
    
    
    }
    public String[] populateFields(String eid, String mandyear) {
        dataS[0] = getBsalary(eid);
        dataS[1] = getBRA();
        dataS[2] = getAdvance(eid, mandyear);
        dataS[3] = getNoPayDays(eid, mandyear);
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
         dataS[29] = getSpecial(eid, mandyear);

        dataS[15] = getsick(eid, mandyear);
        dataS[16] = getannual(eid, mandyear);
        dataS[17] = getcasual(eid, mandyear);
        dataS[18] = getNoPayHours(eid, mandyear);
        dataS[19] = calculateNoPayDate(dataS[0], dataS[3]);
        dataS[20] = calculateNoPayHours(dataS[0], dataS[18]);
        dataS[21] = calculateEpf(dataS[0]);
         dataS[30] = calculateEtf(dataS[0]);
        dataS[22] = calculateTotalBasic(dataS[0], dataS[1]);
        dataS[23] = calculateGross(dataS[22], dataS[19], dataS[20]);
        dataS[24] = calculateSOt(dataS[0], dataS[9]);
        dataS[25] = calculateDOt(dataS[0], dataS[10]);
        if (Double.parseDouble(dataS[0]) > 25000.00) {
            dataS[26] = getStamp();
        } else {
            dataS[26] = "0.0";
        }
        dataS[27] = getIncomeTax(dataS[0]);
        double tallow=Double.parseDouble(dataS[11]+dataS[12]+dataS[13]+dataS[14]+dataS[29]);
        double tot=Double.parseDouble(dataS[24]+dataS[25]);
        dataS[31]=String.valueOf(tot);
        double tincen=Double.parseDouble( dataS[6]+ dataS[7]+ dataS[8]);
        dataS[28]=calculateNetSalary(tallow,tot,dataS[23],tincen, dataS[4],dataS[19],dataS[20], dataS[10], dataS[26], dataS[27]);
        return dataS;

    }
private String calculateNetSalary(double allowance,double ot,String gross,double incentive,String arrears,String nopay1,String nopay2,String loan,String stamp,String income){


    double totnet=allowance+ot+incentive+Double.parseDouble(gross)+Double.parseDouble(arrears)-Double.parseDouble(nopay1)-Double.parseDouble(nopay2)-Double.parseDouble(loan+stamp+income);
    return String.valueOf(totnet);



}
    private String getIncomeTax(String sal) {

        double ptax = 0;
        double salary = Double.parseDouble(sal);
        if (salary < 62500) {
            ptax = 0;
            return String.valueOf(ptax);
        } else if ((62500 <= salary) && (salary < 104167)) {
            ptax = (salary * 0.04) - 2500;
            return String.valueOf(ptax);

        } else if ((104167 <= salary) && (salary < 145833)) {
            ptax = (salary * .08) - 6667;
            return String.valueOf(ptax);

        } else if ((145833 <= salary) && (salary < 187500)) {
            ptax = (salary * .12) - 12500;
            return String.valueOf(ptax);

        } else if (187500 <= salary) {
            ptax = (salary * .16) - 20000;
            return String.valueOf(ptax);

        }
        return "0";
    }

    private String getStamp() {

        rs = getQuery("SELECT amount  FROM rates WHERE type='" + "stamp" + "' ");
        try {
            while (rs.next()) {

                return String.valueOf(rs.getString("amount"));
            }

        } catch (Exception e) {

        }
        return "0";

    }

    private String calculateDOt(String bsal, String units) {

        return String.valueOf(((Double.parseDouble(bsal) / 200.0) * (2.0 / 4.0)) * Double.parseDouble(units));

    }

    private String calculateSOt(String bsal, String units) {

        return String.valueOf(((Double.parseDouble(bsal) / 200.0) * (1.5 / 4.0)) * Double.parseDouble(units));

    }

    private String calculateGross(String bsal, String nh, String nd) {

        return String.valueOf(Double.parseDouble(bsal) - (Double.parseDouble(nh) + Double.parseDouble(nd)));

    }

    private String calculateTotalBasic(String bsal, String bra) {

        return String.valueOf(Double.parseDouble(bsal) + (Double.parseDouble(bra)));

    }

    private String calculateNoPayHours(String bsal, String units) {

        return String.valueOf(((Double.parseDouble(bsal) / 200.0) * (1.5 / 4.0)) * Double.parseDouble(units));

    }

    private String calculateNoPayDate(String bsal, String units) {

        return String.valueOf((Double.parseDouble(bsal) / 26.0) * Double.parseDouble(units));

    }

    private String getNoPayHours(String eid, String mandyear) {

        rs = getQuery("SELECT SUM(nopay_deduction_units) as npd FROM emp_daily_records WHERE employeeId='" + eid + "' AND date LIKE '" + mandyear + "%'");
        try {
            while (rs.next()) {

                return rs.getString("npd");

            }

        } catch (Exception e) {

        }
        return "0";

    }

    private String getLoan(String eid, String modifiedDate) {

        cou = 0;

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT date,installement,duration FROM loan_payroll WHERE employeeId='" + eid + "' ");
        try {
            while (rs.next()) {
                if (Double.parseDouble(rs.getString("duration")) > 0) {
                    Date date1 = (Date) myFormat.parse(rs.getString("date"));
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

    private String getNoPayDays(String eid, String modifiedDate) {

        rs = getQuery("SELECT SUM(nopay_days) as npd FROM emp_leaves WHERE employeeId='" + eid + "' AND date LIKE '" + modifiedDate + "%'");
        try {
            while (rs.next()) {

                return rs.getString("npd");

            }

        } catch (Exception e) {

        }
        return "0";
    }

    private String getArrears(String eid, String modifiedDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT date,amount  FROM arrears_payroll WHERE empNo='" + eid + "' ");
        try {
            while (rs.next()) {

                Date date1 = (Date) myFormat.parse(rs.getString("date"));
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

        rs = getQuery("SELECT date,noOfDays FROM leavez_payroll WHERE employeeId='" + eid + "' AND type='" + "casual" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("date"));
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

        rs = getQuery("SELECT date,noOfDays FROM leavez_payroll WHERE employeeId='" + eid + "' AND type='" + "annual" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("date"));
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

        rs = getQuery("SELECT date,noOfDays FROM leavez_payroll WHERE employeeId='" + eid + "' AND type='" + "sick" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("date"));
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
   private String getSpecial(String eid, String modifiedDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT date,amount FROM allowance_payroll WHERE employeeId='" + eid + "' AND type='" + "Special" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("date"));
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
    private String getAttendence(String eid, String modifiedDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT date,amount FROM allowance_payroll WHERE employeeId='" + eid + "' AND type='" + "Attendence" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("date"));
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

        rs = getQuery("SELECT date,amount FROM allowance_payroll WHERE employeeId='" + eid + "' AND type='" + "Festival" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("date"));
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
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT date,amount FROM allowance_payroll WHERE employeeId='" + eid + "' AND type='" + "Production" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("date"));
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

    private String getTravelling(String eid, String modifiedDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT date,amount FROM allowance_payroll WHERE employeeId='" + eid + "' AND type='" + "Travelling" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("date"));
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

    private String getDoubleOt(String eid, String modifiedDate) {
        rs = getQuery("SELECT SUM(show_2_ot) as npd FROM emp_daily_records WHERE employeeId='" + eid + "' AND date LIKE '" + modifiedDate + "%'");
        try {
            while (rs.next()) {

                return rs.getString("npd");

            }

        } catch (Exception e) {

        }
        return "0";

    }

    private String getNormalOt(String eid, String modifiedDate) {
        rs = getQuery("SELECT SUM(show_ot) as npd FROM emp_daily_records WHERE employeeId='" + eid + "' AND date LIKE '" + modifiedDate + "%'");
        try {
            while (rs.next()) {

                return rs.getString("npd");

            }

        } catch (Exception e) {

        }
        return "0";

    }

    private String getSpecialIncentive(String eid, String modifiedDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT date,amount FROM intensive_payroll WHERE employeeId='" + eid + "' AND type='" + "Special" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("date"));
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

    private String getAttendanceIncentive(String eid, String modifiedDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT date,amount FROM intensive_payroll WHERE employeeId='" + eid + "' AND type='" + "Attendance" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("date"));
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

    private String getProductionIncentive(String eid, String modifiedDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT date,amount FROM intensive_payroll WHERE employeeId='" + eid + "' AND type='" + "Production" + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("date"));
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
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM");

        rs = getQuery("SELECT date,outstanding  FROM advance WHERE employeeId='" + eid + "'");
        try {
            while (rs.next()) {
                Date date1 = (Date) myFormat.parse(rs.getString("date"));
                String dx = String.valueOf(date1);
                if (dx.equals(modifiedDate)) {
                    String bsalary = rs.getString("outstanding");
                    return bsalary;
                }

            }
        } catch (Exception e) {

        }
        return "0";
    }
   private String calculateEtf(String bsalary) {
        rs = getQuery("SELECT percentage FROM rates WHERE type='" + "ETF" + "'");
        try {
            while (rs.next()) {

                String percentage = rs.getString("percentage");
                String amount = String.valueOf(Double.parseDouble(bsalary) * (Double.parseDouble(percentage) / 100.0));
                return amount;

            }
        } catch (Exception e) {

        }
        return "0";

    }
    private String calculateEpf(String bsalary) {
        rs = getQuery("SELECT percentage FROM rates WHERE type='" + "EPF" + "'");
        try {
            while (rs.next()) {

                String percentage = rs.getString("percentage");
                String amount = String.valueOf(Double.parseDouble(bsalary) * (Double.parseDouble(percentage) / 100.0));
                return amount;

            }
        } catch (Exception e) {

        }
        return "0";

    }

    private String getBRA() {
        rs = getQuery("SELECT amount FROM rates WHERE type='" + "BRA" + "'");
        try {
            while (rs.next()) {

                String amount = rs.getString("amount");
                return amount;

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
