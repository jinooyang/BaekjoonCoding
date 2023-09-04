import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int temp;
	static int answer;
	static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		temp = 0;
		answer = 0;
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		bsearch(1, k);
		System.out.println(temp);
	}

	private static void bsearch(int s, int e) {
		if(s > e)return;
		int mid = (s + e) / 2;
	//	System.out.println("mid : " + mid);
		int total = 0;
		for (int i = 1; i <= n; i++) {
			total += Math.min(n, mid / i);
		}
		//System.out.println("total : " + total);
		
		if(total >= k) {
			temp = mid;
		}
		
		if (total >= k) {
			bsearch(s, mid - 1);
		}
		if (total < k) {
			bsearch(mid+1,e);
		}
	}
}