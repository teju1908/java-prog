import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
class Productive {
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-- > 0) {
			int n = sc.nextInt();
			int mat[][] = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					mat[i][j] = sc.nextInt();
				}
			}
			
			System.out.println(HungarianAlgorithm(mat, n));
 
		}
	}
	
	
	public static int HungarianAlgorithm (int[][] map, int n)
	{
		int res=  0;
		
		int[] match = biPartiteKuhnMunkres(map);
		for (int i = 0; i <n; i++) {
			res+=map[match[i]][i];
		}
		
		return res;
	}
 
 
	private static int[] biPartiteKuhnMunkres(int[][] map) {
		int nx = map.length; 	   // Map row numbers
		int ny = map[0].length;    // Map Col numbers
		int[] slack = new int[ny]; // Number of worker array
		int[] lx = new int[nx];    // max cost array for a job
		int[] ly = new int[ny];    //
		int[] matched = new int[ny];
		boolean[] vx = new boolean[nx];
		boolean[] vy = new boolean[ny];
		Arrays.fill(matched, -1);
 
		// Max Cost for a Job
		for (int i = 0; i < nx; i++) {
			for (int j = 0; j < ny; j++) {
				lx[i] = Math.max(lx[i], map[i][j]);
			}
		}
		for (int i = 0; i < nx; i++) {
			Arrays.fill(slack, Integer.MAX_VALUE);
			while (true) {
				Arrays.fill(vx, false);
				Arrays.fill(vy, false);
				int d = Integer.MAX_VALUE;
				if (dfs(i, lx, ly, vx, vy, matched, slack, map, nx, ny)) {
					break;
				}
				for (int j = 0; j < ny; j++) {
					if (!vy[j]) {
						d = Math.min(d, slack[j]);
					}
				}
				for (int j = 0; j < nx; j++) {
					if (vx[j]) {
						lx[j] -= d;
					}
 
				}
				for (int j = 0; j < ny; j++) {
					if (vy[j]) {
						ly[j] += d;
					} else {
						slack[j] -= d;
					}
				}
			}
		}
 
		return matched;
	}
 
 
	private static boolean dfs(int x,
			int[] lx, 
			int[] ly,
			boolean[] vx, 
			boolean[] vy,
			int[] matched,
			int[] slack,
			int[][] map,
			int nx,
			int ny) {
		vx[x] = true;
		boolean result = false;
		for (int i = 0; i < ny; i++) {
			if (!vy[i]) {
				int diff = lx[x] + ly[i] - map[x][i];
				if (diff == 0) {
					vy[i] = true;
					if (matched[i] == -1 || 
							dfs(matched[i], lx, ly, vx, vy, matched, slack, map, nx, ny)) {
						matched[i] = x;
						result = true;
						break;
					}
				} else if (slack[i] > diff) {
					slack[i] = diff;
				}
			}
		}
		return result;
	}
}