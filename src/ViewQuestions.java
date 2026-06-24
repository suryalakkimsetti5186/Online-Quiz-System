import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewQuestions extends JFrame {

    DefaultTableModel model;
    JTable table;
    JButton refresh, back;
    JScrollPane sp;

    ViewQuestions() {

        setLayout(null);

        String[] cols = {
                "QID",
                "Question",
                "Option1",
                "Option2",
                "Option3",
                "Option4",
                "Correct"
        };

        model = new DefaultTableModel(cols, 0);

        table = new JTable(model);

        sp = new JScrollPane(table);
        sp.setBounds(50, 50, 950, 450);
        add(sp);

        loadQuestions();

        refresh = new JButton("Refresh");
        refresh.setBounds(350, 550, 120, 40);
        add(refresh);

        refresh.addActionListener(e -> loadQuestions());

        back = new JButton("Back");
        back.setBounds(550, 550, 120, 40);
        add(back);

        back.addActionListener(e -> {
            new AdminPanel();
            dispose();
        });

        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void loadQuestions() {

        try {

            model.setRowCount(0);

            Connection conn = DBConnection.connect();

            Statement st = conn.createStatement();

            ResultSet rs =
                    st.executeQuery("select * from questions");

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("qid"),
                        rs.getString("question_text"),
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4"),
                        rs.getString("correctopt")
                });
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}