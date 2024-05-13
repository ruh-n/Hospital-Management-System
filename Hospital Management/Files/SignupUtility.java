package Files;

import java.io.*;
import java.util.Scanner;

public class SignupUtility {
    private static final String SIGNUP_FILE = "Files/Signup.txt";

    public static boolean validAccount(String user, String pass) {
        boolean flag = false;
        try {
            File file = new File(SIGNUP_FILE);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] value = line.split("\t"); // Split the line by tab
                if (value[0].equals(user) && value[1].equals(pass)) {
                    flag = true;
                    break;
                }
            }
            scanner.close(); // Close the Scanner
        } catch (IOException e) {
            e.printStackTrace(); // Print the exception stack trace
        }
        return flag;
    }
}