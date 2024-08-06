import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int ary[] = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            ary[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(ary);
        System.out.println(N % 2 == 0 ? ary[N / 2- 1]  : ary[N / 2]);
    }

}