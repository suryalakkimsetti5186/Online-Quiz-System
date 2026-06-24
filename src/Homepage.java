import javax.swing.*;

public class Homepage extends JFrame {

    JButton b1, b2;
    JLabel l;

    Homepage() {

        setLayout(null);

        l = new JLabel("ONLINE QUIZ SYSTEM");
        l.setBounds(350, 150, 300, 40);
        add(l);

        b1 = new JButton("Admin");
        b1.setBounds(350, 300, 120, 50);
        add(b1);

        b1.addActionListener(e -> {
            new AdminLogin();
            dispose();
        });

        b2 = new JButton("Student");
        b2.setBounds(530, 300, 120, 50);
        add(b2);

        b2.addActionListener(e -> {
            new StudentLogin();
            dispose();
        });

        setTitle("Online Quiz System");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}