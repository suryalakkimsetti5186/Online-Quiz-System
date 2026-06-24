import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminLogin extends JFrame implements ActionListener {

    JTextField tf1;
    JPasswordField tf2;
    JLabel l1, l2, l3;
    JButton b;

    AdminLogin() {

        setLayout(null);

        l1 = new JLabel("ADMIN LOGIN");
        l1.setFont(new Font("Arial", Font.BOLD, 30));
        l1.setBounds(360, 120, 300, 50);
        add(l1);

        l2 = new JLabel("Username:");
        l2.setFont(new Font("Arial", Font.BOLD, 16));
        l2.setBounds(280, 260, 100, 30);
        add(l2);

        tf1 = new JTextField();
        tf1.setBounds(400, 260, 220, 35);
        add(tf1);

        l3 = new JLabel("Password:");
        l3.setFont(new Font("Arial", Font.BOLD, 16));
        l3.setBounds(280, 340, 100, 30);
        add(l3);

        tf2 = new JPasswordField();
        tf2.setBounds(400, 340, 220, 35);
        add(tf2);

        b = new JButton("Login");
        b.setFont(new Font("Arial", Font.BOLD, 16));
        b.setBounds(450, 450, 120, 45);
        add(b);

        b.addActionListener(this);

        setTitle("Admin Login");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String[] user = {"surya", "harika", "navya"};
        String[] password = {"2007", "2006", "2007"};

        String username = tf1.getText();
        String pass = new String(tf2.getPassword());

        boolean valid = false;

        for (int i = 0; i < user.length; i++) {

            if (user[i].equals(username) &&
                    password[i].equals(pass)) {

                valid = true;
                break;
            }
        }

        if (valid) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login Successful"
            );

            new AdminPanel();
            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Username or Password"
            );
        }
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}