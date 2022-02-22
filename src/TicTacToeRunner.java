import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JOptionPane;

public class TicTacToeRunner extends JFrame
{
    JPanel mainPnl;
    JPanel statsPnl; //top
    JPanel gamePnl; //mid
    JPanel controlPnl; //bottom
    
    JLabel currentPlayerLbl;

    JButton quitBtn;


    //array for three possible moves
    ArrayList<String> move = new ArrayList<>();

    private static final int ROW = 3;
    private static final int COL = 3;
    private static JButton[][] board = new JButton[ROW][COL];

    //stats displayed
    int playerWins = 0;
    int computerWins = 0;
    int ties = 0;
    String currentPlayer = "X";



    public TicTacToeRunner()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        createStatsPanel();
        mainPnl.add(statsPnl, BorderLayout.NORTH);

        createDisplayPanel();
        mainPnl.add(gamePnl, BorderLayout.CENTER);

        createControlPanel();
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        TicTacToe.clearBoard();
    }

    private void createStatsPanel()
    {
        statsPnl = new JPanel();
        currentPlayerLbl = new JLabel("Current Player:" + currentPlayer + " ");

        statsPnl.add(currentPlayerLbl);
    }

    private void createDisplayPanel()
    {
        gamePnl = new JPanel();
        gamePnl.setLayout(new GridLayout(3, 3));
        createButtons();
    }

    private void createControlPanel()
    {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 1));

        quitBtn = new JButton("Quit");
        controlPnl.add(quitBtn);
        quitBtn.addActionListener(ActiveEvent_ae -> System.exit(0));
    }

    public void createButtons()
    {
        for(int row = 0; row <=2; row++)
        {
            for(int col = 0; col <= 2; col++)
            {
                board[row][col] = new TicTacToeTile(row, col);
                gamePnl.add(board[row][col]);
                board[row][col].setText(" ");
                board[row][col].setBackground(Color.white);
                board[row][col].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        TicTacToeTile buttonClicked = (TicTacToeTile) e.getSource();

                        JFrame frame = new JFrame("JOptionPane");
                        if (!buttonClicked.getText().isBlank()) {
                            // Show the error msg option pane
                            JOptionPane.showMessageDialog(frame, "This move is not valid");
                            return;
                        }


                        buttonClicked.setText(String.valueOf(currentPlayer));

                        // update the board in the tictactoe java class
                        TicTacToe.updateBoard(currentPlayer, buttonClicked.getRow(), buttonClicked.getCol());

                        // check for win condition
                        if (TicTacToe.isWin(currentPlayer))
                        {
                            JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins! Play again!");
                            TicTacToe.clearBoard();
                            for(int row = 0; row <=2; row++) {
                                for (int col = 0; col <= 2; col++) {
                                    board[row][col].setText(" ");
                                }
                            }
                            currentPlayer = "O";
                            currentPlayerLbl.setText("Current Player:" + currentPlayer + " ");
                        }

                        // check for tie as well and do processing on that
                        if (TicTacToe.isTie())
                        {
                            JOptionPane.showMessageDialog(frame, "The game is a tie! Play again!");
                            TicTacToe.clearBoard();
                            for(int row = 0; row <=2; row++) {
                                for (int col = 0; col <= 2; col++) {
                                    board[row][col].setText(" ");
                                }
                            }
                            currentPlayer = "O";
                            currentPlayerLbl.setText("Current Player:" + currentPlayer + " ");
                        }

                            if (currentPlayer == "X") {
                                currentPlayer = "O";
                            } else {
                                currentPlayer = "X";
                            }
                            currentPlayerLbl.setText("Current Player:" + currentPlayer + " ");
                    }
                });
            }
        }
    }
}
