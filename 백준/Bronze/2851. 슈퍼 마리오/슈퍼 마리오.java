import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ary[] = new int[10];
        for (int i = 0; i < 10; i++) {
            ary[i] = Integer.parseInt(br.readLine());
        }
        int answer = 0;
        int i = 0;
        while (i < 10 && answer + ary[i] <= 100) {
            answer += ary[i];
            i++;
        }
        int next = answer;
        if (i < 10) next += ary[i];
        if (100 - answer >= next - 100) answer = next;
        System.out.println(answer);

    }

}