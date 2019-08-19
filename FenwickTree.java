import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.util.Random;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.InputStream;

public class FenwickTree{
 
  public static void main(String[] args) {
    InputStream inputStream = System.in;
    OutputStream outputStream = System.out;
    InputReader in = new InputReader(inputStream);
    PrintWriter out = new PrintWriter(outputStream);
    PandaAndShopping solver = new PandaAndShopping();
    solver.solve(1, in, out);
    out.close();
  }
 
  static class PandaAndShopping {
 
    int[] h;
    long[] l;
    long[] tree;
 
    void update(int root, int l, int r, int pos, long value) {
      if (l == r) {
        tree[root] = value;
      } else {
        int mid = (l + r) >> 1;
        if (pos <= mid) {
          update(root << 1, l, mid, pos, value);
        } else {
          update(root << 1 | 1, mid + 1, r, pos, value);
        }
        tree[root] = Math.max(tree[root << 1], tree[root << 1 | 1]);
      }
    }
 
    long query(int root, int l, int r, int a, int b) {
      if (a > r || b < l) {
        return 0;
      }
      if (a <= l && r <= b) {
        return tree[root];
      }
      int mid = (l + r) >> 1;
      long left = query(root << 1, l, mid, a, b);
      long right = query(root << 1 | 1, mid + 1, r, a, b);
      return Math.max(left, right);
    }
 
    public void solve(int testNumber, InputReader in, PrintWriter out) {
      int n = in.nextInt();
      List<Integer> hList = new ArrayList<>(n);
      List<Long> lList = new ArrayList<>(n);
      for (int i = 0; i < n; ++i) {
        int curH = in.nextInt();
        long curL = in.nextLong();
        if (curH < 0) {
          continue;
        }
        hList.add(curH);
        lList.add(curL);
      }
      h = new int[hList.size()];
      l = new long[hList.size()];
      for (int i = 0; i < hList.size(); ++i) {
        h[i] = hList.get(i);
        l[i] = lList.get(i);
      }
      int[] compressedL = ArrayUtils.compressSorted(l);
      n = h.length;
      int size = ArrayUtils.max(compressedL) + 1;
      tree = new long[size << 2];
      // build(1, 0, n - 1);
 
      long[] dp = new long[h.length];
      for (int i = 0; i < n; ++i) {
        dp[i] = query(1, 0, size, 0, compressedL[i]) + h[i];
        update(1, 0, size, compressedL[i], dp[i]);
      }
 
      out.println(ArrayUtils.max(dp));
    }
 
  }
 
  static class InputReader implements FastInput {
 
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer;
    private int bytesRead;
 
    public InputReader(InputStream is) {
      din = new DataInputStream(is);
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
    }
 
    public InputReader(String file_name) throws IOException {
      din = new DataInputStream(new FileInputStream(file_name));
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
    }
 
    public int nextInt() {
      int ret = 0;
      byte c = read();
      while (c <= ' ') {
        c = read();
      }
      boolean neg = (c == '-');
      if (neg) {
        c = read();
      }
      do {
        ret = ret * 10 + c - '0';
      } while ((c = read()) >= '0' && c <= '9');
 
      if (neg) {
        return -ret;
      }
      return ret;
    }
 
    public long nextLong() {
      long ret = 0;
      byte c = read();
      while (c <= ' ') {
        c = read();
      }
      boolean neg = (c == '-');
      if (neg) {
        c = read();
      }
      do {
        ret = ret * 10 + c - '0';
      }
      while ((c = read()) >= '0' && c <= '9');
      if (neg) {
        return -ret;
      }
      return ret;
    }
 
    private void fillBuffer() {
      try {
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
      } catch (IOException e) {
        throw new RuntimeException("trying to read from null input, verify constraints ");
      }
      if (bytesRead == -1) {
        buffer[0] = -1;
      }
    }
 
    private byte read() {
      if (bufferPointer == bytesRead) {
        fillBuffer();
      }
      return buffer[bufferPointer++];
    }
 
  }
 
  static interface FastInput {
 
  }
 
  static class ArrayUtils {
 
    public static int max(int[] arr) {
      int val = arr[0];
      for (int i = 1; i < arr.length; i++) {
        if (arr[i] > val) {
          val = arr[i];
        }
      }
      return val;
    }
 
    public static long max(long[] arr) {
      long val = 0;
      for (int i = 0; i < arr.length; i++) {
        if (arr[i] > val) {
          val = arr[i];
        }
      }
      return val;
    }
 
    public static void sort(long[] arr) {
      int n = arr.length;
      Random r = new Random();
      for (int i = 0; i < n; i++) {
        int p = r.nextInt(n + 1);
        if (p < n) {
          long temp = arr[i];
          arr[i] = arr[p];
          arr[p] = temp;
        }
      }
      Arrays.sort(arr);
    }
 
    public static int[] compressSorted(long[] val) {
      int n = val.length;
      long[] copy = copy(val);
      int[] ret = new int[n];
      sort(copy);
      int pt = 1;
      Map<Long, Integer> vals = new HashMap<>();
      for (int i = 0; i < n; ++i) {
        if (!vals.containsKey(copy[i])) {
          vals.put(copy[i], pt++);
        }
      }
      for (int i = 0; i < n; ++i) {
        ret[i] = vals.get(val[i]);
      }
      return ret;
    }
 
    public static long[] copy(long[] src) {
      long[] cp = new long[src.length];
      System.arraycopy(src, 0, cp, 0, src.length);
      return cp;
    }
 
  }
}