import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		char before = '#';
		int oneCnt = 0;
		int zeroCnt = 0;
		boolean added = false;
		for (int i = 0; i < s.length(); i++) {
			if (before != s.charAt(i)) {
				if (before != '#') {
					if (before == '0')
						zeroCnt++;
					if (before == '1')
						oneCnt++;
				}
			}
			before = s.charAt(i);
			
		}
		if (before == '0')
			zeroCnt++;
		if (before == '1')
			oneCnt++;
		System.out.println(Math.min(zeroCnt, oneCnt));

	}
}