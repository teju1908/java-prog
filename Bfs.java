import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
 
public class Bfs {
	InputStream is;
	PrintWriter out;
	String INPUT = "";
 
	void solve() {
		for (int T = ni(); T > 0; T--) {
			int n = ni();
			int[] a = na(n);
			for (int i = 0; i < n; i++)
				a[i]--;
			int[] from = new int[3 * n - 2];
			int[] to = new int[3 * n - 2];
			int[] w = new int[3 * n - 2];
			for (int i = 0; i < n; i++) {
				from[i] = i;
				to[i] = a[i];
			}
			for (int i = 0; i < n - 1; i++) {
				from[n + i] = i;
				to[n + i] = i + 1;
				// tr(n+i, i+1);
				w[n + i] = 1;
			}
			for (int i = 2 * n - 1, p = 1; i < from.length; i++, p++) {
				from[i] = p;
				to[i] = p - 1;
				w[i] = 1;
			}
			int[][][] g = packWD(n, from, to, w);
			long[] d = dijkl(g, 0);
			out.println(d[n - 1]);
		}
	}
 
	int[][][] packWD(int n, int[] from, int[] to, int[] w) {
		int[][][] g = new int[n][][];
		int[] p = new int[n];
		for (int f : from)
			p[f]++;
		for (int i = 0; i < n; i++)
			g[i] = new int[p[i]][2];
		for (int i = 0; i < from.length; i++) {
			--p[from[i]];
			g[from[i]][p[from[i]]][0] = to[i];
			g[from[i]][p[from[i]]][1] = w[i];
		}
		return g;
	}
 
	long[] dijkl(int[][][] g, int from) {
		int n = g.length;
		long[] td = new long[n];
 
		Arrays.fill(td, Long.MAX_VALUE / 2);
		MinHeapL q = new MinHeapL(n);
		q.add(from, 0);
		td[from] = 0;
 
		while (q.size() > 0) {
			int cur = q.argmin();
			q.remove(cur);
 
			for (int[] e : g[cur]) {
				int next = e[0];
				long nd = td[cur] + e[1];
				if (nd < td[next]) {
					td[next] = nd;
					q.update(next, nd);
				}
			}
		}
 
		return td;
	}
 
	class MinHeapL {
		public long[] a;
		public int[] map;
		public int[] imap;
		public int n;
		public int pos;
		public long INF = Long.MAX_VALUE;
 
		public MinHeapL(int m) {
			n = Integer.highestOneBit((m + 1) << 1);
			a = new long[n];
			map = new int[n];
			imap = new int[n];
			Arrays.fill(a, INF);
			Arrays.fill(map, -1);
			Arrays.fill(imap, -1);
			pos = 1;
		}
 
		public long add(int ind, long x) {
			int ret = imap[ind];
			if (imap[ind] < 0) {
				a[pos] = x;
				map[pos] = ind;
				imap[ind] = pos;
				pos++;
				up(pos - 1);
			}
			return ret != -1 ? a[ret] : x;
		}
 
		public long update(int ind, long x) {
			int ret = imap[ind];
			if (imap[ind] < 0) {
				a[pos] = x;
				map[pos] = ind;
				imap[ind] = pos;
				pos++;
				up(pos - 1);
			} else {
				a[ret] = x;
				up(ret);
				down(ret);
			}
			return x;
		}
 
		public long remove(int ind) {
			if (pos == 1)
				return INF;
			if (imap[ind] == -1)
				return INF;
 
			pos--;
			int rem = imap[ind];
			long ret = a[rem];
			map[rem] = map[pos];
			imap[map[pos]] = rem;
			imap[ind] = -1;
			a[rem] = a[pos];
			a[pos] = INF;
			map[pos] = -1;
 
			up(rem);
			down(rem);
			return ret;
		}
 
		public long min() {
			return a[1];
		}
 
		public int argmin() {
			return map[1];
		}
 
		public int size() {
			return pos - 1;
		}
 
		private void up(int cur) {
			for (int c = cur, p = c >>> 1; p >= 1 && a[p] > a[c]; c >>>= 1, p >>>= 1) {
				long d = a[p];
				a[p] = a[c];
				a[c] = d;
				int e = imap[map[p]];
				imap[map[p]] = imap[map[c]];
				imap[map[c]] = e;
				e = map[p];
				map[p] = map[c];
				map[c] = e;
			}
		}
 
		private void down(int cur) {
			for (int c = cur; 2 * c < pos;) {
				int b = a[2 * c] < a[2 * c + 1] ? 2 * c : 2 * c + 1;
				if (a[b] < a[c]) {
					long d = a[c];
					a[c] = a[b];
					a[b] = d;
					int e = imap[map[c]];
					imap[map[c]] = imap[map[b]];
					imap[map[b]] = e;
					e = map[c];
					map[c] = map[b];
					map[b] = e;
					c = b;
				} else {
					break;
				}
			}
		}
	}
 
	void run() throws Exception {
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
 
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		if (!INPUT.isEmpty())
			tr(System.currentTimeMillis() - s + "ms");
	}
 
	public static void main(String[] args) throws Exception {
		new Bfs().run();
	}
 
	private byte[] inbuf = new byte[1024];
	public int lenbuf = 0, ptrbuf = 0;
 
	private int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}
 
	private boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}
 
	private int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}
 
	private double nd() {
		return Double.parseDouble(ns());
	}
 
	private char nc() {
		return (char) skip();
	}
 
	private String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
 
	private char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}
 
	private char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}
 
	private int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}
 
	private int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}
 
		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
 
	private long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}
 
		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
 
	private static void tr(Object... o) {
		System.out.println(Arrays.deepToString(o));
	}
}