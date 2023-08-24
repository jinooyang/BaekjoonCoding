import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numCost[] = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
		int alphabet[] = { 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9 };
		String s = st.nextToken();
		int answer =0;
		for (int i = 0; i < s.length(); i++) {
			int n = s.charAt(i) - 'A';
			int dial = alphabet[n];
			answer += numCost[dial];
		}
		System.out.println(answer);
	}
}