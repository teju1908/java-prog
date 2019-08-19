import java.awt.event.ItemEvent;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;
 
public class ReversalSort implements Runnable {
    static final int MOD = (int) 1e9 + 7;
    static final int MI = (int) 1e9;
    static final long ML = (long) 1e18;
    static final Reader in = new Reader();
    static final PrintWriter out = new PrintWriter(System.out);
    StringBuilder answer = new StringBuilder();
 
    public static void main(String[] args) {
        new Thread(null, new ReversalSort(), "persefone", 1 << 32).start();
    }
 
    @Override
    public void run() {
        solve();
        printf();
        flush();
    }
 
    void solve() {
        for (int test = in.nextInt(); test > 0; test--) {
            int n = in.nextInt();
            int k = Math.min(in.nextInt(), n);
 
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = in.nextInt();
            }
 
            int[][] front_to_back = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    front_to_back[i][j] = front_to_back[i][j - 1];
                    for (int t = i; t < j; t++) {
                        if (a[t] > a[j]) {
                            front_to_back[i][j]++;
                        }
                    }
                }
            }
 
            int[][] back_to_front = new int[n + 1][n + 1];
            for (int i = n; i > 0; i--) {
                for (int j = i - 1; j > 0; j--) {
                    back_to_front[i][j] = back_to_front[i][j + 1];
                    for (int t = i; t > j; t--) {
                        if (a[t] > a[j]) {
                            back_to_front[i][j]++;
                        }
                    }
                }
            }
            int[][] dp = new int[n + 1][k + 1];
 
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= k; j++) {
                    dp[i][j] = dp[i - 1][j];
                    for (int t = i; t > 0; t--) {
                        dp[i][j] = Math.min(dp[i][j], dp[t - 1][j - 1] + back_to_front[i][t] - front_to_back[t][i]);
                    }
                }
            }
            printf(front_to_back[1][n] + Arrays.stream(dp[n]).min().getAsInt());
        }
    }
 
    void printf() {
        out.print(answer);
    }
 
    void close() {
        out.close();
    }
 
    void flush() {
        out.flush();
    }
 
    void printf(Stream<?> str) {
        str.forEach(o -> add(o, " "));
        add("\n");
    }
 
 
    void printf(Object... obj) {
        printf(false, obj);
    }
 
    void printfWithDescription(Object... obj) {
        printf(true, obj);
    }
 
 
    private void printf(boolean b, Object... obj) {
 
        if (obj.length > 1) {
            for (int i = 0; i < obj.length; i++) {
                if (b) add(obj[i].getClass().getSimpleName(), " - ");
                if (obj[i] instanceof Collection<?>) {
                    printf((Collection<?>) obj[i]);
                } else if (obj[i] instanceof int[][]) {
                    printf((int[][]) obj[i]);
                } else if (obj[i] instanceof long[][]) {
                    printf((long[][]) obj[i]);
                } else if (obj[i] instanceof double[][]) {
                    printf((double[][]) obj[i]);
                } else printf(obj[i]);
            }
            return;
        }
 
        if (b) add(obj[0].getClass().getSimpleName(), " - ");
        printf(obj[0]);
    }
 
    void printf(Object o) {
        if (o instanceof int[])
            printf(Arrays.stream((int[]) o).boxed());
        else if (o instanceof char[])
            printf(new String((char[]) o));
        else if (o instanceof long[])
            printf(Arrays.stream((long[]) o).boxed());
        else if (o instanceof double[])
            printf(Arrays.stream((double[]) o).boxed());
        else if (o instanceof boolean[]) {
            for (boolean b : (boolean[]) o) add(b, " ");
            add("\n");
        } else
            add(o, "\n");
    }
 
    void printf(int[]... obj) {
        for (int i = 0; i < obj.length; i++) printf(obj[i]);
    }
 
    void printf(long[]... obj) {
        for (int i = 0; i < obj.length; i++) printf(obj[i]);
    }
 
    void printf(double[]... obj) {
        for (int i = 0; i < obj.length; i++) printf(obj[i]);
    }
 
    void printf(boolean[]... obj) {
        for (int i = 0; i < obj.length; i++) printf(obj[i]);
    }
 
    void printf(Collection<?> col) {
        printf(col.stream());
    }
 
    <T, K> void add(T t, K k) {
        if (t instanceof Collection<?>) {
            ((Collection<?>) t).forEach(i -> add(i, " "));
        } else if (t instanceof Object[]) {
            Arrays.stream((Object[]) t).forEach(i -> add(i, " "));
        } else
            add(t);
        add(k);
    }
 
 
    <T> void add(T t) {
        answer.append(t);
    }
 
    static class Reader {
        private BufferedReader br;
        private StringTokenizer st;
 
        Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
 
        Reader(String fileName) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(fileName));
        }
 
        boolean isReady() throws IOException {
            return br.ready();
        }
 
        String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }
 
        int nextInt() {
            return Integer.parseInt(next());
        }
 
        int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = nextInt();
            return arr;
        }
 
        long nextLong() {
            return Long.parseLong(next());
        }
 
        String nextLine() {
            String s = "";
            try {
                s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }
    }
}
