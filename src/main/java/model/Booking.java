
package model;

public class Booking {
    private String bookingId;
    private String username;
    private String rideId;
    private int seatsBooked;
    private double totalAmount;
    private String status;
    private String paymentMethod;

    public Booking(String bookingId, String username, String rideId, int seatsBooked, double totalAmount, String status, String paymentMethod) {
        this.bookingId = bookingId;
        this.username = username;
        this.rideId = rideId;
        this.seatsBooked = seatsBooked;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public String getBookingId() { return bookingId; }
    public String getUsername() { return username; }
    public String getRideId() { return rideId; }
    public int getSeatsBooked() { return seatsBooked; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public String getPaymentMethod() { return paymentMethod; }

    public String toFileString() {
        return bookingId + "," + username + "," + rideId + "," + seatsBooked + "," + totalAmount + "," + status + "," + paymentMethod;
    }

    public static Booking fromFileString(String line) {
        String[] parts = line.split(",");
        return new Booking(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4]), parts[5], parts[6]);
    }
}
