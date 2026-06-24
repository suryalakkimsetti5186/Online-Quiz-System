import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteQuestion extends JFrame implements ActionListener {

    JButton delete, back, view;
    JLabel title, text;
    JTextField tf;

    DeleteQuestion() {

        setLayout(null);

        title = new JLabel("DELETE QUESTION");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(280, 50, 350, 40);
        add(title);

        text = new JLabel("Enter Question ID:");
        text.setBounds(180, 180, 200, 30);
        add(text);

        tf = new JTextField();
        tf.setBounds(380, 180, 150, 30);
        add(tf);

        delete = new JButton("Delete");
        delete.setBounds(180, 300, 120, 40);
        add(delete);

        delete.addActionListener(this);

        view = new JButton("View Questions");
        view.setBounds(340, 300, 150, 40);
        add(view);

        view.addActionListener(e -> {
            new ViewQuestions();
            dispose();
        });

        back = new JButton("Back");
        back.setBounds(530, 300, 120, 40);
        add(back);

        back.addActionListener(e -> {
            new AdminPanel();
            dispose();
        });

        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            if (tf.getText().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Enter Question ID");

                return;
            }

            int id = Integer.parseInt(tf.getText());

            Connection conn = DBConnection.connect();

            PreparedStatement check =
                    conn.prepareStatement(
                            "select qid from questions where qid=?"
                    );

            check.setInt(1, id);

            ResultSet rs = check.executeQuery();

            if (!rs.next()) {

                JOptionPane.showMessageDialog(this,
                        "Question ID Not Found");

                return;
            }

            PreparedStatement del =
                    conn.prepareStatement(
                            "delete from questions where qid=?"
                    );

            del.setInt(1, id);

            del.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Question Deleted Successfully");

            tf.setText("");

            rs.close();
            check.close();
            del.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}