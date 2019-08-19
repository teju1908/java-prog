import java.util.*;
import java.io.*;
class MergeSort {
    public static void main(String args[] ) throws IOException {
      Scanner in = new Scanner(System.in);
      PrintWriter pw = new PrintWriter(System.out);
      int t = in.nextInt();
      String[] name = new String[t];
      int[] age = new int[t];
      String[] hometown = new String[t];
      String[] address = new String[t];
      for(int i=0;i<t;i++)
      {
          name[i] = in.next();
          age[i] = in.nextInt();
          hometown[i] = in.next();
          address[i] = in.next();
          
      }
      ArrayList<String> list = new ArrayList<String>();
      for(int i=0;i<t-1;i++)
      for(int j=i+1;j<t;j++)
      {
          if(name[i].equals(name[j]) || age[i] == age[j] || hometown[i].equals(hometown[j]) || address[i].equals(address[j]))
                {
                    String p = (i+1)+" "+(j+1);
                    list.add(p);
                }
          
      }
      pw.println(list.size());
      for(int i=0;i<list.size();i++)
      pw.println(list.get(i));
        pw.close();
    }
}