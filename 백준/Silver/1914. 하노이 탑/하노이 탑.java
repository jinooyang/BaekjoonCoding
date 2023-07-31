import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {

	static int totalnum = 0;
	static StringBuilder sb = new StringBuilder();
	static int icnt = 0;
	static int[] ary = new int[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		totalnum = Integer.parseInt(br.readLine());
		if (totalnum <= 20) {
			System.out.println((1 << totalnum) - 1);
			hanoi(totalnum, 1, 2, 3);
			bw.write(sb.toString());
		} else {
			BigInteger su = new BigInteger("2");
			su = su.pow(totalnum);
			su = su.subtract(BigInteger.ONE);
			System.out.println(su);
		}
		bw.flush();
		bw.close();
	}

	private static void hanoi(int num, int start, int mid, int end) {
		if (num == 1) {
			System.out.println(start + " " + end);
			icnt++;
			return;
		}
		hanoi(num - 1, start, end, mid);
		System.out.println(start + " " + end);
		icnt++;
		hanoi(num - 1, mid, start, end);
	}

}