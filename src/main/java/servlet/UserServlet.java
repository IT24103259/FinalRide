
package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final String FILE_PATH = "C:\\Users\\LOQ\\Desktop\\di bike\\dibike\\data\\users.txt"; // Change this to your actual path

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "register":
                registerUser(request, response);
                break;
            case "login":
                loginUser(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            default:
                response.getWriter().println("Unknown action.");
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        User user = new User(username, password, email, role);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(user.toFileString());
            bw.newLine();
        }

        response.sendRedirect("login.jsp");
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        List<User> users = loadUsers();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("profile.jsp");
                return;
            }
        }

        request.setAttribute("error", "Invalid credentials");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");
        String newEmail = request.getParameter("email");
        String newPassword = request.getParameter("password");

        List<User> users = loadUsers();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    user.setEmail(newEmail);
                    user.setPassword(newPassword);
                }
                bw.write(user.toFileString());
                bw.newLine();
            }
        }

        response.sendRedirect("profile.jsp");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String username = request.getParameter("username");

        List<User> users = loadUsers();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                if (!user.getUsername().equals(username)) {
                    bw.write(user.toFileString());
                    bw.newLine();
                }
            }
        }

        response.sendRedirect("login.jsp");
    }

    private List<User> loadUsers() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                users.add(User.fromFileString(line));
            }
        }
        return users;
    }
}
