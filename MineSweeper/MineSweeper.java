
import java.util.Random;
import java.util.Scanner;

class SetGameBoard
{
    static char[][] MineSweeperBoard;
    private int n;
    Random rand=new Random();

    public SetGameBoard(int n)
    {
        this.n=n;
        MineSweeperBoard = new char[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                MineSweeperBoard[i][j]='0';
            }
        }
        CreatBoard();
        OutPutBoard();
        printBoard();
    }
    public void CreatBoard()
    {
        int x,y;
        for(int i=0;i<n;i++)
        {
            x=rand.nextInt(n);
            y=rand.nextInt(n);
            if(MineSweeperBoard[x][y]!='*')
            {
                MineSweeperBoard[x][y]='*';
            }
            else
            {
                i--;
            }
        }
    }
    public void OutPutBoard()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(MineSweeperBoard[i][j]!='*')
                    MineSweeperBoard[i][j]=SetOutput(i,j);
            }
        }
    }
    public char SetOutput(int x,int y)
    {
        char count='0';
        
            count = MineCheckOut(count,x-1,y);      //North
            count = MineCheckOut(count,x+1,y);      //South
            count = MineCheckOut(count,x,y-1);      //West
            count = MineCheckOut(count,x,y+1);      //East
            count = MineCheckOut(count,x-1,y+1);    //North-East
            count = MineCheckOut(count,x+1,y+1);    //South-East
            count = MineCheckOut(count,x+1,y-1);    //South-West
            count = MineCheckOut(count,x-1,y-1);    //North-West
        
        return count;
    }
    public char MineCheckOut(char count,int x,int y)
    {
        if(((0<=x&&x<=n-1)&&(0<=y&&y<=n-1)) && MineSweeperBoard[x][y]=='*')
            ++count;
        return count;
    }
 
        

    public void printBoard()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(MineSweeperBoard[i][j]+" ");
            }
            System.out.println();
        }
    }
}




/*--------------------------------------------------------------------------------------------------------------------------- */
/*--------------------------------------------------------------------------------------------------------------------------- */


class GamePlay
{
    private int n;
    private char[][] GamePlayBoard;
    public GamePlay(int n)
    {
        this.n=n;
        GamePlayBoard= new char[n][n];
         for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                GamePlayBoard[i][j]='-'; 
            }
        }
        
    }
    
    public void playing(int x,int y)
    {
        if(SetGameBoard.MineSweeperBoard[x][y]=='*')
        {
            Lose();
            printBoard();
            System.out.println("######                 YOU                 ######");
            System.out.println("######                 LOST                ######");
            System.out.println("######                 THE                 ######");
            System.out.println("######                 GAME                ######");
            System.out.println();
            System.out.println();
            System.exit(0);
        }

        else if(SetGameBoard.MineSweeperBoard[x][y]>'0')
        {
            GamePlayBoard[x][y]=SetGameBoard.MineSweeperBoard[x][y];
            printBoard();
        }
        else
        {
            boolean[][] visit=new boolean[n][n];
            BackTrack(x,y,visit,n); 
            printBoard();
        }

    }
    public void BackTrack(int x,int y,boolean[][] visit,int n)
    {
       System.out.println("hi");
        if(!((0<=x&&x<=n-1)&&(0<=y&&y<=n-1)))
            return;

        
        if(!(visit[x][y]))
        {
                if((SetGameBoard.MineSweeperBoard[x][y]!='0'))
                    GamePlayBoard[x][y]=SetGameBoard.MineSweeperBoard[x][y];
                return;
            
        }

        visit[x][y]=true;
        GamePlayBoard[x][y]=SetGameBoard.MineSweeperBoard[x][y];
        BackTrack(x-1,y,visit,n);   //North
        BackTrack(x,y+1,visit,n);   //East
        BackTrack(x+1,y,visit,n);  //South
        BackTrack(x,y-1,visit,n);  //west 
        BackTrack(x-1,y+1,visit,n);    //North-East
        BackTrack(x+1,y+1,visit,n);    //South-East
        BackTrack(x+1,y-1,visit,n);    //South-West
        BackTrack(x-1,y-1,visit,n);    //North-West 

        visit[x][y]=false; 

    }    
    public void Lose()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(SetGameBoard.MineSweeperBoard[i][j]=='*')
                {
                    GamePlayBoard[i][j]=SetGameBoard.MineSweeperBoard[i][j];
                }
            }
        }
    }  
    public boolean VictoryCheck()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(SetGameBoard.MineSweeperBoard[i][j]!='*')
                {
                    if(GamePlayBoard[i][j]==SetGameBoard.MineSweeperBoard[i][j])
                        continue;
                    else
                        return false;
                }
            }
        }
        return true;
    }

    public void printBoard()
    {
        System.out.print("     ");
        
        for(int i=0;i<n;i++)
            System.out.print(i+" ");
        System.out.println();
        System.out.println();

        for(int i=0;i<n;i++)
        {
            System.out.print(i+"    ");
            for(int j=0;j<n;j++)
            {
                System.out.print(GamePlayBoard[i][j]+" ");
            }
            System.out.println();
        }
    }

}

public class MineSweeper
{
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        SetGameBoard g=new SetGameBoard(9);

        GamePlay play=new GamePlay(9);
        play.printBoard();
        
        while(true)
        {
            System.out.print("Enter the Row and Column :  ");
            int x=in.nextInt();
            int y=in.nextInt();

            play.playing(x,y);

            if(play.VictoryCheck())
            {
                System.out.println("==================================================");
                System.out.println("##################################################");
                System.out.println();
                System.out.println();
                System.out.println("  ******          YOU WON THE GAME         ******   ");
                System.out.println();
                System.out.println();
                System.out.println("##################################################");
                System.out.println("==================================================");

            }
        }


     
    }
    static int[][] temp=new int[1][2];
}