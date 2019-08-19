import java.util.*;
import java.io.*;

class TrieNode
{
 
	int value;
	TrieNode left,right;
 
	TrieNode root;
	public TrieNode()
	{
		value = 0;
		left = null;
		right = null;
	}
 
	public void insert(int pre_xor) 
	{
 
		TrieNode curr = root;
 
		for(int i=31;i>=0;i--)
		{
			int x = pre_xor & 1<<i;
 
			if(x != 0)
			{
				if(curr.right == null)
				{
					curr.right = new TrieNode();
				}
				curr = curr.right; 
			}
			else
			{
				if(curr.left == null)
				{
					curr.left = new TrieNode();
				}
				curr = curr.left;
			}
		}
		curr.value = pre_xor;
	}
 
	public int query(int pre_xor) 
	{
 
		TrieNode curr = root;
		for(int i=31;i>=0;i--)
		{
 
			int x = pre_xor & 1<<i;
			if(x == 0 && curr.right!=null)
			{
				curr = curr.right;
			}
			else if(curr.left != null)
			{
				curr = curr.left;
			}
 
		}
		return pre_xor ^ curr.value;
	}
 
}
class SubMatrix
{
 
 
	public static void main(String[] args) 
	{
 
		InputReader sc = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
 
		int n = sc.nextInt();
		int m = sc.nextInt();
		int arr[][] = new int[n][m];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				arr[i][j] = sc.nextInt();
			}
		}
		int max = 0;
		for(int L=0;L<m;L++)
		{
 
			int mat[] = new int[n]; 
			for(int R=L;R<m;R++)
			{
				for(int index = 0;index<n;index++)
					mat[index] = mat[index] ^ arr[index][R]; 
 
				int pre_xor = 0;
				TrieNode tn = new TrieNode();
				tn.root = new TrieNode();
				tn.insert(pre_xor);
				int ans = 0;
 
				for(int i=0;i<mat.length;i++)
				{
					pre_xor = pre_xor^mat[i];
					tn.insert(pre_xor);
					ans = Math.max(ans,tn.query(pre_xor));
				}
				max = Math.max(max,ans);
 
			}
 
 
		}
		out.println(max);
 
		out.close();
 
 
	}    
 
 
	static class Pair{
		int x;
		int y;
		Pair(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
 
	public static class CompareTable implements Comparator{
		public int compare(Object o1,Object o2){
			Pair p1 = (Pair) o1;
			Pair p2 = (Pair) o2;
 
			if(p1.x>p2.x)
				return -1;
			else if(p1.x<p2.x)
				return 1;
			else{
				if(p1.y<p2.y)
					return -1;
				else if(p1.y>p2.y)
					return 1;
				else
					return 0;
			}
		}
	}
 
	public static boolean isPal(String s){
		for(int i=0, j=s.length()-1;i<=j;i++,j--){
			if(s.charAt(i)!=s.charAt(j)) return false;
		}
		return true;
	}
	public static String rev(String s){
		StringBuilder sb=new StringBuilder(s);
		sb.reverse();
		return sb.toString();
	}
 
	public static long gcd(long x,long y){
		if(x%y==0)
			return y;
		else
			return gcd(y,x%y);
	}
 
	public static int gcd(int x,int y){
		if(x%y==0)
			return y;
		else 
			return gcd(y,x%y);
	}
	public static int lcm(int a, int b)
	{
		return (a*b)/gcd(a, b);
	}
	public static long gcdExtended(long a,long b,long[] x){
 
		if(a==0){
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
	public static long modpower(long x,long y, long mod)
	{
		long res = 1;               
		x = x % mod;
		while (y > 0)
		{
			// If y is odd, multiply x with result
			if (y%2==1)
				res = (res*x) % mod;
 
			y = y/2; 
			x = (x*x) % mod;  
		}
		return res;
	}
	public static int abs(int a,int b){
		return (int)Math.abs(a-b);
	}
 
	public static long abs(long a,long b){
		return (long)Math.abs(a-b);
	}
 
	public static int max(int a,int b){
		if(a>b)
			return a;
		else
			return b;
	}
 
	public static int min(int a,int b){
		if(a>b)
			return b;
		else 
			return a;
	}
 
	public static long max(long a,long b){
		if(a>b)
			return a;
		else
			return b;
	}
 
	public static long min(long a,long b){
		if(a>b)
			return b;
		else 
			return a;
	}
 
	public static long pow(long n,long p,long m){
		long  result = 1;
		if(p==0)
			return 1;
		if (p==1)
			return n;
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
 
	public static long pow(long n,long p){
		long  result = 1;
		if(p==0)
			return 1;
		if (p==1)
			return n;
		while(p!=0)
		{
			if(p%2==1)
				result *= n;	    
			p >>=1;
			n*=n;	    
		}
		return result;
	}
 
	static class InputReader {
 
		private final InputStream stream;
		private final byte[] buf = new byte[8192];
		private int curChar, snumChars;
		private SpaceCharFilter filter;
 
		public InputReader(InputStream stream) {
			this.stream = stream;
		}
 
		public int snext() {
			if (snumChars == -1)
				throw new InputMismatchException();
			if (curChar >= snumChars) {
				curChar = 0;
				try {
					snumChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (snumChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}
 
		public int nextInt() {
			int c = snext();
			while (isSpaceChar(c)) {
				c = snext();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = snext();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public long nextLong() {
			int c = snext();
			while (isSpaceChar(c)) {
				c = snext();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = snext();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = snext();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public int[] nextIntArray(int n) {
			int a[] = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
 
		public long[] nextLongArray(int n) {
			long a[] = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
 
		public String readString() {
			int c = snext();
			while (isSpaceChar(c)) {
				c = snext();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = snext();
			} while (!isSpaceChar(c));
			return res.toString();
		}
 
		public String nextLine() {
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = snext();
			} while (!isEndOfLine(c));
			return res.toString();
		}
 
		public boolean isSpaceChar(int c) {
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
 
		private boolean isEndOfLine(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}
 
		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}   