import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Solution {

	public static void main(String args[]) throws Exception {
		Set<Character> set = new HashSet<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = null;
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {

			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int answer = 1;
			// 입력
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				int nodeNumber = Integer.parseInt(st.nextToken());
				char value = st.nextToken().charAt(0);
				int leftNode = 0;
				int rightNode = 0;
				if (i <= (n / 2)) {
					leftNode = Integer.parseInt(st.nextToken());
					if (st.hasMoreTokens())
						rightNode = Integer.parseInt(st.nextToken());
				}
				// 계산
				// 연산자는 자식이 두개 있어야한다
				// 숫자는 자식이 있으면 안된다

				if (value == '*' || value == '/' || value == '+' || value == '-') {
					// 연산자 일 경우
					if (leftNode == 0 || rightNode == 0) {// 자식이 둘 있어야한다
						//System.out.println("test_case : " + test_case + "operator has no child : " + i);
						answer = 0;
					}
				} else {
					// 숫자일 경우
					if (leftNode != 0 || rightNode != 0) {// 자식이 있으면 안된다
						//System.out.println("test_case : " + test_case + "number has child : " + i);
						//System.out.println("value is : " + value);
						answer = 0;
					}
				}
			}

			System.out.println("#" + test_case + " " + answer);
		}
		//System.out.println("fin");
	}

}