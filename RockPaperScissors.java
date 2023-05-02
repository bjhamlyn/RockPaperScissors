import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RockPaperScissors extends JFrame 
{
    //declare components for the game
    JPanel panel;
    JButton rockButton, paperButton, scissorsButton, resetButton;
    JLabel gameLabel, playerLabel, vsLabel, computerLabel, playerScoreLabel, computerScoreLabel;
    int playerScore, computerScore;
    //set the font to recognize Korean text
    Font koreaFont = new Font("Malgun Gothic", Font.PLAIN, 12);
    Font font = new Font("Malgun Gothic", Font.BOLD, 14);
    Font gameFont = new Font("Malgun Gothic", Font.BOLD, 20);
    Random rand = new Random();

    //set the main method to start the game
    public static void main(String[] args) 
    {
        RockPaperScissors game = new RockPaperScissors();
    }

    //method to handle button clicks
    public void ButtonClicked(ActionEvent e) 
    {
        //get the button that was clicked
        JButton button = (JButton) e.getSource();
        String playerChoice = button.getText();
        //create an array of possible choices (rock, paper, scissors)
        String[] choices = 
        {"Rock (바위)", "Paper (보)", "Scissors (가위)"};
        //generate random choices for the computer
        String computerChoice = choices[rand.nextInt(choices.length)];

        //methods to check for game outcomes and to display the results of each game
        if (playerChoice.equals(computerChoice)) {
            JOptionPane.showMessageDialog(panel, "It's a tie (그것은 넥타이)! ");
        } else if ((playerChoice.equals("Rock (바위)") && computerChoice.equals("Scissors (가위)"))
                || (playerChoice.equals("Scissors (가위)") && computerChoice.equals("Paper (보)"))
                || (playerChoice.equals("Paper (보)") && computerChoice.equals("Rock (바위)"))) {
            playerScore++;
            playerScoreLabel.setText("Player (플레이어): " + playerScore);
            UIManager.put("OptionPane.messageFont", new Font("Malgun Gothic", Font.BOLD, 14));
            UIManager.put("OptionPane.buttonFont", new Font("Malgun Gothic", Font.BOLD, 14));
            JOptionPane.showMessageDialog(panel, "You win (네가 이겼다)! " + playerChoice + " beats " + computerChoice);
        } else {
            computerScore++;
            computerScoreLabel.setText("Computer (컴퓨터): " + computerScore);
            UIManager.put("OptionPane.messageFont", new Font("Malgun Gothic", Font.BOLD, 14));
            UIManager.put("OptionPane.buttonFont", new Font("Malgun Gothic", Font.BOLD, 14));
            JOptionPane.showMessageDialog(panel, "You lose (당신은 패배)! " + computerChoice + " beats " + playerChoice);
        }
    }

    //ctor to initialize the game
    public RockPaperScissors() {
        panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        setContentPane(panel);

        //set the layout design to GridBagLayout for simplicity of GridLayout, but with more control
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();

        /*The GridBagLayout uses the grid layout indexes to assign position.
        for example, a 3x3 grid would assign the columns indexes of 0, 1, 2 from left to right.
        It des the same for the Rows. Then the gridx, and gridy are used to assign a position to 
        an item within the grid. An item with gridx = 0; gridy = 0; would be placed in the top left
        of the grid.  
        */

        // Create and configure the Game Label
        gameLabel = new JLabel("Rock Paper Scissors (가위 바위 보)");
        gameLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 24));
        gameLabel.setForeground(Color.BLUE);
        gameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 10, 0);
        panel.add(gameLabel, c);

        // create and configure the VS Label
        vsLabel = new JLabel("vs");
        vsLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
        vsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        panel.add(vsLabel, c);
        
        //create and configure the player score label
        playerScore = 0;
        playerScoreLabel = new JLabel("Player (플레이어): ");
        playerScoreLabel.setText("Player (플레이어): " + playerScore);
        playerScoreLabel.setHorizontalAlignment(SwingConstants.LEFT);
        playerScoreLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 0.5;
        panel.add(playerScoreLabel, c);
        
        //create and configure the computer score label
        computerScore = 0;
        computerScoreLabel = new JLabel(": Computer (컴퓨터)");
        computerScoreLabel.setText(computerScore + ": Computer (컴퓨터)");
        computerScoreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        computerScoreLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_END;
        panel.add(computerScoreLabel, c);
        
        //create the rock button
        rockButton = new JButton("Rock (바위)");
        rockButton.addActionListener(e -> ButtonClicked(e));
        rockButton.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        panel.add(rockButton, c);
        
        //create the paper button
        paperButton = new JButton("Paper (보)");
        paperButton.addActionListener(e -> ButtonClicked(e));
        paperButton.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
        c.gridx = 1;
        panel.add(paperButton, c);
        
        //create the scissors button
        scissorsButton = new JButton("Scissors (가위)");
        scissorsButton.addActionListener(e -> ButtonClicked(e));
        scissorsButton.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
        c.gridx = 2;
        panel.add(scissorsButton, c);
        
        //create the reset button
        resetButton = new JButton("Reset (초기화)");
        resetButton.addActionListener(e -> ResetGame());
        resetButton.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
        resetButton.setBackground(Color.BLACK);
        resetButton.setForeground(Color.RED);
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(resetButton, c);

        //set up the frame
        setTitle("Rock Paper Scissors (가위 바위 보)");
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void ResetGame()
    {
        //reset scores to 0
        playerScore = 0;
        computerScore = 0;

        //update score labels
        playerScoreLabel.setText("Player: " + playerScore);
        computerScoreLabel.setText("Computer: " + computerScore);

        //enable buttons
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);
        scissorsButton.setEnabled(true);
    }
}

