import java.io.*;
import java.util.*;
class GFG {
    final static long mod= 1000000009;
	public static void main (String[] args) {
		Scanner sc= new Scanner(System.in);
		int t= sc.nextInt();
		long d= little(2);
		while(t-->0)
		{
		    long n= sc.nextLong();
		    n=(n)%(mod-1);
		    System.out.println(((exponential(10,n)-exponential(8,n)+mod)%mod*d)%mod);
		}
	}
	public static long exponential(long a, long p)
	{
	    long result=1;
	    while(p>0)
	    {
	        if(p%2==1)
	            result=(result*a)%mod;
	        a=(a*a)%mod;
	        p/=2;
	    }
	    return result;
	}
	public static long little(long n)
	{
	    return exponential(n, mod-2);
	}
}