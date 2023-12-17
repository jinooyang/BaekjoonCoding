import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		StringBuilder test = new StringBuilder("abcde");

//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		StringBuilder ans = new StringBuilder();
		if (K == 0) {
			for (int i = 0; i < N; i++) {
				ans.append("B");
			}
			System.out.println(ans);
			return;
		}
		// N으로 만들 수 있는 최대 개수는?
		int x = (int) Math.sqrt(K);
//		System.out.println("x : " + x);
		int max = 0;
		for (int i = 0; i <= x; i++) {
			int tempMax = i * (N - i);
			max = Math.max(max, tempMax);
		}
		if (max < K) {
			System.out.println(-1);
			return;
		}
		// 재귀로 문제를 푼다
		// 가장 큰 제곱수를 먼저 넣는다
		// 나머지는 이제 A사이에 끼워넣으면서 추가한다?
//		String answer = "";

		for (int i = 0; i < x; i++) {
//			answer += "A";
			ans.append("A");
		}

		for (int i = 0; i < x; i++) {
//			answer += "B";
			ans.append("B");
		}
		int remainN = N - x - x;
		K -= x * x;
//		System.out.println(ans);

		int start = 0;
		int end = x;

		while (true) {
			// 한개씩 추가한다
//			System.out.println("K : " + K);
			if (K == 0)
				break;
			int addIndex = 0;
			if (K > x) {
				addIndex = K == 1 ? K : (K % 2 == 0 ? K / 2 : K / 2 + 1);

			} else {
				addIndex = K;
			}
			StringBuilder front = new StringBuilder(ans.subSequence(0, addIndex));
			StringBuilder back = new StringBuilder(ans.subSequence(addIndex, ans.length()));
			ans = new StringBuilder().append(front).append("B").append(back);
			K -= addIndex;
		}

		int remainder = N - ans.length();
		for (int i = 0; i < remainder; i++) {
			ans.append("A");
		}
		System.out.println(ans.toString());

	}
}