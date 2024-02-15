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
public class withdra extends login_data {

    private String card = getCard_number();
    private int balnc;
    private int amount;
    private String date;

    private String bl;
    private String am;

    public String getBl() {
        return bl;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBalnc() {
        return balnc;
    }

    public void setBalnc(int blnc) {
        this.balnc = blnc;
    }
    static int counter = 0;

    public void balnc() throws Exception {
        connectdb db = new connectdb();
        db.conect_to_db();
        Connection con = db.con;

        PreparedStatement pst;
        String sql;

//               String sql;
//        PreparedStatement pst;
        sql = "select * from userdetails where user_card_number=? ";

        pst = con.prepareStatement(sql);

        pst.setString(1, card);
        ResultSet rs = pst.executeQuery();

        if (rs.next() == true) {
            setBalnc(rs.getInt("balance"));
        }
//   
    }

    public void match() throws Exception {
        connectdb db = new connectdb();
        try {
            db.conect_to_db();
        } catch (Exception ex) {
            Logger.getLogger(deposit.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection con = db.con;

        PreparedStatement pstmt;
        String sql;
        ResultSet rs;

        sql = "select * from userdetails where user_card_number=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, card);
        rs = pstmt.executeQuery();

        if (rs.next() == true) {
            int balanc = rs.getInt("balance");

            if (amount > 25000) {

                JOptionPane.showMessageDialog(null, "Sorry you can not withdraw more then 25,000 at a time");

            } else if (amount > balanc) {
                JOptionPane.showMessageDialog(null, "You don't have an enough balance to withdraw.");
            } else if (amount <= balanc && amount <= 25000) {
                counter += amount;

                if (counter > 50000) {
                    JOptionPane.showMessageDialog(null, "Sorry."
                            + "Your ATM card limit is 50,000 in a day.You have already withdrawed 50,000 ");
                } else {
                    sql = "insert into withdraws values(?,?,?,?)";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(4, card);
                    pstmt.setString(1, date);
                    pstmt.setInt(2, balnc);
                    pstmt.setInt(3, amount);
                    pstmt.executeUpdate();

                    sql = "update userdetails set balance=balance-? where user_card_number=?";
                    pstmt = con.prepareStatement(sql);

                    pstmt.setInt(1, amount);
                    pstmt.setString(2, card);
                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Withraw Successfully!");
                }

            }
        }
    }

}
