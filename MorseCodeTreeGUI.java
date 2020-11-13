import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MorseCodeTreeGUI extends JFrame {
    private JTextField morseCodeField;
    private JTextField translationField;
    private JButton translateButton;
    private JButton showMorseAlphabetButton;

    public static void main(String[] args) {
        JFrame frame = new MorseCodeTreeGUI();
        frame.setVisible(true);
    }

    public MorseCodeTreeGUI() {
        super("Morse Code Translator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        // Set the layout manager to grid layout
        contentPane.setLayout(new GridLayout(3,2));
        contentPane.add(new JLabel("Morse Code"));
        contentPane.add(new JLabel("Translation"));
        morseCodeField = new JTextField(15);
        translationField = new JTextField(15);
        contentPane.add(morseCodeField);
        contentPane.add(translationField);
        translateButton = new JButton("Translate");
        contentPane.add(translateButton);
        translateButton.addActionListener(new NewTranslation());
        showMorseAlphabetButton = new JButton("Display Morse Code Alphabet");
        contentPane.add(showMorseAlphabetButton);
        showMorseAlphabetButton.addActionListener(new MorseAlphabet());

        pack();
    }
    private class MorseAlphabet implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MorseCodeTree mct = new MorseCodeTree();
            String[] morseAlphabet = {"*-", "-***", "-*-*", "-**", "*", "**-*", "--*", "****", "**", "*---", "-*-",
            "*-**", "--", "-*", "---", "*--*", "--*-", "*-*", "***", "-", "**-", "***-", "*--", "-**-", "-*--", "--**"};
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < morseAlphabet.length / 2; i++) {
                int length = morseAlphabet[i].length();
                int spaces = 8 - length + 10;

                sb.append(mct.decode(morseAlphabet[i]) + " = " + String.format("%1$-" + spaces + "s", morseAlphabet[i])
                        + mct.decode(morseAlphabet[i + 13]) + " = " + morseAlphabet[i + 13] + "\n");
            }

            JOptionPane.showMessageDialog(null, sb);
        }
    }

    private class NewTranslation implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MorseCodeTree mct = new MorseCodeTree();
            String message = morseCodeField.getText();
            String translation = mct.decode(message);
            translationField.setText(translation);
        }
    }


}
