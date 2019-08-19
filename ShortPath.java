import java.io.*;
import java.util.*;
class ShortPath{
    static class Parser{
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
        public Parser(InputStream in){
            din = new DataInputStream(in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0; }
        public long nextLong() throws Exception {
            long ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = c == '-';
            if (neg) c = read();
            do { ret = ret * 10 + c - '0';
                c = read();
            } while (c > ' ');
            if (neg) return -ret;
            return ret; }
        //reads in the next string
        public String next() throws Exception{
            StringBuilder ret = new StringBuilder();
            byte c = read();
            while (c <= ' ') c = read();
            do {
                ret = ret.append((char)c);
                c = read();
            } while (c > ' ');
            return ret.toString(); }
        public int nextInt() throws Exception {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = c == '-';
            if (neg) c = read();
            do {
                ret = ret * 10 + c - '0';
                c = read(); } while (c > ' ');
            if (neg) return -ret;
            return ret; }
        private void fillBuffer() throws Exception{
            bytesRead = din.read(buffer,
                    bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1; }
        private byte read() throws Exception {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++]; }} 
    public static void main(String args[]) throws Exception {
        Parser S=new Parser(System.in);
        PrintWriter out =new PrintWriter(System.out);
		int n=S.nextInt();
		int m=S.nextInt();
		int f=S.nextInt();
		int c[]=new int[n];
		for(int i=0;i<n;i++)
			c[i]=S.nextInt();
		ArrayList<node> a[]=new ArrayList[n];
		for(int i=0;i<n;i++)
			a[i]=new ArrayList<node>();
		for(int i=0;i<m;i++) {
			int u=S.nextInt()-1;
			int v=S.nextInt()-1;
			long w=S.nextInt();
			a[u].add(new node(v,w));
			a[v].add(new node(u,w));
		}
		int k=S.nextInt();
		node e[]=new node[n];
		for(int i=0;i<n;i++)
			e[i]=new node(i,Long.MAX_VALUE);
		boolean vis[]=new boolean[n];
		Arrays.fill(vis,false);
		e[0].w=0;
		PriorityQueue<node> q=new PriorityQueue<node>(1,new Comparator<node>() {
			@Override
			public int compare(node a,node b) {
				return ((Long)a.w).compareTo((Long)b.w);
			}
		});
		q.add(e[0]);
		while(!q.isEmpty()) {
			node curr=q.poll();
			if(vis[curr.v])
				continue;
			vis[curr.v]=true;
			for(node it:a[curr.v]) {
				if(!vis[it.v]) {
					if(e[it.v].w>it.w+e[curr.v].w) {
						e[it.v].w=it.w+e[curr.v].w;
						q.add(new node(it.v,e[it.v].w));
					}
				}
			}
		}//while
		Arrays.sort(e,new Comparator<node>() {
			@Override
			public int compare(node a,node b) {
				return ((Long)a.w).compareTo((Long)b.w);
			}
		});
		int index=0;
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<k;i++){
			long val=-1;
			if(c[e[index].v]>0) {
				c[e[index].v]--;
				val=e[index].w+(long)f;
			}
			else if(index<n-1) {
				index++;
				c[e[index].v]--;
				val=e[index].w+(long)f;
			}
			sb.append(val+" ");
		}
		out.println(sb);
        //out.println(-1);s.next()
        out.close();
    }//main
	static class node{
		int v;
		long w;
		node(int vv,long ww){
			v=vv;
		    w=ww;
		}
	}
}//class