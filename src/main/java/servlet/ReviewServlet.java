package servlet;

import model.Review;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
    private static final String FILE_PATH = "C:\\Users\\LOQ\\Desktop\\di bike\\dibike\\reviews.txt"; // Update if needed

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("submit".equals(action)) {
            submitReview(request, response);
        } else if ("delete".equals(action)) {
            deleteReview(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Review> reviews = loadReviews();
        request.setAttribute("reviewList", reviews);
        request.getRequestDispatcher("viewReviews.jsp").forward(request, response);
    }

    private void submitReview(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = UUID.randomUUID().toString();
        String username = request.getParameter("username");
        String targetType = request.getParameter("targetType");
        String targetId = request.getParameter("targetId");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Review review = new Review(id, username, targetType, targetId, rating, comment, date);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(review.toFileString());
            bw.newLine();
        }

        response.sendRedirect("ReviewServlet");
    }

    private void deleteReview(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        List<Review> reviews = loadReviews();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Review r : reviews) {
                if (!r.getId().equals(id)) {
                    bw.write(r.toFileString());
                    bw.newLine();
                }
            }
        }

        response.sendRedirect("ReviewServlet");
    }

    private List<Review> loadReviews() throws IOException {
        List<Review> reviews = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                reviews.add(Review.fromFileString(line));
            }
        }
        return reviews;
    }
}