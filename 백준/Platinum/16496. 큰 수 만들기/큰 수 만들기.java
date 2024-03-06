import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("test.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		String[] ary = new String[n];

		st = new StringTokenizer(br.readLine());
		boolean isZero = true;
		for (int i = 0; i < n; i++) {
			ary[i] = st.nextToken();
			if(!ary[i].equals("0"))isZero = false;
		}
		if(isZero){
			System.out.println("0");
			return;
		}
		Arrays.sort(ary, Collections.reverseOrder());
		for (int i = n - 1; i > 0; i--) {
			String a = ary[i];
			for (int j = i - 1; j >= 0; j--) {
				String b = ary[j];
				if ((b + a).compareTo(a + b) < 0) {
					ary[j + 1] = ary[j];
					ary[j] = a;
					// printAry(n, ary);

				} else {
					// printAry(n, ary);
					break;
				}
			}
		}
		printAry(n, ary);
	}

	private static void printAry(int n, String[] ary) {
		for (int i = 0; i < n; i++) {
			System.out.print(ary[i]);
		}
		System.out.println();
	}

}
