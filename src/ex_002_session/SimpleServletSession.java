package ex_002_session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet - session
 */
@WebServlet("/SimpleServletSession")
public class SimpleServletSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccessRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        proccessRequest(req, resp);
    }

    /**
     * Переопределение методов doGet и doPost
     * 
     * @param req
     * @param resp
     * @throws IOException
     */
    private void proccessRequest( HttpServletRequest req, HttpServletResponse resp ) throws IOException {
        // Кодировка
    	resp.setContentType("text/html;charset=UTF-8");
    	
    	// Получить объект writer
        PrintWriter writer = resp.getWriter();

        // Получить сессию из запроса
        HttpSession session = req.getSession();
        
        // Получаем объект. Запрашиваем ранее установленный аттрибут "count" 
        Object resSessionObj = session.getAttribute("count");
        
        // Если resSessionObj пусто тогда count = 0 иначе count равен resSessionObj
        int count = ( resSessionObj == null ) ? 0 : (int) resSessionObj;

        // Инкрементируем count и потом в сессию записываем значение count
        count++;
        session.setAttribute("count", count);

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

            writer.print("<h1>Count requested: " + session.getId() + "</h1>");
            writer.print("<h1>Count requested: " + count + "</h1>");


            writer.println("</body");
            writer.println("</html>");
        } finally {
            writer.close();
        }

    }
	
}
