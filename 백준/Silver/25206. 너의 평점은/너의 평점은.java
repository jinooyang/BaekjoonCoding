import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		double total = 0;
		int cnt = 0;
		for (int i = 0; i < 20; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			double points = Double.parseDouble(st.nextToken());
			String score = st.nextToken();

			switch (score) {
			case "A+":
				total += points * 4.5;
				cnt += points;
				break;

			case "A0":
				total += points * 4.0;
				cnt += points;
				break;
			case "B+":
				total += points * 3.5;
				cnt += points;
				break;

			case "B0":
				total += points * 3.0;
				cnt += points;
				break;
			case "C+":
				total += points * 2.5;
				cnt += points;
				break;

			case "C0":
				total += points * 2.0;
				cnt += points;
				break;
			case "D+":
				total += points * 1.5;
				cnt += points;
				break;

			case "D0":
				total += points * 1.0;
				cnt += points;
				break;
			case "F":
				//total += points * 0;
				cnt += points;
				break;
			case "P":
				//total += points * 0;

				break;

			default:
				break;
			}

		}
		if (cnt == 0)
			System.out.println(0);
		else
			System.out.println(total / cnt);
	}
}