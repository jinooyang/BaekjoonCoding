import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());//방의 정원
        int B = Integer.parseInt(st.nextToken());//방의 정원
        int C = Integer.parseInt(st.nextToken());//방의 정원
        int N = Integer.parseInt(st.nextToken());//학생수
        int answer = 0;
        for (int a = 0; a <= N; a++) {//A방의 개수
            for (int b = 0; b <= N; b++) {//B방의 개수
                for (int c = 0; c <= N; c++) {//C방의 개수
                    if(a*A + b*B + c*C == N){
                        answer = 1;
                    }
                }
            }
        }
        System.out.println(answer);

    }

}