import java.io.BufferedReader;
import java.io.InputStreamReader;
 
import java.util.*;

class VarianceMinimization {
    public static void main(String args[] ) throws Exception {
  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        int t =Integer.parseInt(br.readLine());
        while(t!=0){
            int n=Integer.parseInt(br.readLine());
            int a[][]=new int[n][2];
            long min=Long.MAX_VALUE;
            long sumx=0;
            long sumy=0,sumsqx=0,sumsqy=0;
            for(int i=0;i<n;i++)
            {StringTokenizer st=new StringTokenizer(br.readLine());
                a[i][0]=Integer.parseInt(st.nextToken());
                a[i][1]=Integer.parseInt(st.nextToken());
                sumx+=a[i][0];
                sumy+=a[i][1];
                sumsqx+=(long)(Math.pow(a[i][0],2));
                sumsqy+=(long)(Math.pow(a[i][1],2));
            }
            for(int i=0;i<n;i++)
            {
                double newSumx=((double)(sumx-a[i][0]))/(n-1);
                double newSumy=((double)(sumy-a[i][1]))/(n-1);
                long x=(long)newSumx;
                long y=(long)newSumy;
                if(newSumx-x>0.5d)
                   x+=1;
                if(newSumy-y>0.5d)
                   y+=1;
                long temp=n*(sumsqx-(long)(Math.pow(a[i][0],2))+x*x) +  n*(sumsqy-(long)(Math.pow(a[i][1],2))+y*y);
                temp=temp-(long)Math.pow((sumx-a[i][0]+x),2)-(long)Math.pow((sumy-a[i][1]+y),2);
                min=Math.min(min,temp);
            }
            System.out.println(min);
            t--;
        }
 
 
    }
}