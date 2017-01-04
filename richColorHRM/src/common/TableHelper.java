/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import common.queryHelper;
import javax.swing.table.TableColumn;

/**
 *
 * @author Achala Kavinda
 */
public class TableHelper {

    ResultSet rs;
    queryHelper queryHelper;
    
    public TableHelper() {
        queryHelper = new queryHelper();
    }
    
    
//Table Filling Helper
    public void fillTable(JTable tableName, String Sql, String[] dbAttribute) {
        DefaultTableModel Table = (DefaultTableModel) tableName.getModel();
        int rowCount = Table.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            Table.removeRow(i);
        }
        ResultSet rs = queryHelper.getResultSet(Sql);
        if (rs != null) {
            try {
                int colNo = dbAttribute.length;
                while (rs.next()) {
                    Object[] objects = new Object[colNo];
                    for (int i = 0; i < colNo; i++) {
                        objects[i] = rs.getString(dbAttribute[i]);
                    }
                    Table.addRow(objects);
                }
                tableName.setModel(Table);
            } catch (Exception e) {
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
    public int getSelectedRow(JTable table){
        return table.getSelectedRow();
    }   
   
//get selected cell in a row
    public String getSelectedRowCell(JTable table,int colm) {
        // get the selected row
        int selectedRow = getSelectedRow(table);
        String returnNum =table.getValueAt(selectedRow, colm).toString();
        // return the value
        return returnNum;
    }
 
//delete coloum in table
    public void deleteSelectedColoum(JTable Table,int key){
        TableColumn tc=Table.getColumnModel().getColumn(key);
        Table.removeColumn(tc);
    }    
    
}
