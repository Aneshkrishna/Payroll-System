import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DB {
public static Connection con=null;
public static Statement stmt=null;
	
public DB(){
	try{
		Class.forName("com.mysql.jdbc.Driver");  
		con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedetails","root","root");  
		stmt=(Statement) con.createStatement();  
		
	}catch(Exception e){
		e.printStackTrace();
	}
}
} 

