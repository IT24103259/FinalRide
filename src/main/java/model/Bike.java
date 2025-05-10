
package model;

public class Bike {
    private String id;
    private String name;
    private String type; // "Electric" or "Regular"
    private double pricePerHour;
    private boolean available;

    public Bike(String id, String name, String type, double pricePerHour, boolean available) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pricePerHour = pricePerHour;
        this.available = available;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(double pricePerHour) { this.pricePerHour = pricePerHour; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String toFileString() {
        return id + "," + name + "," + type + "," + pricePerHour + "," + available;
    }

    public static Bike fromFileString(String line) {
        String[] parts = line.split(",");
        return new Bike(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), Boolean.parseBoolean(parts[4]));
    }
}
