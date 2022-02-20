import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class OneSingleJSON {

    public static void main(String []args) throws ClassNotFoundException, SQLException, IOException {

Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn=null;
ArrayList<CustomerDetails> al=new ArrayList<CustomerDetails>();
   conn=  DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","Coolmarkiv@90");

   //Object of the statement class will help us execute queries
    Statement st= conn.createStatement();
        JSONArray ja=new JSONArray();
    ResultSet rs=st.executeQuery("select * from CustomerInfo where location = 'Asia' and purchaseDate=curdate();");
    while(rs.next()) {
        CustomerDetails c=new CustomerDetails();
        c.setCourseName(rs.getString(1));
        c.setPurchasedDate(rs.getString(2));
        c.setAmount(rs.getInt(3));
        c.setLocation(rs.getString(4));
/*  System.out.println(c.getCourseName());
        System.out.println(c.getPurchasedDate());
        System.out.println(c.getAmount());
        System.out.println(c.getLocation());*/
       al.add(c);
        //Create string from the java object
        Gson g=new Gson();
        String jsonString=g.toJson(c);
        ja.add(jsonString);
    }

    for (int i=0;i<al.size();i++) {
        ObjectMapper o = new ObjectMapper();
        o.writeValue(new File("/Users/thonthanaalvikram/Projects/JsonJava/CustomerInfo"+i+".json"), al.get(i));
    }


    //JSON Simple
        JSONObject jo=new JSONObject();
    jo.put("data",ja);
   // System.out.println(jo.toJSONString());
     String unescapedString=  StringEscapeUtils.unescapeJava(jo.toJSONString());
     String stringreplaced=unescapedString.replace("\"{","{");
        String stringreplaced1=stringreplaced.replace("}\"","}");
     System.out.println(stringreplaced1);
     try (FileWriter fw=new FileWriter("/Users/thonthanaalvikram/Projects/JsonJava/Single.json")){
         fw.write(stringreplaced1);
     }

    /*    ObjectMapper o = new ObjectMapper();
        o.writeValue(new File("/Users/thonthanaalvikram/Projects/JsonJava/CustomerInfo"+i+".json"), al.get(i));
*/

        conn.close();
    }

}
