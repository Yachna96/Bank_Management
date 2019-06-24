package pac;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class abc
 */
//@WebServlet("/abc")
public class abc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String username= null;
	private static final String password = null;
	Connection con;
    PreparedStatement ps;
    ResultSet rs;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public abc() {
        super();
        // TODO Auto-generated constructor stub
    }
public void init(ServletConfig config) throws ServletException {
		
		// TODO Auto-generated method stub
		
		 try 
         {
              Class.forName("oracle.jdbc.driver.OracleDriver");
                  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","root"); 
         } 
            catch (ClassNotFoundException e)
               {
                  System.out.println(e);               } 
            catch (SQLException e) 
               {
                  System.out.println(e);
               }

	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
       
       String username=request.getParameter("uname");
        String password=request.getParameter("pass");
        
        pw.println("<html><body>");
       try 
        {
              ps=con.prepareStatement("select * from Employee_Details where EMP_NAME=? and EMP_PASSWORD=?");
              ps.setString(1,username );
              ps.setString(2,password);
              rs=ps.executeQuery();
              if(rs.next())
              {
                    pw.println("<h3>welcome " +" " + username +"</h3>");
                    RequestDispatcher rd1=request.getRequestDispatcher("Customer.html");
                    rd1.include(request,response);
                    //or
                    //response.sendRedirect("./home.html");
                   // pw.println("<form method=\"post\" action=\"login.html\">");
                    //pw.println("<input type=\"submit\" name=\"logout\" " + "value=\"Logout\">");
                    //pw.println("</form>");
                    
              }
              else
              {
                    pw.println("<center><h3>invalid username/password Enter Correct username/password</h3></center>");
                    RequestDispatcher rd2=request.getRequestDispatcher("home.html");
                    //rd2.include(request,response);                    //or
                 //response.sendRedirect("./Login.html");
              }
        
       }
        catch (SQLException e) 
             {
        	e.printStackTrace();
             }
              

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
