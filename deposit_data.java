/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class deposit_data extends login_data {

    private String numb = getCard_number();
    private String blnc;
    private String date;
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBlnc() {
        return blnc;
    }

    public void setBlnc(String blnc) {
        this.blnc = blnc;
    }

    public String getNumb() {
        return numb;
    }

    public void setNumb(String numb) {
        this.numb = numb;
    }

    public void blnc_get() {
        try {
            // TODO add your handling code here:
            deposit_data obj = new deposit_data();
//            obj.setNumb(cnumb.getText());
//            String n=cnumb.getText();
            connectdb db = new connectdb();
            try {
                db.conect_to_db();
            } catch (Exception ex) {
                Logger.getLogger(deposit.class.getName()).log(Level.SEVERE, null, ex);
            }
            Connection con = db.con;

            PreparedStatement pstmt;
            String sql;

            sql = "select balance from userdetails where  user_card_number=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, numb);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() == false) {
                JOptionPane.showMessageDialog(null, "No such Account created");
                System.out.println("No account createed");

            } else {
                setBlnc(rs.getString("balance"));
//                     
            }

        } catch (SQLException ex) {
            Logger.getLogger(deposit.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Connection error with Database");

        }
    }

    public void dep() throws Exception {
        connectdb db = new connectdb();
        db.conect_to_db();
        Connection con = db.con;

        PreparedStatement pstmt;
        String sql;

        sql = "select deposit from userdeposits where card_number=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, numb);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next() == false) {
            JOptionPane.showMessageDialog(null, "No such Account created");

        } else {
            String a = rs.getString("user_blnc");
//deposit obj=new deposit();

        }
    }

    public void insert() {
        try {

            connectdb db = new connectdb();
            db.conect_to_db();
            Connection con = db.con;

//
            PreparedStatement pstmt;
            String sql;

            sql = "select * from userdetails where user_card_number=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, numb);
                    ResultSet rs = pstmt.executeQuery();
if(rs.next()){

            sql = "insert into userdeposits(card_number,date,balance,deposit) values(?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, numb);
            pstmt.setString(2, date);
            pstmt.setString(3, blnc);

            pstmt.setString(4, amount);
            pstmt.executeUpdate();

//            
            sql = "update userdetails set balance=balance+? where user_card_number=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, amount);
            pstmt.setString(2, numb);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Deposit Successfully!");
}
else{
    System.out.println("Unsucess");
}
        } catch (Exception ex) {
            Logger.getLogger(deposit.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Deposit Fail!");

        }
    }

}
