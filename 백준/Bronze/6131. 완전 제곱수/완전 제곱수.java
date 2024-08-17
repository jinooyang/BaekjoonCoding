import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int answer =0;
        for (int B = 1; B <= 500; B++) {
            for (int A = B; A <= 500; A++) {
                if(A*A ==B*B+N)answer++;
            }
        }
        System.out.println(answer);


    }

}