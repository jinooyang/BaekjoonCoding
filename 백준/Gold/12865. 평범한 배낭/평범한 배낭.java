import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 물품 수
		int k = Integer.parseInt(st.nextToken());// 최대무게 K
		int ary[] = new int[k + 1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			for (int x = k - w; x >= 0; x--) {
				int newVal = ary[x] + v;
				if (newVal > ary[x + w]) { // 갱신된 값을 또 갱신하면 안됨
					ary[x + w] = newVal;
				}
			}
		}
		System.out.println(ary[k]);
	}
}