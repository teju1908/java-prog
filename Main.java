import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.Map.Entry;
 
public class Main {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;
	private PrintWriter pw;
	private long mod = 1000000 + 7;
 
	private StringBuilder ans_sb;
	private void soln() {
		int p = nextInt();
		long p1 = p;
		long l = nextLong();
		boolean[] a = new boolean[p];
		long[] inv = new long[p];
		for(int i=1; i<p; i++) {
			inv[i] = pow(i, p-2, p);
		}
		//debug(inv);
		long[] a2 = new long[p];
		Arrays.fill(a2, 1);
		for(int i=p-2;i>=1;i--) {
			if(!a[i]) {
				boolean[] pos = new boolean[p];
				for(int j=1; j<p; j++) {
					a2[j] = (a2[j] * inv[j])%p1;
					a2[j] = pow(j,i,p1);
//					if(v1 != a2[j]) {
//						while(true);
//					}
					pos[(int)a2[j]] = true;
				}
				//debug(a2);
				for(int j=1; j<p; j++) {
					long v = (1L - a2[j] + p1)%p1;
					if(pos[(int)v]) {
						//debug(i);
						a[i] = true;
						int sqrt = (int)(Math.sqrt(i));
						for(int k=1; k<=sqrt; k++) {
							if(i%k == 0) {
								a[k] = true;
								a[i/k] = true;
							}
						}
						break;
					}
				}
			}
		}
		long cc = l/(p1-1);
		long rem = l%(p1-1);
		long cnt = 0;
		long ccc = 0;
		for(int i=1; i<p; i++) {
			if(a[i]) {
				ccc++;
				if(i <= rem)
					cnt++;
			}
		}
		cnt = cnt + ccc*cc;
		pw.println(cnt);
		
	}
		public void fft(double[] a, double[] b, boolean invert) {
			int count = a.length;
			for (int i = 1, j = 0; i < count; i++) {
				int bit = count >> 1;
				for (; j >= bit; bit >>= 1) j -= bit;
				j += bit;
				if (i < j) {
					double temp = a[i];
					a[i] = a[j];
					a[j] = temp;
					temp = b[i];
					b[i] = b[j];
					b[j] = temp;
				}
			}
			for (int len = 2; len <= count; len <<= 1) {
				int halfLen = len >> 1;
				double angle = 2 * Math.PI / len;
				if (invert) angle = -angle;
				double wLenA = Math.cos(angle), wLenB = Math.sin(angle);
				for (int i = 0; i < count; i += len) {
					double wA = 1, wB = 0;
					for (int j = 0; j < halfLen; j++) {
						double uA = a[i + j], uB = b[i + j];
						double vA = a[i + j + halfLen] * wA - b[i + j + halfLen] * wB;
						double vB = a[i + j + halfLen] * wB + b[i + j + halfLen] * wA;
						a[i + j] = uA + vA;
						b[i + j] = uB + vB;
						a[i + j + halfLen] = uA - vA;
						b[i + j + halfLen] = uB - vB;
						double nextWA = wA * wLenA - wB * wLenB;
						wB = wA * wLenB + wB * wLenA;
						wA = nextWA;
					}
				}
			}
			if (invert) {
				for (int i = 0; i < count; i++) {
					a[i] /= count;
					b[i] /= count;
				}
			}
		}
		public long[] multiply(long[] a, long[] b) {
			
			int resultSize = Integer.highestOneBit(Math.max(a.length, b.length) - 1) << 2;
			resultSize = Math.max(resultSize, 1);
			
			double[] aReal = new double[resultSize], aImaginary = new double[resultSize];
			double[] bReal = new double[resultSize], bImaginary = new double[resultSize];
			
			for (int i = 0; i < a.length; i++) aReal[i] = a[i];
			for (int i = 0; i < b.length; i++) bReal[i] = b[i];
			
			fft(aReal, aImaginary, false);
			
			if (a == b) {
				System.arraycopy(aReal, 0, bReal, 0, aReal.length);
				System.arraycopy(aImaginary, 0, bImaginary, 0, aImaginary.length);
			} 
			else 
				fft(bReal, bImaginary, false);
			
			for (int i = 0; i < resultSize; i++) {
				double real = aReal[i] * bReal[i] - aImaginary[i] * bImaginary[i];
				aImaginary[i] = aImaginary[i] * bReal[i] + bImaginary[i] * aReal[i];
				aReal[i] = real;
			}
			
			fft(aReal, aImaginary, true);
			
			long[] result = new long[resultSize];
			for (int i = 0; i < resultSize; i++) result[i] = Math.round(aReal[i]);
			return result;
		}
	private String solveEqn(long a, long b) {
		long x = 0, y = 1, lastx = 1, lasty = 0, temp;
		while (b != 0) {
			long q = a / b;
			long r = a % b;
			a = b;
			b = r;
			temp = x;
			x = lastx - q * x;
			lastx = temp;
			temp = y;
			y = lasty - q * y;
			lasty = temp;
		}
		return lastx + " " + lasty;
	}
 
	private void debug(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}
 
	private long pow(long a, long b, long c) {
		if (b == 0)
			return 1;
		long p = pow(a, b / 2, c);
		p = (p * p) % c;
		return (b % 2 == 0) ? p : (a * p) % c;
	}
 
	private long gcd(long n, long l) {
		if (l == 0)
			return n;
		return gcd(l, n % l);
	}
 
	public static void main(String[] args) throws Exception {
		new Thread(null, new Runnable() {
			@Override
			public void run() {
				new Main().solve();
			}
		}, "1", 1 << 26).start();
		//new Main().solve();
	}
 
	public StringBuilder solve() {
		InputReader(System.in);
		/*
		 * try { InputReader(new FileInputStream("C:\\Users\\hardik\\Desktop\\in.txt"));
		 * } catch(FileNotFoundException e) {}
		 */
		pw = new PrintWriter(System.out);
		// ans_sb = new StringBuilder();
		soln();
 
		pw.close();
		// System.out.println(ans_sb);
		return ans_sb;
	}
 
	public void InputReader(InputStream stream1) {
		stream = stream1;
	}
 
	private boolean isWhitespace(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}
 
	private boolean isEndOfLine(int c) {
		return c == '\n' || c == '\r' || c == -1;
	}
 
	private int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}
 
	private int nextInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}
 
	private long nextLong() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		long res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}
 
	private String nextToken() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}
 
	private String nextLine() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isEndOfLine(c));
		return res.toString();
	}
 
	private int[] nextIntArray(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = nextInt();
		}
		return arr;
	}
 
	private long[] nextLongArray(int n) {
		long[] arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = nextLong();
		}
		return arr;
	}
 
	private void pArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		return;
	}
 
	private void pArray(long[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		return;
	}
 
	private boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return isWhitespace(c);
	}
 
	private char nextChar() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		char c1 = (char) c;
		while (!isSpaceChar(c))
			c = read();
		return c1;
	}
 
	private interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}