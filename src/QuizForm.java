import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.sql.*;

public class QuizForm extends JFrame implements ActionListener {

    JLabel questionNo;
    JLabel question, l1;

    JRadioButton op1;
    JRadioButton op2;
    JRadioButton op3;
    JRadioButton op4;

    ButtonGroup bg;

    JButton next, previous, submit;

    ArrayList<String> save, ques, opt1, opt2, opt3, opt4, corropt;
    ArrayList<Integer> qno;

    String id1 = "", name1 = "";

    int score = 0, comp = 0, att = 0;

    QuizForm(String id, String name) {

        id1 = id;
        name1 = name;

        try {

            Connection conn = DBConnection.connect();

            PreparedStatement pst =
                    conn.prepareStatement("select * from questions");

            ResultSet rs = pst.executeQuery();

            ques = new ArrayList<>();
            opt1 = new ArrayList<>();
            opt2 = new ArrayList<>();
            opt3 = new ArrayList<>();
            opt4 = new ArrayList<>();
            corropt = new ArrayList<>();
            qno = new ArrayList<>();

            while (rs.next()) {

                qno.add(rs.getInt("qid"));
                ques.add(rs.getString("question_text"));

                opt1.add(rs.getString("option1"));
                opt2.add(rs.getString("option2"));
                opt3.add(rs.getString("option3"));
                opt4.add(rs.getString("option4"));

                corropt.add(rs.getString("correctopt"));
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        save = new ArrayList<>();

        for (int i = 0; i < qno.size(); i++) {
            save.add("");
        }

        setLayout(null);

        l1 = new JLabel("ONLINE QUIZ EXAMINATION");
        l1.setFont(new Font("Arial", Font.BOLD, 28));
        l1.setBounds(280, 40, 500, 40);
        add(l1);

        questionNo = new JLabel();
        questionNo.setFont(new Font("Arial", Font.BOLD, 18));
        questionNo.setBounds(100, 120, 300, 30);
        add(questionNo);

        question = new JLabel();
        question.setFont(new Font("Arial", Font.BOLD, 18));
        question.setBounds(100, 180, 700, 40);
        add(question);

        op1 = new JRadioButton();
        op1.setBounds(120, 260, 500, 30);

        op2 = new JRadioButton();
        op2.setBounds(120, 320, 500, 30);

        op3 = new JRadioButton();
        op3.setBounds(120, 380, 500, 30);

        op4 = new JRadioButton();
        op4.setBounds(120, 440, 500, 30);

        add(op1);
        add(op2);
        add(op3);
        add(op4);

        bg = new ButtonGroup();

        bg.add(op1);
        bg.add(op2);
        bg.add(op3);
        bg.add(op4);

        previous = new JButton("Previous");
        previous.setBounds(180, 550, 120, 40);
        add(previous);

        next = new JButton("Next");
        next.setBounds(380, 550, 120, 40);
        add(next);

        submit = new JButton("Submit");
        submit.setBounds(580, 550, 120, 40);
        add(submit);

        previous.addActionListener(this);
        next.addActionListener(this);
        submit.addActionListener(this);

        previous.setEnabled(false);

        showQuestion();

        setTitle("Quiz Form");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void showQuestion() {

        questionNo.setText(
                "Question " + (comp + 1) + " of " + qno.size()
        );

        question.setText(ques.get(comp));

        op1.setText(opt1.get(comp));
        op2.setText(opt2.get(comp));
        op3.setText(opt3.get(comp));
        op4.setText(opt4.get(comp));

        bg.clearSelection();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == next) {

            if (comp < qno.size() - 1) {

                saveAnswers();

                comp++;

                showQuestion();
            }

            previous.setEnabled(comp > 0);
            next.setEnabled(comp < qno.size() - 1);
        }

        else if (e.getSource() == previous) {

            if (comp > 0) {

                saveAnswers();

                comp--;

                showQuestion();
            }

            previous.setEnabled(comp > 0);
            next.setEnabled(true);
        }

        else if (e.getSource() == submit) {

            saveAnswers();

            score = 0;

            for (int i = 0; i < qno.size(); i++) {

                if (save.get(i).equals(corropt.get(i))) {
                    score++;
                }

                if (!save.get(i).equals("")) {
                    att++;
                }
            }

            new ResultForm(
                    id1,
                    name1,
                    score,
                    att,
                    qno.size()
            );

            dispose();
        }
    }

    public void saveAnswers() {

        if (op1.isSelected())
            save.set(comp, op1.getText());

        else if (op2.isSelected())
            save.set(comp, op2.getText());

        else if (op3.isSelected())
            save.set(comp, op3.getText());

        else if (op4.isSelected())
            save.set(comp, op4.getText());
    }
}