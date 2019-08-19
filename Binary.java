import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.*;
 
import static java.lang.Math.*;
 
public class Binary implements Runnable 
{
	static class InputReader 
	{
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;
		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		public InputReader(InputStream stream) 
		{
			this.stream = stream;
		}
		
		public int read()
		{
			if (numChars==-1) 
				throw new InputMismatchException();
            
			if (curChar >= numChars) 
			{
				curChar = 0;
				try
				{
					numChars = stream.read(buf);
				}
				catch (IOException e)
				{
					throw new InputMismatchException();
				}
                
				if(numChars <= 0)               
					return -1;
			}
			return buf[curChar++];
		}
     
		public String nextLine()
		{
			String str = "";
			try
			{
				str = br.readLine();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}	
			return str;
		}
		public int nextInt() 
		{
			int c = read();
            
			while(isSpaceChar(c)) 
				c = read();
		
			int sgn = 1;
        
			if (c == '-') 
			{
				sgn = -1;
				c = read();
			}
            
			int res = 0;
			do
			{
				if(c<'0'||c>'9') 
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			while (!isSpaceChar(c)); 
        
			return res * sgn;
		}
        
		public long nextLong() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-')
			{
				sgn = -1;
				c = read();
			}
			long res = 0;
			
			do 
			{
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}	
			while (!isSpaceChar(c));
				return res * sgn;
		}
		
		public double nextDouble() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-')
			{
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') 
			{
				if (c == 'e' || c == 'E')
					return res * Math.pow(10, nextInt());
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') 
			{
				c = read();
				double m = 1;
				while (!isSpaceChar(c))
				{
					if (c == 'e' || c == 'E')
						return res * Math.pow(10, nextInt());
					if (c < '0' || c > '9')
						throw new InputMismatchException();
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
			return res * sgn;
		}
    
		public String readString() 
		{
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do 
			{
				res.appendCodePoint(c);
				c = read();
			} 
			while (!isSpaceChar(c));
            
			return res.toString();
		}
     
		public boolean isSpaceChar(int c) 
		{
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
     
		public String next()
		{
			return readString();
		}
        
		public interface SpaceCharFilter
		{
			public boolean isSpaceChar(int ch);
		}
	}
	public static void main(String args[]) throws Exception 
	{
		new Thread(null, new Binary(),"Main",1<<27).start();
	}
	@SuppressWarnings("unchecked")
	public static class GraphUndirected
	{
		int v;
		ArrayList<Integer> adjListArray[];
		
		GraphUndirected(int v)
		{
			this.v=v;
			
			adjListArray = new ArrayList[v]; 
			int i;
			for(i=0;i<v;i++)
			{
				adjListArray[i] = new ArrayList<>();
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static class GraphDirected
	{
		int v;
		ArrayList<Integer> adj[];
		
		GraphDirected(int v)
		{
			this.v=v;
			
			adj = new ArrayList[v]; 
			int i;
			for(i=0;i<v;i++)
			{
				adj[i] = new ArrayList<>();
			}
		}
	}@SuppressWarnings("unchecked")
	
	public static void addEdgeDirected(GraphDirected graph,int v, int w)
	{
		graph.adj[v].add(w);
	}
	@SuppressWarnings("unchecked")
	public static void addEdgeUnDirected(GraphUndirected graph, int start, int end)
	{
		graph.adjListArray[start].add(end); 
		graph.adjListArray[end].add(start); 
	}
	@SuppressWarnings("unchecked")
	public static void printGraph(GraphUndirected graph)
	{
		int i,j;
		for(i=0;i<graph.v;i++)
		{
			//System.out.println("Adjacency List Vertex" + i);
			Iterator<Integer> at=graph.adjListArray[i].iterator();
			while(at.hasNext())
			{
				int x=at.next();
				System.out.print(x + " ");
			}
			System.out.println();
		}
	}
	@SuppressWarnings("unchecked")
	public static int BFS(int i,boolean visited[] , ArrayList<ArrayList<Integer>> g)
	{
		Queue<Integer> queue = new LinkedList<>();
		visited[i]=true;
		queue.add(i);
		ArrayList<Integer> bfs = new ArrayList<>();
		while(queue.size()!=0)
		{
			i=queue.poll();
			bfs.add(i);
			
			Iterator<Integer> it = g.get(i).listIterator(); 
			while (it.hasNext()) 
			{ 
				int n = it.next(); 
				if(!visited[n]) 
				{ 
					visited[n] = true; 
					queue.add(n); 
				} 
			} 
		}
		return bfs.size();
	}
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer> dfsutil = new ArrayList<>();
	
	public static void DFSUtil(GraphDirected g,int v,boolean visited[]) 
	{ 
		visited[v] = true; 
		dfsutil.add(v);
		Iterator<Integer> i = g.adj[v].listIterator(); 
		while (i.hasNext()) 
		{ 
			int n = i.next(); 
			if (!visited[n]) 
				DFSUtil(g,n, visited); 
		}
	} 
	@SuppressWarnings("unchecked")
	public static void DFS(GraphDirected g,int v) 
	{ 
		boolean visited[] = new boolean[g.v]; 
		DFSUtil(g,v, visited); 
	} 
	@SuppressWarnings("unchecked")
	public static int findMother(GraphDirected g)
	{
		boolean visited[] = new boolean[g.v];
		int v=0;
		int i;
		for(i=0;i<g.v;i++)
		{
			if(visited[i]==false)
			{
				DFSUtil(g,i,visited);
				v=i;
			}
		}
		
		DFSUtil(g,v,visited);  
		for (i=0; i<g.v; i++) 
		{
			if(visited[i] == false) 
				return -1; 
		}
		return v; 
	}
	
	public static ArrayList<Integer> list = new ArrayList<>();
	
	public static void inorderTraversal(int i,int arr[],int n)
	{
		int l = i<<1;
		int r = l+1;
		if(l<=n)
		{
			inorderTraversal(l,arr,n);
		}
		list.add(arr[i]);
		if(r<=n)
		{
			inorderTraversal(r,arr,n);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void run()
	{
		InputReader in = new InputReader(System.in);
		PrintWriter w = new PrintWriter(System.out);
		int n = in.nextInt();
		int i,j,k;
		int arr[] = new int[2*n+2];
		for(i=0;i<n;i++)
		{
			arr[i+1]=in.nextInt();
		}
		inorderTraversal(1,arr,n);
		for(i=0;i<n;i++)
		{
			arr[i]=list.get(i);
		}
		Collections.sort(list);
		int ans=0,temp;
		for(i=0;i<n-1;i++)
		{
			k=i;
			if(arr[i]!=list.get(i))
			{
				for(j=i;j<n;j++)
				{
					if(arr[j]==list.get(i))
						break;
				}
				k=j;
			}
			if(k!=i)
			{
				temp=arr[i];
				arr[i]=arr[k];
				arr[k]=temp;
				ans++;
			}
		}
		w.println(ans);
		w.flush();
		w.close();
	}
}