import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="CountServlet",urlPatterns = "/count")
public class CountServlet extends HttpServlet {
    private int counter = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String count = req.getParameter("count");
        if (count == null) {
            counter++;
        } else {
            counter = counter;
        }
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h2>This page has been viewed " + counter + " times!</h2>");
    }
}