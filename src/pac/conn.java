package pac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class conn {
	 
	public static void main(String args [])
	{
	
	         try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe","hr","root");
				Statement stmt=con.createStatement();
				
				ResultSet rs=stmt.executeQuery("select * from Employee_Details");
				while(rs.next())
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
				
				con.close();
				
	     	}
	     	catch (Exception e)
	     	{
	     		System.out.println(e);
	     	}
}						
						
						
}			