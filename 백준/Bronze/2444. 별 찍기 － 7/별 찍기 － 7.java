import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        StringBuilder output = new StringBuilder(); // StringBuilder 생성

        for (int i = 0; i < n; i++) {
            int max = 2 * n - 1;
            for (int j = 0; j < max / 2 - i; j++) {
                output.append(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                output.append("*");
            }
            output.append("\n"); // 줄 바꿈 추가
        }
        for (int i = n - 2; i >= 0; i--) {
            int max = 2 * n - 1;
            for (int j = 0; j < max / 2 - i; j++) {
                output.append(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                output.append("*");
            }
            output.append("\n"); // 줄 바꿈 추가
        }

        System.out.println(output.toString()); // StringBuilder 내용 출력
    }
}