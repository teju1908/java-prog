import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
public class TreeArray {
	public static void main(String[] args) throws IOException {
		Reader S=new Reader();
		int n=S.nextInt();
		int q=S.nextInt();
		long sum[]=new long[n];
		long ss=0;
		TreeMap<Integer,Integer> h=new TreeMap<Integer,Integer>();
		for(int i=0;i<n;i++) {
			int x=S.nextInt();
			if(h.get(x)==null)
				h.put(x,1);
			else
			{
				int y=h.get(x);
				h.put(x,y+1);
			}
			ss+=x;
		}
		sum[0]=ss;
		for(int i=0;i<n-1;i++) {
			int x=h.firstEntry().getValue()-1;
			int y=h.lastEntry().getValue()-1;
			int xx=h.firstEntry().getKey();
			int yy=h.lastEntry().getKey();
			if(x==0)
				h.pollFirstEntry();
			else
				h.put(xx,x);
			if(y==0)
				h.pollLastEntry();
			else
				h.put(yy,y);
			if(h.get(yy-xx)==null)
				h.put(yy-xx,1);
			else {
				int zz=h.get(yy-xx);
				h.put(yy-xx,zz+1);
			}
			ss=ss-xx-xx;
			sum[i+1]=ss;
		}
		for(int i=0;i<q;i++) {
			System.out.println(sum[S.nextInt()]);
		}//for
	}//main
 
	static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
 
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
 
            if (neg)
                return -ret;
            return ret;
        }
 
        public char nextChar() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
 
            return (char) c;
        }
 
        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
 
        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
 
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
 
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
 
            if (neg)
                return -ret;
            return ret;
        }
 
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }//reader
}//class