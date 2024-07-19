import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ary[] = new int[10];
        while (N > 0) {
            ary[N % 10]++;
            N /= 10;
        }
        int maxCnt = 0;
        for (int i = 0; i < 10; i++) {
            if (i == 6 || i == 9) continue;
            maxCnt = Math.max(ary[i], maxCnt);
        }
        int sixNine = ary[6] + ary[9];
        System.out.println(Math.max(sixNine % 2 == 1 ? sixNine / 2 + 1 : sixNine / 2, maxCnt));


    }
}