import java.util.*;
import java.io.*;
import java.math.BigInteger;
 

public class SuffixMatches
{
 
    static long mod = (int) (1e9+7);
    static InputReader in;
    static PrintWriter out;    
    
    static int[] getZ(char pat[]){
        int n = pat.length;
        int z[] = new int[n];
 
        int l = 0,r = 0;
        for(int i=1;i<n;i++){
            if(i > r){
                l = r = i;
                while(r < n && pat[r-l] == pat[r]) r++;
                z[i] = r - l;
                r--;
            }
            else{
                int k = i - l;
                if(z[k] < r - i + 1)	z[i] = z[k];
                else{
                    l = i;
                    while(r < n && pat[r-l] == pat[r]) r++;
                    z[i] = r - l;
                    r--;
                }
            }
        }
 
        z[0] = 0;
        return z;
    }
 
    static void solve() throws FileNotFoundException, IOException
    {
 
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);
       
        String s = in.readString();
        s = rev(s);
        int n = s.length();
        
        int[] lps = new int[n];
        lps = getZ(s.toCharArray());
 
        int q = in.nextInt();
        while(q-- > 0){
            int i = in.nextInt();
            out.println(i == n ? n :lps[n - i]);
        }
        
        out.close();
 
    }
    
    
    public static void main(String[] args)
    {
 
        new Thread(null,new Runnable()
        {
            @Override
            public void run()
            {
                try{
                    solve();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        },"1",1<<26).start();
    }
 
    static class Pair implements Comparable<Pair>
    {
 
        int x,y;
        int i;
        
	Pair (int x,int y,int i)
        {
		this.x = x;
		this.y = y;
                this.i = i;
	}
        
        Pair (int x,int y)
        {
		this.x = x;
		this.y = y;
	}
        
	public int compareTo(Pair o)
        {
            if(this.x != o.x)
                    return Integer.compare(this.x,o.x);
            return Integer.compare(this.y, o.y);
		//return 0;
	}
 
        public boolean equals(Object o)
        {
            if (o instanceof Pair)
            {
                Pair p = (Pair)o;
                return p.x == x && p.y==y;
            }
            return false;
        }
 
        @Override
        public String toString()
        {
            return x + " "+ y + " "+i;
        }
        
        public int hashCode()
        {
            return new Integer(x).hashCode() * 31 + new Integer(y).hashCode();
        }
    
    } 
 
 
    static class Merge
    {
 
        public static void sort(int inputArr[])
        {
            int length = inputArr.length;
            doMergeSort(inputArr,0, length - 1);
        }
 
        private static void doMergeSort(int[] arr,int lowerIndex, int higherIndex)
        {        
            if (lowerIndex < higherIndex)
            {
                int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
                doMergeSort(arr,lowerIndex, middle);
                doMergeSort(arr,middle + 1, higherIndex);
                mergeParts(arr,lowerIndex, middle, higherIndex);
            }
        }
 
        private static void mergeParts(int[]array,int lowerIndex, int middle, int higherIndex)
        {
            int[] temp=new int[higherIndex-lowerIndex+1];
            for (int i = lowerIndex; i <= higherIndex; i++)
            {
                temp[i-lowerIndex] = array[i];
            }
            int i = lowerIndex;
            int j = middle + 1;
            int k = lowerIndex;
            while (i <= middle && j <= higherIndex)
            {
                if (temp[i-lowerIndex] < temp[j-lowerIndex])
                {
                    array[k] = temp[i-lowerIndex];
                    i++;
                } else
                {
                    array[k] = temp[j-lowerIndex];
                    j++;
                }
                k++;
            }
            while (i <= middle)
            {
                array[k] = temp[i-lowerIndex];
                k++;
                i++;
            }
            while(j<=higherIndex)
            {
                array[k]=temp[j-lowerIndex];
                k++;
                j++;
            }
        }
 
    }
 
    static long add(long a,long b)
    {
        long x=(a+b);
        while(x>=mod) x-=mod;
        return x;
    }
 
    static long sub(long a,long b)
    {
        long x=(a-b);
        while(x<0) x+=mod;
        return x;
    }
 
    static long mul(long a,long b)
    {
        a%=mod;
        b%=mod;
        long x=(a*b);
        return x%mod;
    }
 
 
    static boolean isPal(String s)
    {
        for(int i=0, j=s.length()-1;i<=j;i++,j--)
        {
                if(s.charAt(i)!=s.charAt(j)) return false;
        }
        return true;
    }
    static String rev(String s)
    {
                StringBuilder sb=new StringBuilder(s);
                sb.reverse();
                return sb.toString();
    }
 
    static long gcd(long x,long y)
    {
        if(y==0)
                return x;
        else
                return gcd(y,x%y);
    }
 
    static int gcd(int x,int y)
    {
        if(y==0)
                return x;
        else 
                return gcd(y,x%y);
    }
 
    static long gcdExtended(long a,long b,long[] x)
    {
 
        if(a==0)
        {
            x[0]=0;
            x[1]=1;
            return b;
        }
        long[] y=new long[2];
        long gcd=gcdExtended(b%a, a, y);
 
        x[0]=y[1]-(b/a)*y[0];
        x[1]=y[0];
 
        return gcd;
    }
 
 
    static long mulmod(long  a,long b,long m)
    {
        if (m <= 1000000009) return a * b % m;
 
        long res = 0;
        while (a > 0)
        {
            if ((a&1)!=0)
            {
                res += b;
                if (res >= m) res -= m;
            }
            a >>= 1;
            b <<= 1;
            if (b >= m) b -= m;
        }
        return res;
    }
 
    static int abs(int a,int b)
    {
        return (int)Math.abs(a-b);
    }
 
    public static long abs(long a,long b)
    {
        return (long)Math.abs(a-b);
    }
 
    static int max(int a,int b)
    {
        if(a>b)
                return a;
        else
                return b;
    }
 
    static int min(int a,int b)
    {
        if(a>b)
                return b;
        else 
                return a;
    }
 
    static long max(long a,long b)
    {
        if(a>b)
                return a;
        else
                return b;
    }
 
    static long min(long a,long b)
    {
        if(a>b)
                return b;
        else 
                return a;
    }
 
    static long pow(long n,long p,long m)
    {
         long  result = 1;
          if(p==0)
            return 1;
 
        while(p!=0)
        {
            if(p%2==1)
                result *= n;
            if(result>=m)
            result%=m;
            p >>=1;
            n*=n;
            if(n>=m)
            n%=m;
        }
        return result;
    }
 
    static long pow(long n,long p)
    {
        long  result = 1;
          if(p==0)
            return 1;
 
        while(p!=0)
        {
            if(p%2==1)
                result *= n;	    
            p >>=1;
            n*=n;	    
        }
        return result;
    }
 
    static void debug(Object... o)
    {
            System.out.println(Arrays.deepToString(o));
    }
 
    static class InputReader
    {
 
        private final InputStream hasheam;
        private final byte[] buf = new byte[8192];
        private int curChar, snumChars;
        private SpaceCharFilter filter;
 
        public InputReader(InputStream hasheam)
        {
                this.hasheam = hasheam;
        }
 
        public int snext()
        {
                if (snumChars == -1)
                        throw new InputMismatchException();
                if (curChar >= snumChars)
                {
                        curChar = 0;
                        try
                        {
                                snumChars = hasheam.read(buf);
                        } catch (IOException e)
                        {
                                throw new InputMismatchException();
                        }
                        if (snumChars <= 0)
                                return -1;
                }
                return buf[curChar++];
        }
 
        public int nextInt()
        {
                int c = snext();
                while (isSpaceChar(c))
                {
                        c = snext();
                }
                int sgn = 1;
                if (c == '-')
                {
                        sgn = -1;
                        c = snext();
                }
                int res = 0;
                do
                {
                        if (c < '0' || c > '9')
                                throw new InputMismatchException();
                        res *= 10;
                        res += c - '0';
                        c = snext();
                } while (!isSpaceChar(c));
                return res * sgn;
        }
 
        public long nextLong()
        {
                int c = snext();
                while (isSpaceChar(c))
                {
                        c = snext();
                }
                int sgn = 1;
                if (c == '-')
                {
                        sgn = -1;
                        c = snext();
                }
                long res = 0;
                do
                {
                        if (c < '0' || c > '9')
                                throw new InputMismatchException();
                        res *= 10;
                        res += c - '0';
                        c = snext();
                } while (!isSpaceChar(c));
                return res * sgn;
        }
 
        public int[] nextIntArray(int n)
        {
                int a[] = new int[n];
                for (int i = 0; i < n; i++)
                {
                        a[i] = nextInt();
                }
                return a;
        }
 
        public long[] nextLongArray(int n)
        {
                long a[] = new long[n];
                for (int i = 0; i < n; i++)
                {
                        a[i] = nextLong();
                }
                return a;
        }
 
        public String readString()
        {
                int c = snext();
                while (isSpaceChar(c))
                {
                        c = snext();
                }
                StringBuilder res = new StringBuilder();
                do
                {
                        res.appendCodePoint(c);
                        c = snext();
                } while (!isSpaceChar(c));
                return res.toString();
        }
 
        public String nextLine()
        {
                int c = snext();
                while (isSpaceChar(c))
                        c = snext();
                StringBuilder res = new StringBuilder();
                do
                {
                        res.appendCodePoint(c);
                        c = snext();
                } while (!isEndOfLine(c));
                return res.toString();
        }
 
        public boolean isSpaceChar(int c)
        {
                if (filter != null)
                        return filter.isSpaceChar(c);
                return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
 
        private boolean isEndOfLine(int c)
        {
                return c == '\n' || c == '\r' || c == -1;
        }
 
        public interface SpaceCharFilter
        {
                public boolean isSpaceChar(int ch);
        }
 
    }
}