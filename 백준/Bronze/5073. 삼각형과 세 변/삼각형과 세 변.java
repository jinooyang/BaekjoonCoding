import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while (true) {
			st = new StringTokenizer(br.readLine());
			if(!tri(st))break;
		}
	}

	private static boolean tri(StringTokenizer st) {
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		if (a == 0 && b == 0 && c == 0)
			return false;
		boolean tri = isTri(a, b, c);
		if (tri) {
			whichTri(a, b, c);
		} else {
			System.out.println("Invalid");
		}
		return true;
	}

	private static boolean isTri(int a, int b, int c) {
		int ary[] = new int[3];
		ary[0] = a;
		ary[1] = b;
		ary[2] = c;
		Arrays.sort(ary);
		if (ary[0] + ary[1] > ary[2])
			return true;
		else
			return false;
	}

	private static void whichTri(int a, int b, int c) {
		if (a == b && b == c) {
			System.out.println("Equilateral");
			return;
		}

		if (a == b || b == c || c == a) {
			System.out.println("Isosceles");
			return;
		}

		System.out.println("Scalene ");
	}
}
