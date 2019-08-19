import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class SparseArrays {

    public static void main(String[] args) 
    {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
       
        int stringSize = in.nextInt();
        ArrayList <String> strList = new ArrayList<String>();
        for(int i = 0 ; i< stringSize ; ++i)
            strList.add(in.next());
        
        int querySize = in.nextInt();
        ArrayList <String> queryList = new ArrayList<String>();
        for(int i = 0 ; i< querySize ; ++i)
            queryList.add(in.next());
        
        
        for(int i = 0 ; i <querySize ; ++i)
        {
            int count=0;
            for(int j=0 ; j<stringSize ; ++j)
                {
                    if(queryList.get(i).equalsIgnoreCase(strList.get(j)))
                        count++;
                }
            
            System.out.println(count);
        }
      //  for(int i=0 ; i< stringSize ; i++)
      //  System.out.println(strList.get(i));
        
      //   for(int i=0 ; i< querySize ; i++)
        //System.out.println(queryList.get(i));
    }
}