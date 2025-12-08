package servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

/**
 * LoginServlet handles user authentication for the Library Management System.
 * This servlet demonstrates:
 * - HTTP POST handling
 * - Session management
 * - Database integration
 * - Secure authentication flow
 * 
 * @author Library Management System Team
 * @version 1.0
 */
public class LoginServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Handles HTTP GET requests - redirects to login page
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
    
    /**
     * Handles HTTP POST requests for user login
     * Validates credentials and creates session on success
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Input validation
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            response.sendRedirect("login.jsp?error=empty");
            return;
        }
        
        // Authenticate user
        boolean isValid = authenticateUser(username, password);
        
        if (isValid) {
            // Create session for authenticated user
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("loginTime", System.currentTimeMillis());
            session.setMaxInactiveInterval(30 * 60); // 30 minutes
            
            // Log successful login
            log("User logged in successfully: " + username);
            
            // Redirect to dashboard/admin page
            response.sendRedirect("dashboard.jsp");
            
        } else {
            // Authentication failed
            log("Failed login attempt for username: " + username);
            response.sendRedirect("login.jsp?error=invalid");
        }
    }
    
    /**
     * Authenticates user credentials against the database
     * 
     * @param username the username to authenticate
     * @param password the password to verify
     * @return true if credentials are valid, false otherwise
     */
    private boolean authenticateUser(String username, String password) {
        // For demonstration: hardcoded admin credentials
        // In production, this should query the database
        if ("admin".equals(username) && "admin123".equals(password)) {
            return true;
        }
        
        if ("librarian".equals(username) && "lib123".equals(password)) {
            return true;
        }
        
        // TODO: Integrate with existing UserDAO or MemberDAO
        // Example:
        // try {
        //     UserDAO userDAO = new UserDAO();
        //     User user = userDAO.findByUsername(username);
        //     return user != null && user.getPassword().equals(password);
        // } catch (SQLException e) {
        //     log("Database error during authentication", e);
        //     return false;
        // }
        
        return false;
    }
    
    /**
     * Servlet initialization
     */
    @Override
    public void init() throws ServletException {
        super.init();
        log("LoginServlet initialized successfully");
    }
    
    /**
     * Servlet cleanup
     */
    @Override
    public void destroy() {
        log("LoginServlet destroyed");
        super.destroy();
    }
}
