import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.event.*;

public class StudentResults extends JFrame implements ActionListener {

    DefaultTableModel model, spec;
    JTable table, table2;
    JButton submit, back, logout;
    JTextField tf;
    JLabel l;
    JScrollPane sp, sp2;

    StudentResults() {

        setLayout(null);

        String[] col = {
                "Student Id",
                "Student Name",
                "Score",
                "Total Questions",
                "Date Attempted",
                "Attempted Questions"
        };

        // Main Table
        model = new DefaultTableModel(col, 0);
        table = new JTable(model);

        sp = new JScrollPane(table);
        sp.setBounds(30, 30, 1250, 250);
        add(sp);

        try {

            Connection conn = DBConnection.connect();

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                    "select * from student_results"
            );

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getInt("score"),
                        rs.getInt("total_questions"),
                        rs.getDate("quiz_date"),
                        rs.getInt("attempted_questions")
                });
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Search Section
        l = new JLabel("Enter Student ID:");
        l.setBounds(50, 320, 150, 30);
        add(l);

        tf = new JTextField();
        tf.setBounds(200, 320, 180, 30);
        add(tf);

        submit = new JButton("Search");
        submit.setBounds(420, 320, 100, 30);
        add(submit);

        submit.addActionListener(this);

        // Student Specific Table
        spec = new DefaultTableModel(col, 0);

        table2 = new JTable(spec);

        sp2 = new JScrollPane(table2);
        sp2.setBounds(30, 380, 1250, 180);
        add(sp2);

        // Back Button
        back = new JButton("Back");
        back.setBounds(450, 620, 120, 40);
        add(back);

        back.addActionListener(e -> {
            new AdminPanel();
            dispose();
        });

        // Logout Button
        logout = new JButton("Logout");
        logout.setBounds(650, 620, 120, 40);
        add(logout);

        logout.addActionListener(e -> {
            new Homepage();
            dispose();
        });

        setTitle("Student Results");
        setSize(1350, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            spec.setRowCount(0);

            String id = tf.getText().trim();

            if (id.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please Enter Student ID"
                );

                return;
            }

            Connection conn = DBConnection.connect();

            PreparedStatement pst = conn.prepareStatement(
                    "select * from student_results where student_id=?"
            );

            pst.setString(1, id);

            ResultSet rs = pst.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                spec.addRow(new Object[]{
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getInt("score"),
                        rs.getInt("total_questions"),
                        rs.getDate("quiz_date"),
                        rs.getInt("attempted_questions")
                });
            }

            if (!found) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student ID Not Found"
                );
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new StudentResults();
    }
}