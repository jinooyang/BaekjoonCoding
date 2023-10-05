import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int len = n / 4;
			String s = br.readLine();
			Set<Integer> set = new TreeSet<>(Collections.reverseOrder());
			for (int i = 0; i < n; i++) {
				// 회전
				for (int j = 0; j < n; j = j + len) {
					String temp = s.substring(j, j + len);
					int num = Integer.parseInt(temp, 16);
					set.add(num);
				}
				char c = s.charAt(s.length() - 1);
				s = s.substring(0, s.length() - 1);
				s = c + s;
			}
			int i = 1;
			int answer = 0;
			for (Integer num : set) {
				if (i == k) {
					answer = num;
					break;
				}
				i++;
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

}