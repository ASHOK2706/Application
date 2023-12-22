import java.util.Random;
import java.util.Scanner;
class GamePlay
{
    private String PName;
    private int Pos;
    private int count;
    GamePlay(String name)
    {
        this.PName=name;
        this.Pos=0;
        this.count=0;
        
    }
    Scanner in=new Scanner(System.in);
    public boolean check(int[][] arr,int value)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(value==arr[i][0])
            {
                this.Pos=arr[i][1];
                return true;
            }

            

        }
        return false;
    }
    public boolean isLaddderOrSnake(int n)
    {
        
        int[][] Ladder ={ {1,38},{4,14},{8,10},{21,42},{28,76},{50,67},{71,92},{86,99} } ;
        int[][] Snake={ {32,10},{36,6},{48,26},{63,18},{88,24},{95,56},{97,78}, };

        //int LPos=check(Ladder,n);

        if(check(Ladder,n))
        {
            return true;
        }
        else if(check(Snake,n))
        {
            return true;
        }

        return false;
    }
    
    public void display()
    {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Player Name : "+this.PName+"      Player Position : "+this.Pos);
        System.out.println("---------------------------------------------------------------------");
    }
    Random random=new Random();
   
    public void RollDice()
    {
        boolean loop=true;
        while(loop)
        {
            int dicevalue=1;
            dicevalue=dicevalue+random.nextInt(6);
            System.out.println("dice :"+dicevalue);
            if((this.Pos+dicevalue)==100)
            {
                System.out.println("======================================");
                System.out.println();
                System.out.println("Congratulations   "+"###  "+this.PName+"  ###");
                System.out.println();
                System.out.println("*****    You Won the Game    ****");
                System.out.println();
                System.out.println("=======================================");
                System.exit(0);
            }
            else if((this.Pos+dicevalue)>100)
            {
                this.Pos=this.Pos;
                if(dicevalue==1 || dicevalue==5 || dicevalue==6)
                continue;
                loop=false;
                continue;
            }

            if(this.count==0 && (this.Pos+dicevalue)==1)
            {
                System.out.println("Congrs your Game is Start ");

                this.count++;
                this.Pos=this.Pos+dicevalue;
                if(isLaddderOrSnake(this.Pos))
                {

                    System.out.println("You got a Ladder ");
                    System.out.println("You got dice "+dicevalue+" Enter to dice Again !!!!");
                    in.nextLine();
                    continue;

                }
            }
            else if(this.count==0){
                
                loop=false;

            }

    


            if(this.count>0)
            {
                
                if(dicevalue==1 || dicevalue==5 || dicevalue==6)
                {
                    System.out.println("You got dice "+dicevalue+" Enter to Roll Dice Again !!!!");
                    in.nextLine();

                    this.Pos=this.Pos+dicevalue;
                    int prePos=this.Pos;
                    if(isLaddderOrSnake(this.Pos))
                    {
                        if(prePos<this.Pos)
                            System.out.println("You got a Ladder ");
                        else
                            System.out.println(" Oops !!!  You got Snake ");
                        continue;
                    }

                    continue;

                }
                else
                {
                    this.Pos=this.Pos+dicevalue;
                    int prePos=this.Pos;
                    if(isLaddderOrSnake(this.Pos))
                    {
                        if(prePos<this.Pos)
                            System.out.println("You got a Ladder ");
                        else
                            System.out.println(" Oops!!!  You got Snake ");
                        loop=false;
                        break;
                    }
                    loop=false;
                    break;
                }
            }
        }        
    }
}
public class SnakeAndLadder {

    public static void main(String[] args)
    {
        Scanner in=new Scanner (System.in);
        System.out.print("Enter No of Players : ");
        int No_of_Players = in.nextInt();
        String[] Players=new String[No_of_Players];
    
        for(int i=0;i<No_of_Players;i++)
        {
            System.out.println("Enter Player No "+(i+1)+" Name :");
            Players[i]=in.next();

        }
        GamePlay[] gameplay_arr=new GamePlay[No_of_Players];
        for(int i=0;i<No_of_Players;i++)
        {
            gameplay_arr[i]=new GamePlay(Players[i]);            

        }

       for(int i=0;i<No_of_Players;i++)
       {

            System.out.println("\n" +Players[i] + " turn (Press Enter to Roll Dice) : ");
            in.nextLine();

            gameplay_arr[i].RollDice();

            for(int j=0;j<No_of_Players;j++)
                gameplay_arr[j].display();
           
          
    
            if(i+1==No_of_Players)
            {
            i=-1;
            }
       }
    }
}