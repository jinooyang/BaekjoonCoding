import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());//여
        int M = Integer.parseInt(st.nextToken());//남
        int K = Integer.parseInt(st.nextToken());//인원K
        int teams = Math.min(N / 2, M);
        N -= teams * 2;
        M -= teams;
        K = Math.max(0, K - N - M);
        System.out.println(teams - (int) Math.ceil((double) K / 3));

    }

}