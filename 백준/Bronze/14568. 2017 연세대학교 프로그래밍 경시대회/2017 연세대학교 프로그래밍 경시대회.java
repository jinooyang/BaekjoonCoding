import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int answer = 0;
        for (int taek = 1; taek <= N; taek++) {
            for (int young = 1; young <= N; young++) {
                for (int nam = 1; nam <= N; nam++) {
                    if (taek + young + nam == N && nam >= young + 2 && taek % 2 == 0) answer++;
                }
            }
        }
        System.out.println(answer);

    }

}