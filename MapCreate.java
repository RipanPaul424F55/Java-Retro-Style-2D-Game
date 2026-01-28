import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class MapCreate
{
    public static void main(String args[])
    {
        Scanner obj=new Scanner(System.in);
        System.out.println("Enter rows:");
        int r=obj.nextInt();
        System.out.println("Enter cols:");
        int c=obj.nextInt();
        System.out.println("Enter value:");
        int val=obj.nextInt();
        System.out.println("Enter File name without extenson:");
        String name=obj.next()+".txt";
        
        try{
            FileWriter f=new FileWriter(name);
            f.write(create(r,c,val));
            f.close();
        }catch(IOException e)
        {
            System.out.println("error");
        }
    }
    public static String create(int r,int c,int val)
    {
        String s="";
        for(int i=0;i<r;i++)
        {
            for(int j=0;j<c;j++)
            if(j!=c-1)
            s+=String.valueOf(val)+" ";
            else
            s+=String.valueOf(val);
            s+="\r\n";
        }
        return s;
    }
}