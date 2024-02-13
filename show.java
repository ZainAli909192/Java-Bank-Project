
package user_interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class show extends login_data {

    private static String name;

    private String card = getCard_number();

//    overriding
    public void match() throws Exception {

        connectdb db = new connectdb();
        db.conect_to_db();
        Connection con = db.con;

        PreparedStatement pst;
        String sql;
        ResultSet rs;

        sql = "select* from userdetails where user_card_number=?";
        pst = con.prepareStatement(sql);
        pst.setString(1, card);
        rs = pst.executeQuery();
//               pst.executeUpdate();
        if (rs.next() == true) {
            
 
            setName(rs.getString("user_name"));

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
