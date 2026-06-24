import javax.swing.*;
import java.sql.*;

public class ResultForm extends JFrame {

    JButton finish;
    JLabel title;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;

    ResultForm(String id, String name, int score, int k, int n) {

        setLayout(null);

        try {

            Connection conn = DBConnection.connect();

            PreparedStatement pst = conn.prepareStatement(
                    "insert into student_results(student_id,name,score,total_questions,quiz_date,attempted_questions) values(?,?,?,?,?,?)"
            );

            pst.setString(1, id);
            pst.setString(2, name);
            pst.setInt(3, score);
            pst.setInt(4, n);
            pst.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            pst.setInt(6, k);

            pst.executeUpdate();

            pst.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        title = new JLabel("QUIZ RESULT");
        title.setBounds(220, 40, 250, 40);
        title.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        add(title);

        l1 = new JLabel("Student ID:");
        l1.setBounds(120, 120, 120, 30);
        add(l1);

        l4 = new JLabel(id);
        l4.setBounds(280, 120, 200, 30);
        add(l4);

        l2 = new JLabel("Student Name:");
        l2.setBounds(120, 180, 120, 30);
        add(l2);

        l5 = new JLabel(name);
        l5.setBounds(280, 180, 200, 30);
        add(l5);

        l3 = new JLabel("Score:");
        l3.setBounds(120, 240, 120, 30);
        add(l3);

        l6 = new JLabel(score + "/" + n);
        l6.setBounds(280, 240, 200, 30);
        add(l6);

        l7 = new JLabel("Attempted:");
        l7.setBounds(120, 300, 120, 30);
        add(l7);

        l8 = new JLabel(k + "/" + n);
        l8.setBounds(280, 300, 200, 30);
        add(l8);

        finish = new JButton("Finish");
        finish.setBounds(230, 400, 120, 40);
        add(finish);

        finish.addActionListener(e -> {
            new Homepage();
            dispose();
        });

        setTitle("Result");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ResultForm("23IT001", "Surya", 8, 10, 10);
    }
}