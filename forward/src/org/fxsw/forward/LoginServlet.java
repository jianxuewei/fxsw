package org.fxsw.forward;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		         PrintWriter out = response.getWriter();
		         Properties properties=new Properties();
		         properties.load(this.getClass().getResourceAsStream("/user.properties"));
		         out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		         out.println("<HTML>");
		         out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		         out.println("  <BODY>");
		         
		         if(properties.getProperty("name").equals(request.getParameter("name")))
		         {
		        	 System.out.println("hello,jian");
		        	 
		        	 request.setAttribute("user", properties.getProperty("user"));
		        	 RequestDispatcher rd=request.getRequestDispatcher("/success.do");
		        	 rd.forward(request, response);
		         }
		         
		         	 out.print("    This is "+request.getParameter("name"));
		         
		         out.println(", using the GET method");
		         out.println("  </BODY>");
		         out.println("</HTML>");
		         out.flush();
		         out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
