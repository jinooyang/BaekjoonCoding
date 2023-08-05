import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 땅
		int q = Integer.parseInt(st.nextToken());// 오리
		int duckWish[] = new int[q];
		boolean check[] = new boolean[n + 1];
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int temp = Integer.parseInt(st.nextToken());// 오리가 가고 싶은 위치
			duckWish[i] = temp;
		}
//		for(int i=0;i<q;i++) {
//			System.out.print(duckWish[i] + " ");
//		}
		for (int i = 0; i < q; i++) {
			int answer = 0;
			// System.out.println("----");
			int want = duckWish[i];
			while (want >= 1) {
				// System.out.println(want);
				if (check[want]) {
					answer = want;
				}
				want = want / 2;
			}
			if (answer== 0) {
				check[duckWish[i]] = true;
			}
			System.out.println(answer);
		}

	}
}