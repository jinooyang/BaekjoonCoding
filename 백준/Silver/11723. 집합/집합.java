import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		StringBuilder sb= new StringBuilder();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			int x = 0;
			switch (s) {
			case "add":
				//System.out.println(1);
				x = Integer.parseInt(st.nextToken());
				list.add(x);
				break;
			case "remove":
				x = Integer.parseInt(st.nextToken());
				for (int j = list.size() - 1; j >= 0; j--) {
					if (list.get(j) == x)
						list.remove(j);
				}
				break;
			case "check":
				//System.out.println("a");
				x = Integer.parseInt(st.nextToken());
				boolean found = false;
				for (int j = list.size() - 1; j >= 0; j--) {
					if (list.get(j) == x)
						found = true;
				}
				if (found)
					sb.append(1 + "\n");
				else
					sb.append(0 + "\n");
				break;
			case "toggle":
				x = Integer.parseInt(st.nextToken());
				found = false;
				for (int j = list.size() - 1; j >= 0; j--) {
					if (list.get(j) == x) {
						found = true;
						list.remove(j);
					}
				}
				if (!found) {
					list.add(x);
				}
				break;
			case "all":
				list.clear();
				for(int j=0;j<20;j++) {
					list.add(j+1);
				}
				break;

			case "empty":
				list.clear();
				break;

			default:
				break;
			}
		}
		System.out.println(sb);

	}
}