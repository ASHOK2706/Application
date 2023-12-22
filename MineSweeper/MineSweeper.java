
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
    
    //Stack stack=new Stack(1000);
    //int[][] a=new int[1][2];
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
            int[][] visit=new int[n][n];
            printZero(x,y,visit,n);  

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                /*if(visit[i][j]==1)
                {
                    GamePlayBoard[i][j]=SetGameBoard.MineSweeperBoard[i][j];
                }*/
               // System.out.print(visit[i][j]+" ");
            }
            System.out.println();
        }


          //  AssignValue(visit);
            
            printBoard();
        }

    }
    public void printZero(int x,int y,int[][] visit,int n)
    {
       
        if(visit[x][y]==1)
            return;
        if(((0<=x&&x<=n-1)&&(0<=y&&y<=n-1)) &&   (SetGameBoard.MineSweeperBoard[x][y]!=0))
        {
                visit[x][y]=1;
                return;
            
        }
        if(!((0<=x&&x<=n-1)&&(0<=y&&y<=n-1)))
            return;

        visit[x][y]=1;

        printZero(x-1,y,visit,n);   //North
        printZero(x,y+1,visit,n);   //East
        printZero(x+1,y,visit,n);  //South
        printZero(x,y-1,visit,n);  //west   

    }
    public void AssignValue(int[][] visit)
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(visit[i][j]==1)
                {
                    GamePlayBoard[i][j]=SetGameBoard.MineSweeperBoard[i][j];
                }
            }
        }
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