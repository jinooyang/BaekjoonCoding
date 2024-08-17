import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

//        ax + by = w
//        x + y = n
        int sheep = -1;
        int goat = -1;
        boolean multipleAnswer = false;
        for (int x = 1; x <= 999; x++) {
            for (int y = 1; y <= 999; y++) {
                if (x + y != n) continue;
                if ((a * x + b * y) == w) {
                    if (sheep == -1) {
                        sheep = x;
                        goat = y;

                    } else {
                        multipleAnswer = true;
                    }
                }


            }

        }
        if (multipleAnswer || sheep == -1) System.out.println(-1);
        else
            System.out.println(sheep + " " + goat);
    }

}