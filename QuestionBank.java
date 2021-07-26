/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionbank;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Aseel Al Mohtaseb
 */ 
public class QuestionBank {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataFrame frame = new DataFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.show();

    }

}

class Player {

    private String name;
    private int age;
    private int score;
    private int time;

    public Player(String name, int age, int score, int time) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.time = time;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n Age: " + age + "\n Score: " + score + "/4" + "\n Elapsed time: " + time;
    }

}

//1st Frame
class DataFrame extends JFrame {

    public DataFrame() {
        setTitle("User's Data");
        setSize(WIDTH, HEIGHT);

        setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2); // to put the jframe in the center
        setResizable(false);

        DP = new DataPanel();
        add(DP);

    }

    private DataPanel DP;
    private Toolkit kit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = kit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
}

class DataPanel extends JPanel {

    public DataPanel() {

        this.setLayout(null);

        add(namelab);
        namelab.setFont(new Font("Serif", Font.BOLD, 35));
        namelab.setBounds(115, 50, 110, 40);

        add(nametxt);
        nametxt.setFont(new Font("Serif", Font.BOLD, 35));
        nametxt.setBounds(250, 50, 130, 40);

        add(agelab);
        agelab.setFont(new Font("Serif", Font.BOLD, 35));
        agelab.setBounds(115, 120, 110, 40);

        add(agetxt);
        agetxt.setFont(new Font("Serif", Font.BOLD, 35));
        agetxt.setBounds(250, 120, 130, 40);

        add(okbtn); // when press, show 2nd frame
        okbtn.setFont(new Font("Serif", Font.BOLD, 35));
        okbtn.setBounds(190, 220, 140, 60);

        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                player.setName(nametxt.getText());
                try {
                    player.setAge(Integer.parseInt(agetxt.getText()));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(DataPanel.this, "you should enter your age as a number ");
                    agetxt.setText("");
                    return;
                }
                ButtonFrame frame = new ButtonFrame(player);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.show();
                nametxt.setEditable(false);
                agetxt.setEditable(false);

            }

        });

    }
    private Player player = new Player();
    private final JLabel namelab = new JLabel("Name: ");
    private final JLabel agelab = new JLabel("Age: ");
    private JTextField nametxt = new JTextField();
    private JTextField agetxt = new JTextField();
    private JButton okbtn = new JButton("OK");  // when press, show 2nd frame

}

//2nd frame 
class ButtonFrame extends JFrame {

    public ButtonFrame(Player player) {
        setTitle("Questions");
        setSize(WIDTH, HEIGHT);
        setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2);
        setResizable(false);

        setLayout(new BorderLayout());

        TP = new TimePanel(player);
        add(TP, BorderLayout.SOUTH);

        BP = new ButPanel(player);
        add(BP, BorderLayout.CENTER);

    }

    private ButPanel BP;
    private TimePanel TP;
    private Toolkit kit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = kit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
}

class ButPanel extends JPanel {

    public ButPanel(Player player) {
        this.player = player;
        setLayout(new GridLayout(2, 2, 10, 10));
        Q1btn.setFont(new Font("Serif", Font.BOLD, 45));
        Q2btn.setFont(new Font("Serif", Font.BOLD, 45));
        Q3btn.setFont(new Font("Serif", Font.BOLD, 45));
        Q4btn.setFont(new Font("Serif", Font.BOLD, 45));

        add(Q1btn);
        add(Q2btn);
        add(Q3btn);
        add(Q4btn);

        OpenAction QAction = new OpenAction();

        Q1btn.addActionListener(QAction);
        Q2btn.addActionListener(QAction);
        Q3btn.addActionListener(QAction);
        Q4btn.addActionListener(QAction);

        while (numbers.size() < numOfQues) {

            int random = rand.nextInt(numOfQues);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        index = 0;
        num = numbers.get(index);

    }

    private Player player;
    private JButton Q1btn = new JButton("Q1");
    private JButton Q2btn = new JButton("Q2");
    private JButton Q3btn = new JButton("Q3");
    private JButton Q4btn = new JButton("Q4");
    private String s;
    private static boolean bool;

    private ArrayList<Integer> numbers = new ArrayList<Integer>();// Array with (diffirent) numbers to represent the
    private int index;//index in numbers Array                    // index of a question in QuesArray 
    private int num;//index in QuesArray                          //In order to ensure that the question is not repeated
    private int numOfQues = 10;
    private Random rand = new Random();

    private static int counter = 0; //to count correct anwsers
    private static int answerAll = 0; //to count the questions that were answered

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        if (index < numOfQues) { // to avoid IndexOutOfBoundsException
            this.index = index;
        }
    }

    public static void setBool(boolean bool) {
        ButPanel.bool = bool;
    }

    public static int getAnswerAll() {
        return answerAll;
    }

    public static int getCounter() {
        return counter;
    }

    private class OpenAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(TimePanel.getTimeInt() == 0) //End of time
            {
                Q1btn.setEnabled(false);
                Q2btn.setEnabled(false);
                Q3btn.setEnabled(false);
                Q4btn.setEnabled(false);
            }
            else{
            QuestionFrame frame = new QuestionFrame(ButPanel.this);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.show();
            s = e.getActionCommand();
            }
        }
    }

    public void putColor() {

        if (bool == true) {
            counter++;
            if (s == "Q1") {
                Q1btn.setBackground(Color.GREEN);
                Q1btn.setEnabled(false);
            } else if (s == "Q2") {
                Q2btn.setBackground(Color.GREEN);
                Q2btn.setEnabled(false);
            } else if (s == "Q3") {
                Q3btn.setBackground(Color.GREEN);
                Q3btn.setEnabled(false);
            } else if (s == "Q4") {
                Q4btn.setBackground(Color.GREEN);
                Q4btn.setEnabled(false);
            }
        } else {
            if (s == "Q1") {
                Q1btn.setBackground(Color.RED);
                Q1btn.setEnabled(false);
            } else if (s == "Q2") {
                Q2btn.setBackground(Color.RED);
                Q2btn.setEnabled(false);
            } else if (s == "Q3") {
                Q3btn.setBackground(Color.RED);
                Q3btn.setEnabled(false);
            } else if (s == "Q4") {
                Q4btn.setBackground(Color.RED);
                Q4btn.setEnabled(false);
            }
        }
        num = numbers.get(index);
        answerAll++;
        
    }
}

class TimePanel extends JPanel {

    private String timeString;
    private static int timeInt = 60;
    private JLabel timeLabel = new JLabel();
    private boolean showMessage = true;
   

    public static int getTimeInt() {
        return timeInt;
    }

    public TimePanel(Player player) {
        timeString = String.valueOf(timeInt);
        timeLabel.setFont(new Font("Serif", Font.BOLD, 35));
        timeLabel.setPreferredSize(new Dimension(80, 80));
        add(timeLabel);

        class TimeListener implements ActionListener {

            public void actionPerformed(ActionEvent event) {
                if (ButPanel.getAnswerAll() == 4 || timeInt == 0) {
                    timeLabel.setText(String.valueOf(timeInt)); // In order not to change the value of the time
                     
                        player.setTime(60 - timeInt);
                        player.setScore(ButPanel.getCounter());

                        if (showMessage) {
                            JOptionPane.showMessageDialog(TimePanel.this, player.toString());
                            showMessage = false; // to show message just one time
                        }
                    
                } else {
                    timeInt--;
                }
                timeString = String.valueOf(timeInt);
                timeLabel.setText(timeString);

            }

        }
        ActionListener listener = new TimeListener();
        Timer t = new Timer(1000, listener);
        t.start();
    }
}

//3rd Frame
class QuestionFrame extends JFrame {

    public QuestionFrame(ButPanel BP) {
        this.BP = BP;

        setTitle("Questions");
        setSize(WIDTH, HEIGHT);
        setLocation((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2);
        setResizable(false);
        this.setLayout(null);

        add(q1Label);
        q1Label.setFont(new Font("Serif", Font.BOLD, 30));
        q1Label.setBounds(170, 30, 150, 40);

        add(q2Label);
        q2Label.setFont(new Font("Serif", Font.BOLD, 20));
        q2Label.setBounds(60, 80, 400, 40);

        add(trueBtn);
        trueBtn.setFont(new Font("Serif", Font.BOLD, 30));
        trueBtn.setBounds(100, 180, 120, 50);

        add(falseBtn);
        falseBtn.setFont(new Font("Serif", Font.BOLD, 30));
        falseBtn.setBounds(270, 180, 120, 50);

        str = "E-learning is better than never learn ";
        b = "true";
        quesArray.add(str);
        answerArray.add(b);

        str = "Corona virus is a global pandemic ";
        b = "true";
        quesArray.add(str);
        answerArray.add(b);

        str = "Trump is the best president in the world ";
        b = "false";
        quesArray.add(str);
        answerArray.add(b);

        str = "Earth is flat ";
        b = "false";
        quesArray.add(str);
        answerArray.add(b);

        str = "One is prime number ";
        b = "false";
        quesArray.add(str);
        answerArray.add(b);

        str = "The number of planets in the solar system is 8 ";
        b = "true";
        quesArray.add(str);
        answerArray.add(b);

        str = "No God except Allah ";
        b = "true";
        quesArray.add(str);
        answerArray.add(b);

        str = "You can live without water ";
        b = "false";
        quesArray.add(str);
        answerArray.add(b);

        str = "Death does not exist ";
        b = "false";
        quesArray.add(str);
        answerArray.add(b);

        str = "Dinosaurs are real beings ";
        b = "true";
        quesArray.add(str);
        answerArray.add(b);

        s = quesArray.get(BP.getNum());
        q2Label.setText(s);

        AnswerListener AnswerListener = new AnswerListener();

        trueBtn.addActionListener(AnswerListener);
        falseBtn.addActionListener(AnswerListener);
    }

    class AnswerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == answerArray.get(BP.getNum())) {
                ButPanel.setBool(true);
            } else {
                ButPanel.setBool(false);
            }

            BP.setIndex(BP.getIndex() + 1);
            BP.putColor();
            setVisible(false);

        }

    }
    private ButPanel BP;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private Toolkit kit = Toolkit.getDefaultToolkit();
    private Dimension screenSize = kit.getScreenSize();
    private int screenWidth = screenSize.width;
    private int screenHeight = screenSize.height;

    private JLabel q1Label = new JLabel("Question :");
    private JLabel q2Label = new JLabel();  //item from quesArray
    private JButton trueBtn = new JButton("true");
    private JButton falseBtn = new JButton("false");

    private ArrayList<String> quesArray = new ArrayList();
    private ArrayList<String> answerArray = new ArrayList();

    private String str; //to write questions
    private String b; //bool: true or false

    private String s;

}
