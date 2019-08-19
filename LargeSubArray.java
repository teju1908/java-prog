import java.io.*;
import java.util.*;
class FastIO {
		private final InputStream is;
		private final byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
 
		public FastIO() {
			this(System.in);
		}
 
		public FastIO(final InputStream is) {
			this.is = is;
		}
 
		public int[] nextArray(final int n) {
			final int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
 
		public int read() {
			if (numChars == -1) {
				throw new RuntimeException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = is.read(buf);
				} catch (final IOException e) {
					throw new RuntimeException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}
 
		public String nextLine() {
			return readLine();
		}
 
		public String readLine() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			final StringBuilder sb = new StringBuilder();
			do {
				sb.append((char) c);
				c = read();
			} while (!isEndOfLine(c));
			return sb.toString();
		}
 
		public String next() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			final StringBuilder sb = new StringBuilder();
			do {
				sb.append((char) c);
				c = read();
			} while (!isSpaceChar(c));
			return sb.toString();
		}
 
		public long nextLong() {
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
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public int nextInt() {
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
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}
 
		public boolean isSpaceChar(final int c) {
			return (c == ' ') || (c == '\n') || (c == '\r') || (c == '\t') || (c == -1);
		}
 
		public boolean isEndOfLine(final int c) {
			return (c == '\n') || (c == '\r') || (c == -1);
		}
	}
class LargeSubArray {
    public static void main(String args[] ) throws Exception {
        FastIO f = new FastIO();
        long MOD = 1000000007;
		int t = f.nextInt();
		while (t-- > 0) {
			int n = f.nextInt();
			long m = f.nextInt();
			long k = f.nextLong();
			long[] suff = new long[n + 1];
			for (int i = 0; i < n; i++) {
				suff[i] = f.nextLong();
			}
			for (int i = n - 1; i >= 0; i--) {
				suff[i] = suff[i] + suff[i + 1];
			}
			long sum = suff[0];
			long ans = 0;
			for (int i = 0; i < n; i++) {
				if (suff[i] >= k) {
					int l = i, r = n - 1;
					long temp = -1;
					while (l <= r) {
						int mid = (l + r) >> 1;
						if (suff[i] - suff[mid + 1] <= k) {
							l = mid + 1;
							temp = mid;
						} else {
							r = mid - 1;
						}
					}
					if (temp != -1) {
						ans += ((temp - i + 1) * m) % MOD;
						ans %= MOD;
					}
				} else if (suff[i] + ((m - 1) * sum) <= k) {
					ans += ((m * (2 * (n - i) + (m - 1) * n)) / 2) % MOD;
					ans %= MOD;
				} else {
					long remain = k - suff[i];
					long c = remain / sum;
					ans += (((c + 1) * (2 * (n - i) + c * n)) / 2) % MOD;
					ans %= MOD;
					remain = remain - c * sum;
					int l = 0, r = n - 1;
					long temp = -1;
					while (l <= r) {
						int mid = (l + r) >> 1;
						if (sum - suff[mid + 1] <= remain) {
							l = mid + 1;
							temp = mid;
						} else {
							r = mid - 1;
						}
					}
					temp++;
					long tot = (n - i) + (n * c) + temp;
					c = m - c - 1;
					ans += (tot * c) % MOD;
					ans %= MOD;
				}
			}
			System.out.println("output:");
			System.out.println(ans);
		}
    }
}
