
package model;

public class Ride {
    private String id;
    private String driverUsername;
    private String origin;
    private String destination;
    private String date;
    private int seats;
    private double price;
    private String status;

    public Ride(String id, String driverUsername, String origin, String destination, String date, int seats, double price, String status) {
        this.id = id;
        this.driverUsername = driverUsername;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.seats = seats;
        this.price = price;
        this.status = status;
    }

    public String getId() { return id; }
    public String getDriverUsername() { return driverUsername; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getDate() { return date; }
    public int getSeats() { return seats; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }

    public void setSeats(int seats) { this.seats = seats; }
    public void setStatus(String status) { this.status = status; }

    public String toFileString() {
        return id + "," + driverUsername + "," + origin + "," + destination + "," + date + "," + seats + "," + price + "," + status;
    }

    public static Ride fromFileString(String line) {
        String[] parts = line.split(",");
        return new Ride(parts[0], parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5]), Double.parseDouble(parts[6]), parts[7]);
    }
}
