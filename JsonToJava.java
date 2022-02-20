import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class JsonToJava {

    public static void main(String []args) throws ClassNotFoundException, SQLException, IOException {

Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn=null;
ArrayList<CustomerDetails> al=new ArrayList<CustomerDetails>();
   conn=  DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","Coolmarkiv@90");

   //Object of the statement class will help us execute queries
    Statement st= conn.createStatement();
    ResultSet rs=st.executeQuery("select * from CustomerInfo where location = 'Asia' and purchaseDate=curdate();");
    while(rs.next()) {
        CustomerDetails c=new CustomerDetails();
        c.setCourseName(rs.getString(1));
        c.setPurchasedDate(rs.getString(2));
        c.setAmount(rs.getInt(3));
        c.setLocation(rs.getString(4));
  System.out.println(c.getCourseName());
        System.out.println(c.getPurchasedDate());
        System.out.println(c.getAmount());
        System.out.println(c.getLocation());
       al.add(c);
    }

    for (int i=0;i<al.size();i++) {
        ObjectMapper o = new ObjectMapper();
        o.writeValue(new File("/Users/thonthanaalvikram/Projects/JsonJava/CustomerInfo"+i+".json"), al.get(i));
    }
        conn.close();
    }

}
