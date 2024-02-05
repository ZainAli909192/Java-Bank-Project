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
public class blncdata extends login_data {
    private String card=getCard_number();
    private String blnc;
    private String name;

    public String getBlnc() {
        return blnc;
    }

    public String getName() {
        return name;
    }

    public void setBlnc(String blnc) {
        this.blnc = blnc;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
public void match()throws Exception{    
             try {                                   
            // TODO add your handling code here:
            deposit_data obj=new deposit_data();
//            obj.setNumb(cnumb.getText());
//            String n=cnumb.getText();
            connectdb db = new connectdb();
            
                db.conect_to_db();
            
            Connection con = db.con;
            
            PreparedStatement pstmt;
            String sql;
            
            
            sql="select balance,user_name from  userdetails  where user_card_number=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, card);
            ResultSet rs=pstmt.executeQuery();
            
            if(rs.next()==false){
                JOptionPane.showMessageDialog(null , "No such Account created");
                
            }
            
            else {
                setBlnc(rs.getString("balance"));
                                setName(rs.getString("user_name"));

                
//deposit obj=new deposit();

        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(deposit.class.getName()).log(Level.SEVERE, null, ex);
        }

}
}
