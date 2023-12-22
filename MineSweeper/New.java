import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/* 
public class New {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
       // int n=in.nextInt();
       
        //System.out.println(f(n));

        int ar[]={1,2,3,4,5,6};
        int i=ar.length-1;
        Rarr(ar,i);
    }
  /*+/  static void fun(int i,int n,int sum)
    {
        if(i>n)
        {
            System.out.println(sum);
            return ;
        }
        sum=sum+i;
        fun(i+1,n,sum);
        
    }*+/
    
   /*  static int f(int n)
    {
        if(n==0)
            return 0;
        return n+f(n-1);
    }*+/
    /*static int f(int n)
    {
        if(n==0)
            return 1;
        return n*f(n-1);
    }*+/
    static void Rarr(int[] ar,int i)
    {
        if(i<0)
            return;
        System.out.print(ar[i]+" ");
        Rarr(ar,i-1);
    }

}
 */


 /* 
public class New{
    public static void main(String[] args) {
        int[] arr={3,2,1};
        int n=3;
        List<Integer> list=new ArrayList<>();
        fun(0,list,arr,n);
    }
    static void fun(int ind,List<Integer> list,int[] arr,int n)
    {
        if(ind==n){
            System.out.println(list);
            return;
        }
        list.add(arr[ind]);
        fun(ind+1,list,arr,n);
        list.remove(list.size()-1);
        fun(ind+1,list,arr,n);

    }
}
*/


public  class New {
    public static void main(String[] args) {
        int[] arr={1,2,1};
        int n=3;
        ArrayList<Integer> list=new ArrayList<>();
        fun(0,list,arr,n,0);
    }
    static void fun(int ind,ArrayList<Integer> list,int[] arr,int n,int sum)
    {
        if(sum==2)
        {
            System.out.println(list);
            return;
        }
        list.add(arr[ind]);
        sum=sum+arr[ind];
        fun(ind,list,arr,n,sum);
        list.remove(list.size()-1);
        sum=sum-arr[ind];
        fun(ind,list,arr,n,sum+arr[ind]);
       

    } 
}
