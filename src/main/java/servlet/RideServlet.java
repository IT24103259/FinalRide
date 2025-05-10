
package servlet;

import model.Ride;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/RideServlet")
public class RideServlet extends HttpServlet {
    private static final String FILE_PATH = "C:\\Users\\LOQ\\Desktop\\di bike\\dibike\\data\\rides.txt"; // Update if needed

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "offer":
                offerRide(request, response);
                break;
            case "delete":
                deleteRide(request, response);
                break;
            case "update":
                updateRide(request, response);
                break;
            default:
                response.sendRedirect("listRides.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Ride> rides = loadRides();
        request.setAttribute("rideList", rides);
        request.getRequestDispatcher("listRides.jsp").forward(request, response);
    }

    private void offerRide(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = UUID.randomUUID().toString();
        String driver = request.getParameter("driver");
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        String date = request.getParameter("date");
        int seats = Integer.parseInt(request.getParameter("seats"));
        double price = Double.parseDouble(request.getParameter("price"));
        String status = "active";

        Ride ride = new Ride(id, driver, origin, destination, date, seats, price, status);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(ride.toFileString());
            bw.newLine();
        }

        response.sendRedirect("RideServlet");
    }

    private void deleteRide(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        List<Ride> rides = loadRides();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Ride ride : rides) {
                if (!ride.getId().equals(id)) {
                    bw.write(ride.toFileString());
                    bw.newLine();
                }
            }
        }

        response.sendRedirect("RideServlet");
    }
    private void updateRide(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        String driver = request.getParameter("driver");
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        String date = request.getParameter("date");
        int seats = Integer.parseInt(request.getParameter("seats"));
        double price = Double.parseDouble(request.getParameter("price"));
        String status = request.getParameter("status");

        List<Ride> rides = loadRides();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Ride ride : rides) {
                if (ride.getId().equals(id)) {
                    // update this ride
                    ride = new Ride(id, driver, origin, destination, date, seats, price, status);
                }
                bw.write(ride.toFileString());
                bw.newLine();
            }
        }

        response.sendRedirect("RideServlet");
    }

    private List<Ride> loadRides() throws IOException {
        List<Ride> rides = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                rides.add(Ride.fromFileString(line));
            }
        }
        return rides;
    }
}
