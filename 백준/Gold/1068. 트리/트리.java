import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int ary[] = new int[n];
		int childNodes[] = new int[n];
		boolean isNode[] = new boolean[n];
		Arrays.fill(isNode, true);
		st = new StringTokenizer(br.readLine());
		int root = -1;
		for (int i = 0; i < n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
			if (ary[i] != -1)
				childNodes[ary[i]]++;
			if (ary[i] == -1) {
				root = i;
			}
		}
		st = new StringTokenizer(br.readLine());
		int del = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new ArrayDeque<>();

		q.add(del);
		isNode[del] = false;
		if (ary[del] != -1) {
			childNodes[ary[del]]--;
		}
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int i = 0; i < n; i++) {
				if (now == ary[i]) {
					q.add(i);
					isNode[i] = false;
					if (ary[i] != -1) {
						childNodes[ary[i]]--;
					}
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < n; i++) {
			if (childNodes[i] == 0 && isNode[i]) {
				answer++;
			}
		}

		if (answer == 0) {
			if (isNode[root]) {
				answer = 1;
			}
		}
		System.out.println(answer);

	}
}