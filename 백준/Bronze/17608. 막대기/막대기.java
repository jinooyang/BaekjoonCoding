import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		Stack<Integer> s = new Stack<>();
		for(int i=0;i<n;i++) {
			int x = Integer.parseInt(br.readLine());
			while(!s.isEmpty() && s.peek() <=x) {
				s.pop();
			}
			s.add(x);
		}
//		System.out.println(s.toString());
		System.out.println(s.size());
	}
}