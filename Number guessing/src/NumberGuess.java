import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuess {
    private int randomNumber;
    private int attempts;
    private JLabel hintLabel;
    private boolean hintVisible = false; // Hint Initially Hidden

    public NumberGuess() {
        // Random number generate (1 to 100)
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attempts = 0;

        // JFrame (Window)
        JFrame frame = new JFrame("🎲 Number Guessing Game");
        frame.setSize(500, 450); // बड़ा Page
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(220, 235, 250)); // Light Blue Background
        frame.setLocationRelativeTo(null); // Center में Open होगा

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Title Label
        JLabel titleLabel = new JLabel("Guess a number (1-100)!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(40, 40, 40));

        // Input Field
        JTextField inputField = new JTextField(10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 18));

        // Guess Button
        JButton guessButton = new JButton("🎯 Guess"); // 🎯 Emoji
        guessButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
        guessButton.setBackground(new Color(42, 56, 59)); // Light Blue Button
        guessButton.setForeground(Color.WHITE);
        guessButton.setFocusPainted(false);

        // Message Label
        JLabel messageLabel = new JLabel("Start guessing...");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Cheat Mode Button (Toggle Show/Hide)
        JButton cheatButton = new JButton("🔍 Show Hint"); // 🔍 Emoji
        cheatButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        cheatButton.setBackground(new Color(255, 150, 100)); // Orange Button
        cheatButton.setForeground(Color.WHITE);
        cheatButton.setFocusPainted(false);

        // Cheat Mode (Hint Label - Hidden by Default)
        hintLabel = new JLabel("The Secret Number is: " + randomNumber);
        hintLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        hintLabel.setForeground(Color.RED);
        hintLabel.setVisible(false); // Hidden Initially

        // Guess Button Action
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int userGuess = Integer.parseInt(inputField.getText());
                    attempts++;

                    if (userGuess < randomNumber) {
                        messageLabel.setText("📉 Too Low! Try again."); // 📉 Emoji
                    } else if (userGuess > randomNumber) {
                        messageLabel.setText("📈 Too High! Try again."); // 📈 Emoji
                    } else {
                        messageLabel.setText("✨ Correct! You guessed it in " + attempts + " attempts!"); // ✨ Emoji
                        guessButton.setEnabled(false);
                    }
                } catch (NumberFormatException ex) {
                    messageLabel.setText("❌ Please enter a valid number!"); // ❌ Emoji
                }
                inputField.setText("");
            }
        });

        // Cheat Button Action (Show/Hide Hint)
        cheatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hintVisible = !hintVisible; // Toggle Hint Visibility
                hintLabel.setVisible(hintVisible);
                cheatButton.setText(hintVisible ? "🙈 Hide Hint" : "🔍 Show Hint"); // Button Text Change
            }
        });

        // Layout Management
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        frame.add(titleLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        frame.add(inputField, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        frame.add(guessButton, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        frame.add(messageLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        frame.add(cheatButton, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        frame.add(hintLabel, gbc); // Hidden Initially

        // Show Frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new NumberGuess();
    }
}
