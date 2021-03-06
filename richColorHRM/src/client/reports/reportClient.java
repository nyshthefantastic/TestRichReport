/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import common.queryHelper;

/**
 *
 * @author Nuwan-PC
 */
public class reportClient extends javax.swing.JFrame {

    /**
     * Creates new form reportClient
     */

    Connection conn = null;
    PreparedStatement pst;
    Statement st;
    ResultSet rs = null;
    public reportClient() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        empFrom = new org.jdesktop.swingx.JXDatePicker();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        empTo = new org.jdesktop.swingx.JXDatePicker();
        jButton3 = new javax.swing.JButton();
        loanText = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        loanFrom = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        loanTo = new org.jdesktop.swingx.JXDatePicker();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        allowanceText = new javax.swing.JComboBox<>();
        allowanceTo = new org.jdesktop.swingx.JXDatePicker();
        jLabel11 = new javax.swing.JLabel();
        allowanceFrom = new org.jdesktop.swingx.JXDatePicker();
        jLabel12 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        districtText = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Reporting");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel2.setText("Cash Report");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 37, 61, -1));

        jButton1.setText("Generate");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 36, -1, -1));

        jLabel3.setText("Bank Report");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 69, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 66, 61, -1));

        jButton2.setText("Generate");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 65, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Employee Log");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 99, -1, -1));
        getContentPane().add(empFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 124, 114, -1));

        jLabel5.setText("To");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 128, -1, -1));

        jLabel6.setText("From");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 128, -1, -1));
        getContentPane().add(empTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 124, 114, -1));

        jButton3.setText("Generate");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 124, -1, -1));

        loanText.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Loan Registry", "Leave Registry" }));
        loanText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loanTextActionPerformed(evt);
            }
        });
        getContentPane().add(loanText, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 193, -1, -1));

        jButton4.setText("Generate ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 234, -1, -1));
        getContentPane().add(loanFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 234, 114, -1));

        jLabel7.setText("To");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 238, -1, -1));

        jLabel8.setText("From");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 238, -1, -1));
        getContentPane().add(loanTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 234, 114, -1));

        jCheckBox1.setText("Annual");
        jCheckBox1.setEnabled(false);
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 192, -1, -1));

        jCheckBox2.setText("Sick");
        jCheckBox2.setEnabled(false);
        getContentPane().add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 192, -1, -1));

        jCheckBox3.setText("Casual");
        jCheckBox3.setEnabled(false);
        getContentPane().add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 192, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Loan Registry And  Leave Registry");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 165, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Allowance And Incentive Report");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 275, -1, -1));

        allowanceText.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Allowance", "Incentive" }));
        getContentPane().add(allowanceText, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 300, 105, -1));
        getContentPane().add(allowanceTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 338, 114, -1));

        jLabel11.setText("From");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 342, -1, -1));
        getContentPane().add(allowanceFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 338, 114, -1));

        jLabel12.setText("To");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 342, -1, -1));

        jButton5.setText("Generate");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 338, 81, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Employee's District Report For Election");
        jLabel13.setToolTipText("");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 379, -1, -1));

        districtText.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ampara\t", "Anuradhapura\t", "Badulla\t", "Batticaloa\t", "Colombo\t", "Galle\t", "Gampaha\t", "Hambantota\t", "Jaffna\t", "Kalutara\t", "Kandy\t", "Kegalla\t", "Kilinochchi\t", "Kurunegala\t", "Mannar\t", "Matale\t", "Matara\t", "Moneragala\t", "Mullaitivu\t", "Nuwara Eliya", "Polonnaruwa\t", "Puttalam\t", "Ratnapura\t", "Trincomalee\t", "Vavuniya" }));
        getContentPane().add(districtText, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 412, 128, -1));

        jLabel14.setText("District");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 415, -1, -1));

        jButton6.setText("Generate");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(207, 411, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String loan = loanText.getSelectedItem().toString();
        Date oDate = loanFrom.getDate();
        DateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String from = oDateFormat.format(oDate);
        Date ooDate = loanTo.getDate();
        String to = oDateFormat.format(ooDate);

        if (loan.equalsIgnoreCase("Loan Registry")) {
            loanReportClient lrc = new loanReportClient();
            lrc.setVisible(true);
            lrc.initab(from, to);

        } else {
            leaveReportClient lerc = new leaveReportClient();
            lerc.setVisible(true);
            lerc.initab(from, to);

        }
        this.setVisible(false);


    }//GEN-LAST:event_jButton4ActionPerformed

    private void loanTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loanTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loanTextActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String allowance = allowanceText.getSelectedItem().toString();
        Date oDate = allowanceFrom.getDate();
        DateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String from = oDateFormat.format(oDate);
        Date ooDate = allowanceTo.getDate();
        String to = oDateFormat.format(ooDate);

        if (allowance.equalsIgnoreCase("Allowance")) {
            allowanceReportClient arc = new allowanceReportClient();
            arc.setVisible(true);

        } else {

            IncentiveReportClient irc = new IncentiveReportClient();
            irc.setVisible(true);

        }
        this.setVisible(false);


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Date oDate = empFrom.getDate();
        DateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String from = oDateFormat.format(oDate);
        Date ooDate = empTo.getDate();
        String to = oDateFormat.format(ooDate);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
       String district=districtText.getSelectedItem().toString();
          votingReportClient vrc=new votingReportClient();
          vrc.setVisible(true);
          vrc.initab(district);
          this.setVisible(false);
         
        
      


    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(reportClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reportClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reportClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reportClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reportClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker allowanceFrom;
    private javax.swing.JComboBox<String> allowanceText;
    private org.jdesktop.swingx.JXDatePicker allowanceTo;
    private javax.swing.JComboBox<String> districtText;
    private org.jdesktop.swingx.JXDatePicker empFrom;
    private org.jdesktop.swingx.JXDatePicker empTo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private org.jdesktop.swingx.JXDatePicker loanFrom;
    private javax.swing.JComboBox<String> loanText;
    private org.jdesktop.swingx.JXDatePicker loanTo;
    // End of variables declaration//GEN-END:variables
}
