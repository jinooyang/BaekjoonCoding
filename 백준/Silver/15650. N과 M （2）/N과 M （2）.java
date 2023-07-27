import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static int n = 0;
	public static int m = 0;
	public static List<Integer> list = new ArrayList<>();

	public static void funcA(int num, int digit) {
		if (digit > m) {// 여기까지왔으면 리스트 다 채우기성공한거임. 출력
			list.forEach(l -> System.out.print(l + " "));
			System.out.println();
			list.remove(list.size() - 1);
			return;
		}

		if (num > n) {
			// 사용할 수 있는 숫자를 모두 사용. 망했음 . 리스트 맨 뒤를 지우고 리턴하자
			// 혹은 위에서 출력하고 성공적이었음
			list.remove(list.size() - 1);
			return;
		}

		// 일반적인 경우
		for (int i = num; i <= n; i++) {
			list.add(i);
			funcA(i + 1, digit + 1);
		}
		if (list.size() == 0)
			return;
		list.remove(list.size() - 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		funcA(1, 1);
	}
}