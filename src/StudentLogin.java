import javax.swing.*;
import java.awt.*;

public class StudentLogin extends JFrame {
    StudentLogin() {
        setLayout(null);

        JLabel l1 = new JLabel("Student Credentials");
        l1.setBounds(380, 150, 300, 50);
        l1.setFont(new Font("Arial", Font.BOLD, 28));
        add(l1);

        // Student ID
        JLabel l2 = new JLabel("Student ID:");
        l2.setBounds(300, 250, 100, 30);
        add(l2);

        JTextField tfId = new JTextField();
        tfId.setBounds(450, 250, 150, 30);
        add(tfId);

        // Student Name (new field)
        JLabel l3 = new JLabel("Name:");
        l3.setBounds(300, 300, 100, 30);
        add(l3);

        JTextField tfName = new JTextField();
        tfName.setBounds(450, 300, 150, 30);
        add(tfName);

        // Submit button
        JButton b = new JButton("Submit");
        b.setBounds(380, 500, 100, 50);
        add(b);

        b.addActionListener(e -> {

            String studentId = tfId.getText();
            String studentName = tfName.getText();

            if(studentId.isEmpty() || studentName.isEmpty()){
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields");
                return;
            }

            new QuizForm(studentId, studentName);
            dispose();
        });

        setTitle("Student Login");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
