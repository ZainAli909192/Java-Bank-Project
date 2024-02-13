/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 *zsd and open the template in the editor.
 */
package user_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class login_data {
 private static String card_number;
 private String pin;

    public String getCard_number() {
        return card_number;
    }

    public String getPin() {
        return pin;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
        
 public void match() throws Exception{
     connectdb db=new connectdb();
    db.conect_to_db();
    Connection con=db.con;
    
    PreparedStatement pst;
     String sql;
     
        sql="select * from tbluser where users_card_number=? and users_pin=?";
        try {
            pst=con.prepareStatement(sql);
        
        pst.setString(1,card_number);
//        Integer be=new Integer(pas);
        pst.setString(2,pin);
           ResultSet rs;
           rs=pst.executeQuery();
           if(rs.next()){
               
   Icon icon=new javax.swing.ImageIcon("F:\\netbeans\\netbeans projects\\Allied\\ic.png");
               JOptionPane.showMessageDialog(null, "Login Successfully!","SUCCESS",JOptionPane.OK_OPTION,icon);     
              
                             show_data sign=new show_data();
                sign.setVisible(true);
//                login.dispose();

           }
           else{
     Icon icon=new javax.swing.ImageIcon("F:\\netbeans\\netbeans projects\\Allied\\cross.png");
               JOptionPane.showMessageDialog(null, "Invalid Card Number or Password","Authentication",JOptionPane.WARNING_MESSAGE,icon);        
           }}
catch (Exception e) {
            System.out.println("Eroor"+e);
        }

    
 } 
    
}
