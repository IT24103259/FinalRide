package servlet;

import model.Bike;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

@WebServlet("/BikeServlet")
public class BikeServlet extends HttpServlet {
    private static final String FILE_PATH = "C:\\Users\\LOQ\\Desktop\\di bike\\dibike\\data\\bikes.txt"; // Update path

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addBike(request, response);
                break;
            case "update":
                updateBike(request, response);
                break;
            case "delete":
                deleteBike(request, response);
                break;
            default:
                response.sendRedirect("listBikes.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Bike> bikes = loadBikes();
        request.setAttribute("bikeList", bikes);
        request.getRequestDispatcher("listBikes.jsp").forward(request, response);
    }

    private void addBike(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = UUID.randomUUID().toString();
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));
        boolean available = true;

        Bike bike = new Bike(id, name, type, price, available);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(bike.toFileString());
            bw.newLine();
        }

        response.sendRedirect("BikeServlet");
    }

    private void updateBike(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));
        boolean available = Boolean.parseBoolean(request.getParameter("available"));

        List<Bike> bikes = loadBikes();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Bike bike : bikes) {
                if (bike.getId().equals(id)) {
                    bike.setName(name);
                    bike.setType(type);
                    bike.setPricePerHour(price);
                    bike.setAvailable(available);
                }
                bw.write(bike.toFileString());
                bw.newLine();
            }
        }

        response.sendRedirect("BikeServlet");
    }

    private void deleteBike(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id = request.getParameter("id");

        List<Bike> bikes = loadBikes();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Bike bike : bikes) {
                if (!bike.getId().equals(id)) {
                    bw.write(bike.toFileString());
                    bw.newLine();
                }
            }
        }

        response.sendRedirect("BikeServlet");
    }

    private List<Bike> loadBikes() throws IOException {
        List<Bike> bikes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                bikes.add(Bike.fromFileString(line));
            }
        }
        return bikes;
    }
}