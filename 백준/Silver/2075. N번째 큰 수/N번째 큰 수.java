import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//1500 * 1500 = 2,250,000
//인풋 n^2 

//정렬 (2,250,000) * log(2,250,000)
// = 2,250,000 * 21
// = 47,250,000

//내림차순으로 정렬하면 시간 초과난다 ..이유는?

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		Integer ary[] = new Integer[n * n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				ary[i * n + j] = Integer.parseInt(st.nextToken());
			}
		}
		// 시간초과
//		 Arrays.sort(ary, Collections.reverseOrder());
//		 System.out.println(ary[n - 1]);

		// 정답
		Arrays.sort(ary);
		System.out.println(ary[n * n - n]);
	}
}