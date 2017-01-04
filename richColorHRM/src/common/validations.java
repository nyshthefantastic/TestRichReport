/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author U Computers
 */
public class validations {

    message mess = null;
//------------------------------------REGISTER EMPLOYEE START--------------------------------------------
    public boolean checkEmptyField(String fName, String lName, String cNum, String nic, String dob, String bank, String acNum, double bSalary, String nyear) {
        if (fName.isEmpty() | lName.isEmpty() | cNum.isEmpty() | nic.isEmpty() | dob.isEmpty() | bank.isEmpty() | acNum.isEmpty() |  nyear.isEmpty()) {
            mess = new message();
            mess.messageBox("One or More Fields are Empty !");
            return false;
        } else {
            return true;
        }

    }
    
    

    public boolean checkFieldText(String fName, String lName, String bank) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(fName);
        Matcher l = p.matcher(lName);
        Matcher b = p.matcher(bank);
        if ((m.find() && m.group().equals(fName)) && (l.find() && l.group().equals(lName)) && (b.find() && b.group().equals(bank))) {
            return true;
        } else {
            mess = new message();
            mess.messageBox("First Name, Last Name and Bank should only contain [A-Z | a-z] !");
            return false;

        }

    }
    public boolean isText(String value) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(value);
        if (m.find()) {
            return true;
        } else {
            mess = new message();
            mess.messageBox("Please check input || Text Only Feild !");
            return false;

        }

    }

    public boolean validateContactNumber(String contactNo) {
        Pattern p = Pattern.compile("\\d{10}");
        Matcher m = p.matcher(contactNo);
        if (m.find() && m.group().equals(contactNo)) {

            return true;
        } else {
            mess = new message();
            mess.messageBox("Contact Number can only contain 10 digits. eg-0771111111");
            return false;
        }

    }
    public boolean validateNIC(String nic){
        
           Pattern p = Pattern.compile("^[0-9]{9}[vVxX]$");
        Matcher m = p.matcher(nic);
        if (m.find() && m.group().equals(nic)) {

            return true;
        } else {
            mess = new message();
            mess.messageBox("NIC number must be in the format of 999999999[v/x]");
            return false;
        }
    
    }
    public boolean validatedob(String ageN){
        int age=Integer.parseInt(ageN);
        if((age>15) &&  (age < 65)){
            return true;
        
        }else{
             mess = new message();
            mess.messageBox("Age cannot be greater than 65 or lesser than 16.please modify date of birth");
            return false;
        }
    
    
    }
    
    public boolean fieldNumberValidatiion(String acNum,double bsalary){
    
           Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(acNum);
        if ((m.find() && m.group().equals(acNum)) && (bsalary >0)) {

            return true;
        } else {
            mess = new message();
            mess.messageBox("Account Number and basic salary can only contain Numerics");
            return false;
        }
    
    
    }
    
    public boolean NumberValidatiion(String acNum){
    
           Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(acNum);
        if ((m.find() && m.group().equals(acNum))) {
            return true;
        } else {
            mess = new message();
            mess.messageBox("Do not enter letters on numeric feilds");
            return false;
        }
    
    
    }
    
    
      public boolean salaryValidatiion(double bsal){
    
       
        if ( (bsal >12000)) {

            return true;
        } else {
            mess = new message();
            mess.messageBox("basic salary must be greater than Rs.12000.00");
            return false;
        }
    
    
    }
        
    //------------------------------REGISTER EMPLOYEE END----------------    
      

      
}
