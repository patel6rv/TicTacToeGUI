
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wulft
 */
public class TicTacToe 
{

    private static final int ROW = 3;
    private static final int COL = 3;
    public static String[][] board = new String[ROW][COL];

    public static void clearBoard()
    {
       // sets all the board elements to a space
       for(int row=0; row < ROW; row++)
       {
           for(int col=0; col < COL; col++)
           {
               board[row][col] = " ";
           }
       }    
    }

    public static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        
        return false;
    }

    private static boolean isColWin(String player)
    {
       // checks for a col win for specified player
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals(player) &&
               board[1][col].equals(player) &&     
               board[2][col].equals(player))
            {
                return true;
            }                
        }
        return false; // no col win
    }

    private static boolean isRowWin(String player)
    {
       // checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals(player) &&
               board[row][1].equals(player) &&     
               board[row][2].equals(player))
            {
                return true;
            }                
        }
        return false; // no row win        
    }

    private static boolean isDiagonalWin(String player)
    {
       // checks for a diagonal win for the specified player
        
        if(board[0][0].equals(player) &&
           board[1][1].equals(player) &&    
           board[2][2].equals(player) )
        {
            return true;
        } 
        if(board[0][2].equals(player) &&
           board[1][1].equals(player) &&    
           board[2][0].equals(player) )
        {
            return true;
        }
        return false;
    }
    
    // checks for a tie before board is filled.
    // check for the win first to be efficient
    public static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so 
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals("X") || 
               board[row][1].equals("X") ||
               board[row][2].equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].equals("O") || 
               board[row][1].equals("O") ||
               board[row][2].equals("O"))
            {
                oFlag = true; // there is an O in this row
            }
            
            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }
            
            xFlag = oFlag = false;
            
        }
        // Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals("X") || 
               board[1][col].equals("X") ||
               board[2][col].equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].equals("O") || 
               board[1][col].equals("O") ||
               board[2][col].equals("O"))
            {
                oFlag = true; // there is an O in this col
            }
            
            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }    
        // Now check for the diagonals
        xFlag = oFlag = false;
        
        if(board[0][0].equals("X") ||
           board[1][1].equals("X") ||    
           board[2][2].equals("X") )
        {
            xFlag = true;
        } 
        if(board[0][0].equals("O") ||
           board[1][1].equals("O") ||    
           board[2][2].equals("O") )
        {
            oFlag = true;
        } 
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }        
        xFlag = oFlag = false;        
        
        if(board[0][2].equals("X") ||
           board[1][1].equals("X") ||    
           board[2][0].equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].equals("O") ||
           board[1][1].equals("O") ||    
           board[2][0].equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }        

        // Checked every vector so I know I have a tie
        return true;
    }

    /**
     * Update board at specific row and col for a specific player
     *
     * @param player
     * @param row
     * @param col
     */
    public static void updateBoard(String player, int row, int col) {
        board[row][col] = player;
    }
}
