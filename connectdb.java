/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user_interface;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Basharat
 */
public class connectdb {
    Connection con;
  
    public void conect_to_db() throws Exception{
        
        try{
            
//                                for derby data base
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//            String url="jdbc:derby://localhost:1527/Alliedd2";
//            con=DriverManager.getConnection(url,"zain","123");
//            
//                com.mysql.cj.jdbc.Driver
            Class.forName("com.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Drivr loaded");
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=clients";
////            String url = "jdbc:sqlserver://localhost\\sqlexpress:1433;databaseName=customer";
//            con = DriverManager.getConnection(url,"sa","123");
            
            
            if(con!=null){
                System.out.println("conected");
            }
            
        }catch (Exception e){
            System.out.println("diver not"+e);
        } 
    }
    
    public static void main(String[] args) {
        connectdb obj=new connectdb();
        try {
            
         
        obj.conect_to_db();
    
        } catch (Exception e) {
        }
        }
}
