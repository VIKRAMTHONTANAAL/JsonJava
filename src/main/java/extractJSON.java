import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class extractJSON {

    public static void main(String[] args) throws IOException {

        ObjectMapper o = new ObjectMapper();
       CustomerDetailsObject co= o.readValue(new File("/Users/thonthanaalvikram/Projects/JsonJava/CustomerDetailsObject.json"),CustomerDetailsObject.class);
System.out.println(co.getStudentName());
    }
}
