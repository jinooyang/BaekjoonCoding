import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double a = Integer.parseInt(st.nextToken());
		double b = Integer.parseInt(st.nextToken());
		double v = Integer.parseInt(st.nextToken());

		double day = (v - a) / (a - b);
	//	System.out.println(day);
		System.out.println((int)Math.ceil(day) + 1);

	}
}