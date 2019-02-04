import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.Stack;
import java.util.ArrayList;
public class GridMaker
{
    public int width = 0;
    public int height = 0;
    public int[][] board;
    public JButton[][] grid;
    public int[][] flagval;
    public int[][] check;
    public int click = 0;
    public GridMaker(int _width, int _height)
    {
        width = _width;
        height = _height;
        grid = new JButton[width][height];
        board = new int[width][height];
        flagval = new int[width][height];
        check = new int[width][height];
    }
    int runval = 0;
    JFrame frame;
    public void run()
    {
        //GridMaker barss = new GridMaker(30,30);
        mineGen();        
        gridGen();
        setFlagVal();
        if(runval < 0)
        {
            frame = new JFrame();
            GridMaker barss = new GridMaker(30,30);
            barss.run();
        }
        runval++;
    }
    //JFrame frame = new JFrame();
    public void gridGen()
    {
       frame = new JFrame();
        frame.setVisible(false);  
        frame.dispose();
        JFrame frame = new JFrame();
       //JButton[][] grid = new JButton[width][height];
       frame.setLayout(new GridLayout(width,height));
       ImageIcon imgs = new ImageIcon("block.png");
       Image img = imgs.getImage() ;
       Image newimg = img.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH );
       ImageIcon icon = new ImageIcon(newimg);
       for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < width; j++)
            {
                int temp = board[i][j];
                int x = i;
                int y = j;
                //JButton showDialogButton = new JButton("Text Button");
                
                //JButton tempbutton = new JButton(("Te
                grid[x][y] = new JButton((""));
                //grid[x][y].setIcon(new ImageIcon("C:\\Users\\200801057\\Desktop\\Projects\\game\\bomb.png"));
                
                grid[x][y].setIcon(imgs);
                //grid[x][y] = new JButton((temps));
                grid[x][y].addActionListener(new ActionListener()
                {
                  public void actionPerformed(ActionEvent e)
                  {
                    
                    grid[x][y].setBackground(Color.white);
                    //grid[x][y].setForeground(Color.red);
                    onClick(x,y);
                    
                    //gridRemake(x, y);
                  }
                });
                frame.add(grid[i][j]);
            }
        }
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       frame.pack();
       frame.setTitle("MineSweeper");
       frame.setSize(1920,1040);
       frame.setVisible(true);   
        
    }
    int bombnum = 0;
    public void mineGen()
    {
        Random numGen = new Random();
        int temp;
        int rows, column;
        final int NO_MINE = 0;
        final int BOMB_VALUE = 99;
        final int NUM_OF_BLOCKS = width * height;
        final int NUM_OF_MINES = (165 + numGen.nextInt(200 - 165 + 1)) ;

        for (int j = 0; j < NUM_OF_MINES; j++) {
            rows = numGen.nextInt(width);
            column = numGen.nextInt(height);
            if((numGen.nextInt(5)) >= 2)
            {
                board[rows][column] = 1;
                bombnum++;
            }
            else
            {
                board[rows][column] = 0;
                //click++;
            }
        }
        //oclick = click;
    }
    public void setFlagVal()
    {
        for(int i = 0; i < flagval.length; i++)
       {
            for(int j = 0; j < flagval.length; j++)
            {
                int flag = bombCheck(i, j);
                flagval[i][j] = flag;
                String temp =  Integer.toString(flag);
                grid[i][j].setText(temp);
                //System.out.println(temp);
            }
        }
    }
    Stack<JButton> stack = new Stack<JButton>();
    Stack<JButton> stacks = new Stack<JButton>();
    ImageIcon imgss = new ImageIcon("afterclick.png");
    public void recur(int x, int y)
    {
            
            //System.out.println("Score: " + (oclick - click) + " Click: " + click);
            if ( x < 0 || x > flagval.length - 1 || y < 0 || y > flagval.length - 1)
            {
                return;
            }
            if(flagval[x][y] > 0)
            {
                    grid[x][y].setBackground(Color.yellow);
                    grid[x][y].setIcon(null);
                    
                    return;
            }
            else if(flagval[x][y] == 0 && board[x][y] == 0)
            {
                //grid[x][y].setIcon(imgss);
                //grid[x][y].doClick();
                grid[x][y].setBackground(Color.white);
                grid[x][y].setIcon(null);
                
                if (check[x][y] == 0)
                {
                   check[x][y] = 1;
                   //grid[x][y].setBackground(Color.yellow);
                   //grid[x][y].setIcon(imgss);
                   //grid[x][y].setBackground(Color.white);
                   //grid[x][y].setIcon(null);
                   recur( x + 1, y );
                   recur( x - 1, y );
                   recur( x, y - 1 );
                   recur( x, y + 1 );
                   
                }
                
            }
            
            /*
            if ( flagval[x][y] == 0 && check[x][y] == 0 && board[x][y] == 0)
            {
                   check[x][y] = 1;
                   //grid[x][y].setBackground(Color.yellow);
                   //grid[x][y].setIcon(imgss);
                   grid[x][y].setBackground(Color.white);
                   grid[x][y].setIcon(null);
                   recur( x + 1, y );
                   recur( x - 1, y );
                   recur( x, y - 1 );
                   recur( x, y + 1 );
                }
                else
                {
                    return;
                }
            */
        
    }

    public int bombCheck(int row, int column)
    {
        int neigh = 0;
        
        if(((row == 0) && (column == 0)))
        {
            
                if(board[row][column + 1] != 0)
                    neigh++;
                if(board[row + 1][column + 1]!= 0)
                    neigh++;
                if(board[row + 1][column]!= 0)
                    neigh++;
                return neigh;
            
        }
        else if ((column == board[0].length - 1) && (row == board.length - 1))
        {
            
                if(board[row][column - 1] != 0)
                    neigh++;
                if(board[row - 1][column - 1]!= 0)
                    neigh++;
                if(board[row - 1][column]!= 0)
                    neigh++;
                return neigh;
            
           
        }
        else if ((column == 0) || (column == board[0].length - 1))
        {
           if ((column == 0))    
                {
                    if(board[row - 1][column] != 0)
                        neigh++;
                    if(board[row - 1][column + 1]!= 0)
                        neigh++;
                    if(board[row][column + 1]!= 0)
                        neigh++;
                    
                    return neigh;
                }
                
                else 
                {
                    if(board[row + 1][column] != 0)
                        neigh++;
                    if(board[row + 1][column - 1]!= 0)
                        neigh++;
                    if(board[row][column - 1]!= 0)
                        neigh++;
                    
                    return neigh;
                }
               
        }
        else if ((column >= 1) && (column != board[0].length - 29))
        {
            
            if((column == 0))    
            {
                 if(board[row - 1][column - 1] != 0)
                    neigh++;
                if(board[row - 1][column] != 0)
                    neigh++;
               if(board[row - 1][column + 1]!= 0)
                    neigh++;
               if(board[row][column - 1] != 0)
                    neigh++;
               if(board[row][column + 1]!= 0)
                    neigh++;
               return neigh;
             }   
             else if (row == 0)
            {
                if(board[row + 1][column + 1] != 0)
                    neigh++;
               if(board[row + 1][column] != 0)
                        neigh++;
               if(board[row + 1][column - 1]!= 0)
                        neigh++;
               if(board[row][column + 1] != 0)
                        neigh++;
               if(board[row][column - 1]!= 0)
                        neigh++;
                   return neigh;
            }
            else if((row == board[0].length - 1))
            {
               
               if(board[row - 1][column - 1] != 0)
                    neigh++;
               if(board[row - 1][column] != 0)
                        neigh++;
               if(board[row - 1][column + 1]!= 0)
                        neigh++;
               if(board[row][column - 1] != 0)
                        neigh++;
               if(board[row][column + 1]!= 0)
                        neigh++;
                   return neigh;
            }
            else if((row >= 1) && (row < board[0].length - 1))
            {
                if(board[row - 1][column - 1] != 0)
                    neigh++;
                 if(board[row - 1][column] != 0)
                        neigh++;
                   if(board[row - 1][column + 1]!= 0)
                        neigh++;
                   if(board[row][column - 1] != 0)
                        neigh++;
                   if(board[row][column + 1]!= 0)
                        neigh++;
                   if(board[row + 1][column - 1]!= 0)
                        neigh++;
                   if(board[row + 1 ][column]!= 0)
                        neigh++;
                   if(board[row + 1][column + 1]!= 0)
                        neigh++;
                   return neigh;
            }
            
            else
            {
               int x = row;
               int y = column;
               if(board[row - 1][column - 1] != 0)
                    neigh++;
               if(board[row - 1][column] != 0)
                        neigh++;
               if(board[row - 1][column + 1]!= 0)
                        neigh++;
               if(board[row][column - 1] != 0)
                        neigh++;
               if(board[row][column + 1]!= 0)
                        neigh++;
                   /*
                        if(board[row - 1][column - 1]!= 0)
                        neigh++;
                   if(board[row - 1 ][column]!= 0)
                        neigh++;
                   if(board[row - 1][column + 1]!= 0)
                        neigh++;
                    */
                   return neigh;
            }
        }
        return neigh;
    }
    public String toString()
    {
        
      String result = "";
        
        for (int i = 0; i < board.length; i++)
      {
          result += (i +"[ ");
          for(int j = 0; j < board[i].length; j++)
          {
              result += " " + flagval[i][j] + " ";
          }
          result += " ]";
          result += "\n";
      }
       return result; 

    }
    int timee = 0;
    long startTime;
    
    public void onClick(int x, int y)
    {
        
        if(timee == 0)
        {
            startTime = System.nanoTime();
            timee++;
        }
        if(board[x][y] != 0) 
        {
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
                long milly = duration/1000000;
                long secs = milly/1000;
                int secss = (int) secs;
                System.out.println("Score (Seconds): " + secs);
            bomb(x, y);
            
            String[] options = {"Restart",
                    "Quit",
                    "Look at the board"};
                    int n = JOptionPane.showOptionDialog(frame,
                    "YOU HIT A NUKE",
                    "LOSER LOSER",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);
                    if(n == 0)
                    {
                        
                        JOptionPane.showMessageDialog(frame, "Re-Run it for a Prpoper Restart");
                        frame.setVisible(false);
                        frame.dispose();
                        System.exit(0);
                    }
                    else if( n == 1)
                    {
                        System.exit(0);
                        
                    }
            
        }
        else
        {
            //bomb(x, y);
            
            //click--;
            int checker = 0;
            for(int i = 0; i < width; i++)
            {
                for(int j = 0; j < width; j++)
                {
                    if(check[x][y] == 1)
                    {
                        checker++;
                    }
                }
            }
            if(checker == (900 - bombnum))
            {
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                long milly = duration/1000000;
                long secs = milly/1000;
                System.out.println("Score (Seconds): " + secs);
                String[] options = {"Restart",
                    "Quit"};
                    int n = JOptionPane.showOptionDialog(frame,
                    "YOU Win",
                    "WINNER",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
                   if(n == 0)
                    {
                        
                        JOptionPane.showMessageDialog(frame, "Re-Run it for a Prpoper Restart");
                        frame.setVisible(false);
                        frame.dispose();
                    }
                    else if( n == 1)
                    {
                        System.exit(0);
                        
                    }
            }
            goodclick( x , y);
            checker = 0;
        }
    }
    public void bomb(int x, int y)
    {
        frame.setLayout(new GridLayout(width,height));
        ImageIcon imgs = new ImageIcon("bomb.png");
        Image img = imgs.getImage() ;
        Image newimg = img.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH );
        ImageIcon icon = new ImageIcon(newimg);
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < width; j++)
            {
                
                if(board[i][j] != 0 )
                {
                    grid[i][j].setBackground(Color.gray);
                    grid[i][j].setForeground(Color.gray);
                    grid[i][j].setIcon(icon);
                }
                if(((i == x) && (j == y)))
                {
                    grid[i][j].setBackground(Color.red);
                    grid[i][j].setForeground(Color.red);
                    grid[i][j].setIcon(icon);
                }
            }   
        }
    }
    public void goodclick(int x, int y)
    {   
        //ImageIcon imgs = new ImageIcon("afterclick.png");
        //String temp = String.valueOf(flagval[x][y]);
        //grid[x][y].setIcon(imgs);
        //grid[x][y].setText(temp);
        //recurflag(x, y);
        
        recur(x, y);
    }
    
}
