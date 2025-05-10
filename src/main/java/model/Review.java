package model;

public class Review {
    private String id;
    private String username;
    private String targetType;
    private String targetId;
    private int rating;
    private String comment;
    private String date;

    public Review(String id, String username, String targetType, String targetId, int rating, String comment, String date) {
        this.id = id;
        this.username = username;
        this.targetType = targetType;
        this.targetId = targetId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getTargetType() { return targetType; }
    public String getTargetId() { return targetId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public String getDate() { return date; }

    public String toFileString() {
        return id + "," + username + "," + targetType + "," + targetId + "," + rating + "," + comment.replace(",", " ") + "," + date;
    }
    public String getTarget() {
        return this.id; // or return target;
    }


    public static Review fromFileString(String line) {
        String[] parts = line.split(",", 7);
        return new Review(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), parts[5], parts[6]);
    }
}