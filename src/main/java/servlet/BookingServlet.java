
package servlet;

import model.Booking;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final String FILE_PATH = "C:\\Users\\LOQ\\Desktop\\di bike\\dibike\\data\\bookings.txt"; // Update path if needed

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "book":
                createBooking(request, response);
                break;
            case "cancel":
                cancelBooking(request, response);
                break;
            default:
                response.sendRedirect("myBookings.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");

        List<Booking> allBookings = loadBookings();
        List<Booking> userBookings = new ArrayList<>();

        for (Booking b : allBookings) {
            if (b.getUsername().equals(username)) {
                userBookings.add(b);
            }
        }

        request.setAttribute("bookingList", userBookings);
        request.getRequestDispatcher("myBookings.jsp").forward(request, response);
    }

    private void createBooking(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String bookingId = UUID.randomUUID().toString();
        String username = request.getParameter("username");
        String rideId = request.getParameter("rideId");
        int seats = Integer.parseInt(request.getParameter("seats"));
        double total = Double.parseDouble(request.getParameter("total"));
        String paymentMethod = request.getParameter("paymentMethod");

        Booking booking = new Booking(bookingId, username, rideId, seats, total, "confirmed", paymentMethod);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(booking.toFileString());
            bw.newLine();
        }

        response.sendRedirect("BookingServlet?username=" + username);
    }

    private void cancelBooking(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String bookingId = request.getParameter("bookingId");
        String username = request.getParameter("username");

        List<Booking> bookings = loadBookings();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Booking b : bookings) {
                if (b.getBookingId().equals(bookingId)) {
                    b = new Booking(b.getBookingId(), b.getUsername(), b.getRideId(), b.getSeatsBooked(),
                            b.getTotalAmount(), "cancelled", b.getPaymentMethod());
                }
                bw.write(b.toFileString());
                bw.newLine();
            }
        }

        response.sendRedirect("BookingServlet?username=" + username);
    }

    private List<Booking> loadBookings() throws IOException {
        List<Booking> bookings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                bookings.add(Booking.fromFileString(line));
            }
        }
        return bookings;
    }
}
