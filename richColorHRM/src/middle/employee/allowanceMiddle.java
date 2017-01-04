 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package middle.employee;

/**
 *
 * @author Achala Kavinda
 */
import java.*;
import common.dbconnct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import common.helperFunctions;
import common.message;
import javax.swing.table.TableColumn;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class allowanceMiddle {
    
    private Connection conn;
    
    public allowanceMiddle() {
        conn = dbconnct.connect();
    }   
    
    
    //resut set    
     public  ResultSet rs(String sql){
        ResultSet rs=null;
        try{
            
         Statement st=conn.createStatement();
            rs =st.executeQuery(sql);            
        }catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
     
   //fill Employee table    
   public  void Table(JTable tableName,String Sql,String dbCols []){
        DefaultTableModel Table =(DefaultTableModel) tableName.getModel();
        int rowCount = Table.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount-1; i>=0 ; i--) {
           Table.removeRow(i);
        }
        ResultSet rs=rs(Sql);
        if(rs!=null){
            try{
                 int colNo=dbCols.length;
                 while(rs.next()){
                 Object[] objects = new Object[colNo];
                 for (int i = 0; i < colNo; i++) {
                    objects[i] = rs.getString(dbCols[i]);
                }
                 Table.addRow(objects);
                 }
                 tableName.setModel(Table);
             }             
             catch (Exception e) {
                System.out.println(e);
            }
        }         
    }
   
   //table row remover
   public void removeTableRow(JTable tableName){
       DefaultTableModel Table =(DefaultTableModel) tableName.getModel();
       int rowCount=Table.getRowCount();
       for (int i = rowCount-1; i>=0 ; i--) {
           Table.removeRow(i);
        }
   }
   
   //get selected row
    public String selectedRowEelement(JTable table,int colm) {
        // get the selected row
        int selectedRow = table.getSelectedRow();
        String returnNum =table.getValueAt(selectedRow, colm).toString();
        // return the value
        return returnNum;
    }   
    
    //delete coloum in table
    public void deleteColoum(JTable Table,int key){
        TableColumn tc=Table.getColumnModel().getColumn(key);
        Table.removeColumn(tc);
    }
    
    //public insert column to table
    
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
   
    
    
    
    
    //Validate Number
    
    public boolean isPositive(String Num){
    
           Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(Num);
        if ((m.find() && m.group().equals(Num)) && Float.parseFloat(Num)>0) {
            return true;
        } else {
            return false;
        }
    
    
    }
    
    
}
