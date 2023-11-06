import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int h = Integer.parseInt(br.readLine());
		h = Math.min(h, Integer.parseInt(br.readLine()));
		h = Math.min(h, Integer.parseInt(br.readLine()));

		int d = Integer.parseInt(br.readLine());
		d = Math.min(d, Integer.parseInt(br.readLine()));
		
		System.out.println(h+d-50);
	}
}