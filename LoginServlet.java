import java.io.*; 
import jakarta.servlet.*; 
import jakarta.servlet.http.*; 
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet { 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 

        response.setContentType("text/html"); 
        PrintWriter out = response.getWriter(); 

        out.println("<!DOCTYPE html><html><head><title>Login</title></head><body>");
        out.println("<h2>Login Page</h2>"); 
        out.println("<form method='post' action='" + request.getContextPath() + "/LoginServlet'>"); 
        out.println("Username: <input type='text' name='username' required><br><br>"); 
        out.println("Password: <input type='password' name='password' required><br><br>"); 
        out.println("<input type='submit' value='Login'>"); 
        out.println("</form>"); 
        out.println("</body></html>");
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 

        response.setContentType("text/html"); 
        PrintWriter out = response.getWriter(); 

        String uname = request.getParameter("username"); 
        String pwd = request.getParameter("password"); 

        out.println("<!DOCTYPE html><html><head><title>Response</title></head><body>");

        // Null checks combined with hardcoded credential check
        if ("admin".equals(uname) && "12345".equals(pwd)) { 
            // Escape/sanitize user input before rendering inside HTML context
            String safeUname = uname == null ? "" : uname.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
            out.println("<h3>Login Successful! Welcome, " + safeUname + ".</h3>"); 
        } else { 
            out.println("<h3>Login Failed! Invalid username or password.</h3>"); 
            // Fixed URL to point back to the Servlet's doGet route
            out.println("<a href='" + request.getContextPath() + "/LoginServlet'>Try Again</a>"); 
        }

        out.println("</body></html>");
    } 
}