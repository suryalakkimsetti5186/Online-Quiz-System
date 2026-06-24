import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddQuestions extends JFrame implements ActionListener {

    JLabel title, l1, l2, l3, l4, l5, l6;
    JTextField question, opt1, opt2, opt3, opt4, corr;
    JButton save, back;

    AddQuestions() {

        setLayout(null);

        title = new JLabel("ADD QUESTION");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(350, 20, 300, 40);
        add(title);

        l1 = new JLabel("Question:");
        l1.setBounds(150, 100, 150, 30);
        add(l1);

        question = new JTextField();
        question.setBounds(350, 100, 400, 35);
        add(question);

        l2 = new JLabel("Option 1:");
        l2.setBounds(150, 160, 150, 30);
        add(l2);

        opt1 = new JTextField();
        opt1.setBounds(350, 160, 400, 35);
        add(opt1);

        l3 = new JLabel("Option 2:");
        l3.setBounds(150, 220, 150, 30);
        add(l3);

        opt2 = new JTextField();
        opt2.setBounds(350, 220, 400, 35);
        add(opt2);

        l4 = new JLabel("Option 3:");
        l4.setBounds(150, 280, 150, 30);
        add(l4);

        opt3 = new JTextField();
        opt3.setBounds(350, 280, 400, 35);
        add(opt3);

        l5 = new JLabel("Option 4:");
        l5.setBounds(150, 340, 150, 30);
        add(l5);

        opt4 = new JTextField();
        opt4.setBounds(350, 340, 400, 35);
        add(opt4);

        l6 = new JLabel("Correct Option:");
        l6.setBounds(150, 400, 150, 30);
        add(l6);

        corr = new JTextField();
        corr.setBounds(350, 400, 400, 35);
        add(corr);

        save = new JButton("Save");
        save.setBounds(300, 500, 120, 40);
        add(save);

        back = new JButton("Back");
        back.setBounds(500, 500, 120, 40);
        add(back);

        save.addActionListener(this);

        back.addActionListener(e -> {
            new AdminPanel();
            dispose();
        });

        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {

            Connection conn = DBConnection.connect();

            String sql =
                    "insert into questions(question_text,option1,option2,option3,option4,correctopt) values(?,?,?,?,?,?)";

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, question.getText());
            pst.setString(2, opt1.getText());
            pst.setString(3, opt2.getText());
            pst.setString(4, opt3.getText());
            pst.setString(5, opt4.getText());
            pst.setString(6, corr.getText());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Question Saved Successfully");

            question.setText("");
            opt1.setText("");
            opt2.setText("");
            opt3.setText("");
            opt4.setText("");
            corr.setText("");

            pst.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}