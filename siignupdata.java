package user_interface;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class siignupdata {

    private String account_number;
    private String name;
    private String cnic;
    private String pas;
    private String deposit;

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getAccount_number() {
        return account_number;
    }

    public String getName() {
        return name;
    }

    public String getCnic() {
        return cnic;
    }

    public String getPas() {
        return pas;
    }
    

  
    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public void setName(String name) {
        this.name = name;
    }

  
    public void setPas(String pas) {
        this.pas = pas;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }
    
    
    
    public void match() throws Exception {
        connectdb db = new connectdb();
        db.conect_to_db();
        Connection con = db.con;

        PreparedStatement pst;
        String sql;
        ResultSet rs;

        sql = "select *from accounts where account_number=? and name=? and cnic=? and balance=?";

        pst = con.prepareStatement(sql);
        pst.setString(1, account_number);
                pst.setString(2, name);
        pst.setString(3, cnic);
        pst.setString(4, deposit);

        

rs=pst.executeQuery();
if(rs.next()==true){
    insert();
    tbluser();
}
else if(rs.next()==false){
    JOptionPane.showMessageDialog(null, "Invalid Data Please try again Later...!");
}
    
    }

    public void insert() throws Exception {
        connectdb db = new connectdb();
        db.conect_to_db();
        Connection con = db.con;

        PreparedStatement pst;
        String sql;
        
        sql="select * from userdetails where user_card_number=?";
        
        pst=con.prepareStatement(sql);
        pst.setString(1, account_number);
        ResultSet rs = pst.executeQuery();

if(rs.next()){
   JOptionPane.showMessageDialog(null, "you have Already registerd");
}        
else{

        sql = "insert into userdetails values(?,?,?,?,?)";

        pst = con.prepareStatement(sql);
        pst.setString(1, account_number);
                pst.setString(2, name);
        pst.setString(3, cnic);
        pst.setString(4, pas);
        pst.setString(5, deposit);

        pst.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Activated successfully");
        
        File file = new File("Customers_data.txt");
        FileWriter fw = new FileWriter(file, true);
        fw.write("Card number: ");
        fw.write(account_number);
        fw.write(" ");
        fw.write("name: ");
        fw.write(getName());
        fw.write(" ");
      
        fw.write("CNIC: ");

        fw.write(getCnic());
        fw.write(" ");
        fw.write("Initial deposit: ");

        fw.write(getDeposit());

     

        fw.write(" ");
        fw.write("PIN: ");

        fw.write(getPas());
        fw.write("\n");
        fw.close();
//
////
               Scanner fi=new Scanner(file);
while(fi.hasNextLine()){
    String data=fi.nextLine();
    System.out.println(data);
}
        try {
            if (file.createNewFile()) {
                System.out.println("File created");

            } else {
                System.out.println(file.getAbsolutePath());
            }

            System.out.println("Already Exist");
        } catch (Exception ex) {
            System.out.println(ex);
        }
//        
}
    }

    public void tbluser() throws Exception {
        connectdb db = new connectdb();
        db.conect_to_db();
        Connection con = db.con;

        PreparedStatement pst;
        String sql;

        sql = "insert into tbluser values(?,?)";
        pst = con.prepareStatement(sql);
        pst.setString(1, account_number);
        pst.setString(2, pas);
        pst.executeUpdate();
    }

}
