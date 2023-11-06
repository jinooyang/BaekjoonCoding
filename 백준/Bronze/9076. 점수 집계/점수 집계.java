import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int test = 0; test < T; test++) {
			st = new StringTokenizer(br.readLine());
			int ary[] = new int[5];
			for (int i = 0; i < 5; i++) {
				ary[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(ary);
			if(ary[3]-ary[1]<4) {
				sb.append(ary[1]+ary[2]+ary[3]).append("\n");
			}
			else {
				sb.append("KIN\n");
			}
		}
		System.out.println(sb);
	}
}
