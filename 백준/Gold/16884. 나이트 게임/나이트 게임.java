import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String K = "koosaga";
        String C = "cubelover";
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(N%2==0?C:K).append("\n");
        }
        System.out.print(sb);


    }
}