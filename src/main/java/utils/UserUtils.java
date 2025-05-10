
package utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class UserUtils {
    private static final String USERS_FILE = "WEB-INF/data/users.txt";

    public static boolean validateUser(String username, String password) {
        try {
            String filePath = Paths.get(System.getProperty("catalina.base"), "webapps", "your-app-name", USERS_FILE).toString();
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean registerUser(String username, String password) {
        try {
            if (userExists(username)) {
                return false;
            }

            String filePath = Paths.get(System.getProperty("catalina.base"), "webapps", "your-app-name", USERS_FILE).toString();
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(username + ":" + password + "\n");
            bw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean userExists(String username) {
        try {
            String filePath = Paths.get(System.getProperty("catalina.base"), "webapps", "your-app-name", USERS_FILE).toString();
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length > 0 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

