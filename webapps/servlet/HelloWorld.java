import java.io.*;
import javax.servlet.*;
import java.io.PrintWriter;

//javac -cp /home/luhuang/workspace/Duke/lib/servlet-api-2.2.jar HelloWorld.java
public class HelloWorld implements Servlet {
	public void init(ServletConfig config) throws ServletException {
	}

	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlet - Greeting from Duke</h1>");
		out.println("</body>");
		out.println("</html>");
	}

	public void destroy() {
	}

	public String getServletInfo() {
		return null;
	}

	public ServletConfig getServletConfig() {
		return null;
	}

}
