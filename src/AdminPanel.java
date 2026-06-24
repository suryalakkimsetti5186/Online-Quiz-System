import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JFrame {

    JButton addb, delete, view, result, logout;
    JLabel title;

    AdminPanel() {

        setLayout(null);

        title = new JLabel("ADMIN PANEL");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(330, 50, 300, 40);
        add(title);

        addb = new JButton("Add Question");
        addb.setBounds(320, 140, 220, 50);
        add(addb);

        addb.addActionListener(e -> {
            new AddQuestions();
            dispose();
        });

        delete = new JButton("Delete Question");
        delete.setBounds(320, 230, 220, 50);
        add(delete);

        delete.addActionListener(e -> {
            new DeleteQuestion();
            dispose();
        });

        view = new JButton("View Questions");
        view.setBounds(320, 320, 220, 50);
        add(view);

        view.addActionListener(e -> {
            new ViewQuestions();
            dispose();
        });

        result = new JButton("View Results");
        result.setBounds(320, 410, 220, 50);
        add(result);

        result.addActionListener(e -> {
            new StudentResults();
            dispose();
        });

        logout = new JButton("Logout");
        logout.setBounds(320, 500, 220, 50);
        add(logout);

        logout.addActionListener(e -> {
            new Homepage();
            dispose();
        });

        setTitle("Admin Panel");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminPanel();
    }
}