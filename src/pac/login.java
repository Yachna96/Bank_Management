package pac;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ResultSet rs;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
			}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter pw=response.getWriter();
		String p=request.getParameter("uname");
	    String l=request.getParameter("pass");
	    
	    try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","root");
			PreparedStatement ps=con.prepareStatement("select * from CUSTOMER_PERSONAL_DETAILS where CUST_NAME=? AND CUST_PASSWORD=?");
		
			ps.setString(1,p);
			ps.setString(2,l);
						
			rs=ps.executeQuery();
			if(rs.next())
			{
				 pw.println("<h3>welcome " +" " +p +"</h3>");
                 RequestDispatcher rd1=request.getRequestDispatcher("menu.html");
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
                 RequestDispatcher rd2=request.getRequestDispatcher("Customer.html");
                 //rd2.include(request,response);                    //or
              //response.sendRedirect("./Login.html");
           }

			
		}catch(Exception e2)
		{
			System.out.println(e2);
			pw.close();
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
