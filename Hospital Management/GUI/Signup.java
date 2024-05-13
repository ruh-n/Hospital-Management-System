package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Signup extends JFrame implements ActionListener {
    JLabel userNameLabel, userPassLabel, signupLabel;
    JPanel panel;
    JTextField userName;
    JPasswordField password;
    JButton btnSignup;

    Font font = new Font("Bookman Old Style", Font.BOLD, 20);
    Font font2 = new Font("Bookman Old Style", Font.BOLD, 40);

    private File file;
    private FileWriter fwriter; //for insert in the file
    private Scanner scanner; //will scan the text file

    public Signup() {
        super("Signup");
        this.setSize(700, 450);
        this.setLocation(400, 200);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.cyan);

        btnSignup = new JButton("Signup");
        btnSignup.setBounds(425, 260, 180, 40);
        btnSignup.setFont(font);
        btnSignup.setForeground(Color.WHITE);
        btnSignup.setBackground(new Color(76, 141, 245));
        btnSignup.addActionListener(this);
        this.add(btnSignup);

        // Panel Container
        panel = new JPanel();
        panel.setBounds(0, 0, 700, 450);
        panel.setOpaque(false);
        panel.setLayout(null);
        this.add(panel);

        // Window Icon
        ImageIcon icon = new ImageIcon("./GUI/Resource/Hospital.jpg");
        Image WImage = icon.getImage();
        this.setIconImage(WImage);

        // Welcome Label
        signupLabel = new JLabel("Signup");
        signupLabel.setBounds(470, 30, 180, 50);
        signupLabel.setFont(font2);
        this.add(signupLabel);

        // USER NAME Label
        userNameLabel = new JLabel("User Name");
        userNameLabel.setBounds(390, 80, 180, 40);
        userNameLabel.setFont(font);
        this.add(userNameLabel);

        // USER NAME TextField
        userName = new JTextField();
        userName.setBounds(390, 120, 240, 40);
        userName.setFont(font);
        this.add(userName);

        // User Password Label
        userPassLabel = new JLabel("Password");
        userPassLabel.setBounds(390, 170, 150, 40);
        userPassLabel.setFont(font);
        this.add(userPassLabel);

        // User Password Password Field
        password = new JPasswordField();
        password.setBounds(390, 210, 240, 40);
        password.setEchoChar('*');
        password.setFont(font);
        this.add(password);

        panel.add(userNameLabel);
        panel.add(userPassLabel);
        panel.add(signupLabel);

        // Images
        ImageIcon image = new ImageIcon("./GUI/Resource/signup.jpg");
        JLabel background = new JLabel();
        background.setBounds(0, 0, 340, 450);
        background.setIcon(image);
        this.add(background);

        ImageIcon image2 = new ImageIcon("./GUI/Resource/b_bg2.png");
        JLabel background2 = new JLabel();
        background2.setBounds(340, 0, 360, 450);
        background2.setIcon(image2);
        this.add(background2);
        // Get the screen dimensions
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       int screenWidth = (int) screenSize.getWidth();
       int screenHeight = (int) screenSize.getHeight();

       // Calculate  center the frame
       int frameWidth = this.getWidth();
       int frameHeight = this.getHeight();
       int x = (screenWidth - frameWidth) / 2;
       int y = (screenHeight - frameHeight) / 2;

       this.setLocation(x, y);

       this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (btnSignup == e.getSource()) {
            String name = userName.getText();
            String pass = String.valueOf(password.getPassword());

            if (name.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill-up all the information");
            } else {
                if (!checkAccount(name)) {
                    addAccount(name, pass);
                    JOptionPane.showMessageDialog(this, "Account created successfully");
                    System.out.println("Closing the Signup window...");
                    this.dispose(); // Close the Signup window
                    new Login(); // Open the Login window
                } else {
                    JOptionPane.showMessageDialog(this, "Account already exists", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    public void addAccount(String user, String pass) {
        try {
            file = new File("Files/Signup.txt"); // Change here
            file.createNewFile(); // Create the file if it doesn't exist
            fwriter = new FileWriter(file, true); // Append mode
            fwriter.write(user + "\t" + pass + "\n"); // Write user and pass separated by a tab and newline
            fwriter.flush(); // Flush the data to the file
            fwriter.close(); // Close the FileWriter
        } catch (IOException e) {
            e.printStackTrace(); // Print the exception stack trace
        }
    }
    
    public boolean checkAccount(String user) {
        boolean flag = false;
        try {
            file = new File("Files/Signup.txt"); // Change here
            scanner = new Scanner(file);
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
        return flag;}
}