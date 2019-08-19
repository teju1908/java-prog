import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;
 

class SegmentTree {
    
    int tree[];
    int mod = 1000000007;
    SegmentTree(int n)
    {
        int x = (int)Math.ceil(Math.log(n)/Math.log(2));
        int size = 2*(int)Math.pow(2,x)-1;
        tree = new int[size];
    }
    public int gcd(int a, int b)
    {
        if(b==0)
            return a;
        return gcd(b,a%b);
    }
    public long fib(int n)
    {
        long F[][]= new long[][]{{1,1},{1,0}};
        if(n==0)
            return 0;
        
        power(F,n-1);
        
        return F[0][0];
    }
    public void multiply(long F[][], long M[][])
    {
        long x = ((F[0][0]*M[0][0])%mod+(F[0][1]*M[1][0])%mod)%mod;
        long y = ((F[0][0]*M[0][1])%mod+(F[0][1]*M[1][1])%mod)%mod;
        long z = ((F[1][0]*M[0][0])%mod+(F[1][1]*M[1][0])%mod)%mod;
        long w = ((F[1][0]*M[0][1])%mod+(F[1][1]*M[1][1])%mod)%mod;
        
        F[0][0] = x;
        F[0][1] = y;
        F[1][0] = z;
        F[1][1] = w;
    }
    public void power(long F[][], int n)
    {
        if(n==0||n==1)
            return;
        long M[][] = new long[][]{{1,1},{1,0}};
        
        power(F,n/2);
        multiply(F,F);
        
        if(n%2!=0)
            multiply(F,M);
    }
    public void build(int node ,int start, int end, int arr[])
    {
        if(start==end)
            tree[node] = arr[start];
        else
        {
            int mid =(start+end)/2;
            build(2*node+1,start, mid,arr);
            build(2*node+2,mid+1,end,arr);
            
            tree[node] = gcd(tree[2*node+1],tree[2*node+2]);
        }
    }
    public int segQuery(int node, int start, int end, int l ,int r)
    {
        if(l<=start&&end<=r)
            return tree[node];
        int mid = (start+end)/2;
        if(l<=mid&&r>=mid+1)
            return gcd(segQuery(2*node+1,start, mid,l,r),segQuery(2*node+2, mid+1, end, l,r));
        if(l>mid)
            return segQuery(2*node+2,mid+1,end,l,r);
        else
            return segQuery(2*node+1,start,mid,l,r);
    }
	public static void main (String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s[] = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int q = Integer.parseInt(s[1]);
	    int arr[] = new int[n];
	    String list[] = br.readLine().split(" ");
	    for(int i=0;i<n;i++)
	    {
	        arr[i] = Integer.parseInt(list[i]);
	    }
	    SegmentTree t1 = new SegmentTree(n);
	    t1.build(0,0,n-1,arr);
	    while(q-->0)
	    {
	        String qu[] = br.readLine().split(" ");
	        int l = Integer.parseInt(qu[0]);
	        int r = Integer.parseInt(qu[1]);
	        int g = t1.segQuery(0,0,n-1,l-1,r-1);
	        System.out.println(t1.fib(g));
	    }
	}
}	