package QuizApp;
import javax.swing.*;

public class QuizFrame extends JFrame {
    private QuizService service = new QuizService();

    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton nextButton;
    private ButtonGroup group;

    public QuizFrame() {
        setTitle("Quiz App");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        questionLabel = new JLabel();
        questionLabel.setBounds(20, 20, 350, 30);
        add(questionLabel);

        options = new JRadioButton[4];
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBounds(20, 60 + (i * 30), 300, 30);
            group.add(options[i]);
            add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.setBounds(150, 200, 100, 30);
        add(nextButton);

        loadQuestion();

        nextButton.addActionListener(e -> {
            int selected = getSelectedOption();
            service.checkAnswer(selected);

            if (service.hasNext()) {
                service.nextQuestion();
                loadQuestion();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Your Score: " + service.getScore());
            }
        });

        setVisible(true);
    }

    private void loadQuestion() {
        Question q = service.getCurrentQuestion();
        questionLabel.setText(q.getQuestion());

        for (int i = 0; i < 4; i++) {
            options[i].setText(q.getOptions()[i]);
        }

        group.clearSelection();

        // Timer
        new TimerThread(10, () -> {
            JOptionPane.showMessageDialog(this, "Time Up!");
        }).start();
    }

    private int getSelectedOption() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) return i;
        }
        return -1;
    }
}