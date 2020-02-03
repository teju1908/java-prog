import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
 
public class MonKandETF {
	static int MAX = 1000001;
	static List<Long> list;
 
	public static void main(String[] args) throws IOException {
		InputReader ir = new InputReader(System.in);
		Print print = new Print();
		int t = ir.nextInt();
		list = seive(MAX);
		while (t-- > 0) {
			long l = ir.nextLong();
			long r = ir.nextLong();
			int k = ir.nextInt();
 
			long phi[] = new long[(int) (r - l + 1)];
			long arr[] = new long[(int) (r - l + 1)];
			for (int i = 0; i < phi.length; i++) {
				phi[i] = (l + i);
				arr[i] = (l + i);
			}
 
			for (int i = 0; list.get(i) * list.get(i) <= r; i++) {
 
				long currPime = list.get(i);
 
				long base = (l / currPime) * currPime;
 
				if (base < l) {
					base = base + currPime;
				}
				long index = base - l;
 
				for (long j = index; j < r - l + 1; j += currPime) {
					phi[(int) j] = phi[(int) j] - (phi[(int) j] / currPime);
					while (arr[(int) j] % currPime == 0) {
						arr[(int) j] /= currPime;
					}
				}
 
			}
			for (int i = 0; i < r - l + 1; i++) {
				if (arr[i] > 1) {
					phi[i] = phi[i] - (phi[i] / arr[i]);
				}
			}
 
			double divCount = 0;
			for (int i = 0; i < phi.length; i++) {
				if (phi[i] % k == 0) {
					divCount++;
				}
			}
 
			print.println(String.format("%.6f", divCount / Double.valueOf(phi.length)));
		}
 
		print.close();
 
	}
 
	static long phi(long n) {
 
		long res = n;
		for (int i = 0; list.get(i) * list.get(i) <= n; i++) {
 
			if (n % list.get((int) i) == 0) {
 
				// subtract multiples of prime[i] from r
				res -= res / list.get((int) i);
 
				// Remove all occurrences of prime[i] in n
				while (n % list.get((int) i) == 0) {
					n = n / list.get((int) i);
				}
			}
		}
 
		/**
		 * every number can have at least one factor grater than sqrt(n) example 16 ,so
		 * sqrt(16)=4 ,if one factor is 8 second can not be more than sqrt(n)
		 */
		if (n > 1)
			res -= (res / n);
		return res;
	}
 
	static List<Long> seive(long r) {
 
		boolean arr[] = new boolean[(int) r];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = true;
		}
 
		for (int i = 2; i * i < r; i++) {
			if (arr[i]) {
 
				for (int j = i * 2; j < r; j += i) {
					arr[j] = false;
				}
			}
		}
 
		List<Long> list = new ArrayList<Long>();
		list.add(2L);
		for (long i = 3; i < r; i += 2) {
			if (arr[(int) i]) {
				list.add(i);
			}
		}
 
		return list;
 
	}
 
	static class InputReader {
 
		private InputStream stream;
		private byte[] buf = new byte[8192];
		private int curChar;
		private int snumChars;
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
			while (isSpaceChar(c))
				c = snext();
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
			while (isSpaceChar(c))
				c = snext();
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
 
		public String readString() {
			int c = snext();
			while (isSpaceChar(c))
				c = snext();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = snext();
			} while (!isSpaceChar(c));
			return res.toString();
		}
 
		public boolean isSpaceChar(int c) {
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
 
		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
 
	static class Print {
		private final BufferedWriter bw;
 
		public Print() {
			this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
		}
 
		public void print(Object object) throws IOException {
			bw.append("" + object);
		}
 
		public void println(Object object) throws IOException {
			print(object);
			bw.append("\n");
		}
 
		public void close() throws IOException {
			bw.close();
		}
	}
}