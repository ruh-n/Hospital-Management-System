package Files;
import java.io.*;
import java.util.Scanner;

public class SignupIO {
    private static final String SIGNUP_FILE = "Files/yxy.txt";

    public static void addAccount(String user, String pass) {
        try {
            File file = new File(SIGNUP_FILE);
            FileWriter fwriter = new FileWriter(file, true); // Append mode
            fwriter.write(user + "\t" + pass + "\n"); // Write user and pass separated by a tab and newline
            fwriter.flush(); // Flush the data to the file
            fwriter.close(); // Close the FileWriter
        } catch (IOException e) {
            e.printStackTrace(); // Print the exception stack trace
        }
    }

    public static boolean checkAccount(String user) {
        boolean flag = false;
        try {
            File file = new File(SIGNUP_FILE);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] value = line.split("\t"); // Split the line by tab
                if (value[0].equals(user)) {
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