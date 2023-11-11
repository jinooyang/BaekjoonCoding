import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int ary[] = new int[26];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			ary[c-'a']++;
		}
		for(int i=0;i<26;i++) {
			System.out.print(ary[i] + " ");
		}
	}
}