import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static class Node {
		int num;

		Map<Integer, Integer> map;

		public Node(int num, Map<Integer, Integer> map) {
			this.num = num;
			this.map = map;
		}
	}

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		//모든 공차를 구한다
		List<Integer> ary = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			ary.add(x);
			if (!map.containsKey(x))
				map.put(x, 0);
			map.put(x, map.get(x) + 1);
		}
		int answer =0 ;
		for(Integer num : map.keySet()){
			answer = Math.max(answer,map.get(num));
		}
		Collections.sort(ary);
		Set<Integer> set = new TreeSet<>(ary);
		if (set.size() == 1) {
			System.out.println(N);
			return;
		}
		List<Node> list = new ArrayList<>();
		for (Integer next : set) {
			list.add(new Node(next, new HashMap<>()));
		}

		for (int i = 0; i < list.size(); i++) {
			for (int j = 1; j <= i; j++) {
				//내 앞에 애들을 확인한다
				int diff = list.get(i).num - list.get(i - j).num;
				int cnt = 1;
				if (list.get(i - j).map.containsKey(diff))
					cnt = list.get(i - j).map.get(diff);
				list.get(i).map.put(diff, cnt + 1);
				answer = Math.max(cnt + 1, answer);
				// System.out.println(list.get(i).num + " : " + diff + " " + (cnt+1) + "개" );
			}
			// System.out.println(list.get(i).map.toString());
		}
		System.out.println(answer);
	}

}
