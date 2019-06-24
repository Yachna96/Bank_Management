package pac;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.PrintWriter;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
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
		int cid=1;
		
		response.setContentType("Text/html");
		PrintWriter pw=response.getWriter();
		
		String p=request.getParameter("cname");
	    String l=request.getParameter("cdob");
		String m=request.getParameter("cadd");
		String r=request.getParameter("cnum");
		String q=request.getParameter("cemail");
		String t=request.getParameter("gender");
 		String x=request.getParameter("cpass");
		
		pw.println("hello");
		int i;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","root");
			PreparedStatement ps=con.prepareStatement("insert into CUSTOMER_PERSONAL_DETAILS values(?,?,?,?,?,?,?,?)");
			Statement st=con.createStatement();
			
			ps.setString(2,p);
			ps.setString(3,l);
			ps.setString(4,m);
			ps.setString(5,r);
			ps.setString(6,q);
			
			ps.setString(7,t);
			ps.setString(8,x);
			//ps.executeUpdate();
		     ResultSet rs=st.executeQuery("select * from customer_personal_details");
		     while(rs.next())
			{
			cid=rs.getInt(1);
			cid++;
				
			}
		     ps.setInt(1,cid);
			i=ps.executeUpdate();
			if(i>0)
			{
				pw.print("You're Successfully registered....");
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
