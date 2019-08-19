import java.util.*;
 
public class StatisticsOfStrings {
	static int[] ar;
	
	static int calculatePower(int a, int b, int mod) {
		if (b == 0) {
			return 1;
		}
		int power = calculatePower(a, b / 2, mod);
		power = (int) ((long) power * power % mod);
		if (b % 2 == 1) {
			power = (int) ((long) power * a % mod);
		}
		return power;
	}
	
	static int find(int i) {
		if (ar[i] < 0) 
			return i;
		 else 
			return ar[i] = find(ar[i]);
		
	}
	
	static boolean join(int i, int j) {
		i = find(i);
		j = find(j);
		if (i == j)
			return false;
		if (ar[i] > ar[j])
			ar[i] = j;
		else {
			if (ar[i] == ar[j])
				ar[i] -= 1;
			ar[j] = i;
		}
		return true;
	}
	
	static int solve(int m, int v, int n, int a, int mod) {
		Arrays.fill(ar, -1);
		int bcnt = 0, cnt = n;
		for (int i = 1; i < n; i++)
			if ((v & 1 << i) > 0) {
				for (int j = i; j < i + m; j++)
					if (join(j, j - i))
						cnt--;
				bcnt++;
			}
		int s = calculatePower(a, cnt, mod);
		return bcnt % 2 == 1 ? s : (mod - s) % mod;
	}
	
	static int solution(int n, int a, int mod) {
		ar = new int[n];
		int sum = 0;
		for (int k = 1; k < n; k++) {
			for (int v = 2; v < 1 << n - k + 1; v += 2) {
				sum = (sum + solve(k, v, n, a, mod)) % mod;
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int a = sc.nextInt();
		int mod = sc.nextInt();
		int res = solution(n, a, mod);
		System.out.println(res);
	}
}