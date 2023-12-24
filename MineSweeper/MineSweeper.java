
import java.util.Random;
import java.util.Scanner;

class SetGameBoard
{
    static char[][] MineSweeperBoard;
    private int n;
    private int bomb;
     Random rand=new Random();
    public SetGameBoard(int n,int bomb)
    {
        this.n=n;
        this.bomb=bomb;
        System.out.println("\n You need to find "+bomb+" Bomb \n" );
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
        //printBoard();
    }
    public void CreatBoard()
    {
        int x,y;
        for(int i=0;i<bomb;i++)
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
        if(((0 <= x && x <= n-1) && (0 <= y && y <= n-1)) && MineSweeperBoard[x][y]=='*')
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
    
    public void playing(int x,int y,char Ftype)
    {
        if(!((0 <= x && x <= n-1 && 0 <= y && y <= n-1) && (Ftype=='M' || Ftype=='F' || Ftype=='U')) )
        {
            System.out.println("Invalid Input");
        }
        else if(Ftype=='F')
        {
            Flag(x,y);
            printBoard();
        }
        else if(Ftype=='U')
        {
            unFlag(x,y);
            printBoard();
        }
        else if(Ftype=='M')
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
    

    }
    public void BackTrack(int x,int y,boolean[][] visit,int n)
    {
        if (!(0 <= x && x <= n - 1 && 0 <= y && y <= n - 1) || visit[x][y]) {
            return;
        }

        visit[x][y] = true;

        if (SetGameBoard.MineSweeperBoard[x][y] == '0') {
            GamePlayBoard[x][y] = SetGameBoard.MineSweeperBoard[x][y];

            BackTrack(x - 1, y, visit, n);   // North
            BackTrack(x, y + 1, visit, n);   // East
            BackTrack(x + 1, y, visit, n);   // South
            BackTrack(x, y - 1, visit, n);   // West
            BackTrack(x - 1, y + 1, visit, n);    // North-East
            BackTrack(x + 1, y + 1, visit, n);    // South-East
            BackTrack(x + 1, y - 1, visit, n);    // South-West
            BackTrack(x - 1, y - 1, visit, n);    // North-West
        } else {
            GamePlayBoard[x][y] = SetGameBoard.MineSweeperBoard[x][y];
        }
        visit[x][y] = false;
    }    
    public void Flag(int x,int y)
    {
        if(GamePlayBoard[x][y]=='-')
            GamePlayBoard[x][y]='F';
        else if(GamePlayBoard[x][y]=='F')
            System.out.println("Already Flaged");
        else
            System.out.println("Invalied Operation ");
    }
    public void unFlag(int x,int y)
    {
        if(GamePlayBoard[x][y]=='F')
            GamePlayBoard[x][y]='-';
        else 
            System.out.println("Invalied Operation or Alreagy Un Flaged");
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
    public void VictoryFlag()
    {
         for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(SetGameBoard.MineSweeperBoard[i][j]=='*')
                {
                    GamePlayBoard[i][j]='F';
                }
            }
        }
    }
    public boolean isFlagAndMineSame()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(SetGameBoard.MineSweeperBoard[i][j]=='*')
                {
                    if(GamePlayBoard[i][j]=='F')
                        continue;
                    else
                        return false;
                }
            }
        }
        return true;
    }
    public void AssignFullBoard()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(GamePlayBoard[i][j]=='-')
                {
                    GamePlayBoard[i][j]=SetGameBoard.MineSweeperBoard[i][j];
                }
            }
        }
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
        System.out.println();
        System.out.println();
    }

}

public class MineSweeper
{
    public static void main(String[] args)
    {

        char playAgain;
        do{
            Scanner in=new Scanner(System.in);

            System.out.println("Choose Dificulty Level ");
            System.out.println("1  :  Easy    5X5");
            System.out.println("2  :  Medium  7X7");
            System.out.println("3  :  Hard    9X9");
            int dificulty=in.nextInt();
            int board_size=0;
            switch(dificulty)
            {
                case 1:
                    SetGameBoard g=new SetGameBoard(5,3);
                    board_size=5;
                    break;
                case 2:
                    SetGameBoard g1=new SetGameBoard(7,5);
                    board_size=7;
                    break;
                case 3:
                    SetGameBoard g2=new SetGameBoard(9,9);
                    board_size=9;
                    break;
                default:
                    System.out.println("Invalid Input");
                    System.exit(0);
                    break;
            }

            GamePlay play=new GamePlay(board_size);
            play.printBoard();
            boolean loop=true;
            while(loop)
            {
                System.out.print("Enter the Row and Column with Function type Ex:( (1 1 M) || (1 1 F) || (1 1 U)) :  ");
                int x=in.nextInt();
                int y=in.nextInt();
                char Ftype=in.next().charAt(0);
                System.out.println();

                play.playing(x,y,Ftype);


                if(play.VictoryCheck() || play.isFlagAndMineSame())
                {
                    if(play.isFlagAndMineSame())
                        play.AssignFullBoard();
                    if(play.VictoryCheck())
                        play.VictoryFlag();
                    play.printBoard();
                    System.out.println("==================================================");
                    System.out.println("##################################################");
                    System.out.println();
                    System.out.println();
                    System.out.println("  ******          YOU WON THE GAME         ******   ");
                    System.out.println();
                    System.out.println();
                    System.out.println("##################################################");
                    System.out.println("==================================================");
                    loop=false;

                }
            } 
        
            System.out.print("If you want to play again (y/n) :");
            playAgain=in.next().charAt(0);
        }while(playAgain=='y');
    }
}