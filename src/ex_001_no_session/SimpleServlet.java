package ex_001_no_session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet - no session
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Cервлет подключается один раз при старте WEB сервера, это свойство класса будет жить пока работает сервер
	private int count;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccessRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccessRequest(req, resp);
    }

    private void proccessRequest( HttpServletRequest req, HttpServletResponse resp ) throws IOException {
        // Кодировка
    	resp.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        count++;

        Enumeration<String> allParams = req.getParameterNames();

        try {
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>");
            writer.println("second servlet");
            writer.println("</title>");
            writer.println("</head>");
            writer.println("<body>");

            int i = 1;

            while (allParams.hasMoreElements()) {
                writer.print("<p>");
                String param = allParams.nextElement();
                writer.println("param " + i + " " + param);
                writer.print("</p>");
                i++;
            }

            writer.print("<h1>Count requested: " + count + "</h1>");


            writer.println("</body");
            writer.println("</html>");
        } finally {
            writer.close();
        }

    }
}
