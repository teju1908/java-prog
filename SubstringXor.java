import java.io.*;
import java.util.*;
class SubstringXor 
{
    public static void main(String args[] ) throws Exception 
    {
        InputReader sc = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        try
        {
            int N = sc.readInt();
            long k = sc.readLong();
            int arr1[] = IOUtils.readIntArray(sc,N);
            int M = 1<<20;
            int arr2[] = new int[M];
            int x = 0;
            for(int i=19;i>=0;i--)
            {
                int k1 = 1<<i;
                int mask = ~(k1-1);
                for(int j=0;j<M;j+=k1)
                    arr2[j] = 0;
                arr2[0] = 1;
                x+=k1;
                int j = 0;
                long count = 0;
                for(int c=0;c<N;c++)
                {
                    j ^= arr1[c] & mask;
                    count +=  arr2[j ^ x];
                    arr2[j]++;
                }
                if(k > count)
                {
                    k -= count;
                    x -= k1;
                }
            }
            out.println(x);
        }
        catch(Exception e)
        {
            out.println(e);
            out.println(e.getMessage());
        }
        out.flush();
        out.close();
    }
}
class InputReader {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;
 
	public InputReader(InputStream stream) {
		this.stream = stream;
	}
 
	public int read() {
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
 
	public int readInt() {
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
 
	public long readLong() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		long sgn = 1;
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
 
	public String readString() {
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
 
	public boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}
 
	public String next() {
		return readString();
	}
 
	public interface SpaceCharFilter {
		boolean isSpaceChar(int ch);
	}
}
 
class OutputWriter {
	private final PrintWriter writer;
 
	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				outputStream)));
	}
 
	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
	}
 
	public void print(Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			if (i != 0)
				writer.print(' ');
			writer.print(objects[i]);
		}
	}
 
	public void println(Object... objects) {
		print(objects);
		writer.println();
	}
 
	public void close() {
		writer.close();
	}
 
	public void flush() {
		writer.flush();
	}
 
}
 
class IOUtils {
	public static int[] readIntArray(InputReader in, int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++)
			array[i] = in.readInt();
		return array;
	}
 
	public static long[] readLongArray(InputReader in, int size) {
		long[] array = new long[size];
		for (int i = 0; i < size; i++)
			array[i] = in.readLong();
		return array;
	}
 
	public static String[] readArray(InputReader in, int size) {
		String[] array = new String[size];
		for (int i = 0; i < size; i++)
			array[i] = in.readString();
		return array;
	}
}