import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.FilterInputStream;
import java.io.BufferedInputStream;
import java.io.InputStream;
 

public class Subsets{
    public static void main(String[] ARGS) {
        new Thread(null, new Runnable() {
            public void run() {
                new Main().solve();
            }
        }, "1", 1 << 26).start();
    }
 
    void solve() {
        InputStream IS = System.in;
        OutputStream OS = System.out;
        ScanReader in = new ScanReader(IS);
        PrintWriter out = new PrintWriter(OS);
        BobAndMathematics bobandmathematics = new BobAndMathematics();
        bobandmathematics.solve(1, in, out);
        out.close();
    }
 
    static class BobAndMathematics {
        long[][] nCr;
        int mod = 1000000006;
 
        public void solve(int testNumber, ScanReader in, PrintWriter out) {
			out.println("output:");
            int t = in.scanInt();
            boolean isprime[] = CodeX.sieve(1000005);
            pre();
            while (t-- > 0) {
                int N = in.scanInt();
                int n = 0;
                int m = 0;
                for (int i = 0; i < N; i++) {
                    if (isprime[in.scanInt()]) m++;
                    else
                        n++;
                }
                long ans = 1;
                long temp1 = modulo(2, n, mod);
                for (int i = 0; i <= m; i++) {
                    long com = nCr[m][i];
                    long temp2 = temp1 * (com % mod);
                    temp2 %= mod;
                    ans *= modulo(i + 2, temp2, mod + 1);
                    ans %= mod + 1;
                }
				
                out.println(ans);
            }
        }
 
        long modulo(long a, long b, long c) {
            long x = 1;
            long y = a;
            while (b > 0) {
                if (b % 2 == 1) {
                    x = (x * y) % c;
                }
                y = (y * y) % c; // squaring the base
                b /= 2;
            }
            return x % c;
        }
 
        private void pre() {
            nCr = new long[1005][1005];
            for (int i = 0; i <= 1000; i++) {
                for (int j = 0; j <= 1000; j++) {
 
                    if (j == 0 || j == i || i == 0)
                        nCr[i][j] = 1;
                    else
                        nCr[i][j] = nCr[i - 1][j - 1] + nCr[i - 1][j];
                    nCr[i][j] %= mod;
                }
            }
        }
 
    }
 
    static class ScanReader {
        private byte[] buf = new byte[4 * 1024];
        private int index;
        private BufferedInputStream in;
        private int Total_Char;
 
        public ScanReader(InputStream inputStream) {
            in = new BufferedInputStream(inputStream);
        }
 
        private int scan() {
            if (index >= Total_Char) {
                index = 0;
                try {
                    Total_Char = in.read(buf);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (Total_Char <= 0) return -1;
            }
            return buf[index++];
        }
 
        public int scanInt() {
            int integer = 0;
            int n = scan();
            while (isWhiteSpace(n)) n = scan();
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    integer *= 10;
                    integer += n - '0';
                    n = scan();
                }
            }
            return neg * integer;
        }
 
        private boolean isWhiteSpace(int n) {
            if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1) return true;
            else return false;
        }
 
    }
 
    static class CodeX {
        public static boolean[] sieve(int N) {
            boolean isPrime[] = new boolean[N + 1];
            for (int i = 0; i <= N; ++i) {
                isPrime[i] = true;
            }
            isPrime[0] = false;
            isPrime[1] = false;
            for (int i = 2; i * i <= N; ++i) {
                if (isPrime[i] == true) {
                    for (int j = i * i; j <= N; j += i)
                        isPrime[j] = false;
                }
            }
 
            return isPrime;
        }
 
    }
}