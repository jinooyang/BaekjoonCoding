import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static long answer = 0;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();
		String c = br.readLine();

		add(a);
		add(b);
		mul(c);
		System.out.println(answer);
	}

	private static void add(String a) {
		answer = answer * 10;
		if (a.equals("black")) {
			answer += 0;

		} else if (a.equals("brown")) {
			answer += 1;
		} else if (a.equals("red")) {
			answer += 2;
		} else if (a.equals("orange")) {
			answer += 3;
		} else if (a.equals("yellow")) {
			answer += 4;
		} else if (a.equals("green")) {
			answer += 5;
		} else if (a.equals("blue")) {
			answer += 6;
		} else if (a.equals("violet")) {
			answer += 7;
		} else if (a.equals("grey")) {
			answer += 8;
		} else if (a.equals("white")) {
			answer += 9;
		}
	}

	private static void mul(String a) {
		if (a.equals("black")) {
			answer *= 1;

		} else if (a.equals("brown")) {
			answer *= 10;
		} else if (a.equals("red")) {
			answer *= 100;
		} else if (a.equals("orange")) {
			answer *= 1_000;
		} else if (a.equals("yellow")) {
			answer *= 10_000;
		} else if (a.equals("green")) {
			answer *= 100_000;
		} else if (a.equals("blue")) {
			answer *= 1_000_000;
		} else if (a.equals("violet")) {
			answer *= 10_000_000;
		} else if (a.equals("grey")) {
			answer *= 100_000_000;
		} else if (a.equals("white")) {
			answer *= 1_000_000_000;
		}
	}
}