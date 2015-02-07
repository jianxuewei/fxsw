package org.fxsw.study;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PrintChineseServlet
 */
@WebServlet("/printChinese")
public class PrintChineseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintChineseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		printChinese("中国人",response);
	}

	private void printChinese(String chineseCharacter, HttpServletResponse response) throws IOException {
		/*OutputStream outputStream=response.getOutputStream();
		//chineseCharacter="中国";
		response.setHeader("content-type", "text/html;charset=UTF-8");
		byte[] b=chineseCharacter.getBytes("UTF-8");
		outputStream.write(b);*/
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		response.setHeader("content-type", "text/html;charset=UTF-8");
		out.println(chineseCharacter);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
