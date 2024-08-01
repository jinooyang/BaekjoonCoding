import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] ary = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ary[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //박스 최개 1개는 조커 박스로 지정할 수 있다
        //조커 박스를 제외한 모든 박스는 비어있거나 같은 색의 카드만 보관해야한다
        //같은 색을 가진 모든 카드는 모두 같은 박스에 있어야한다(조커 제외)
        //이동 한번은 한 박스에서 1장 이상의 카드를 빼, 다른 박스에 넣는다. 이때 색이 모두 같을 필요는 없다.근데 도착하는 박스는 같다
        //조커로 모든 행을 움직인다
        int answer = Integer.MAX_VALUE;
        for (int joker = 0; joker < N; joker++) {
            int cnt = 0;
            int color[] = new int[M];//각 색이 박스가 정해졌는지 본다
            Arrays.fill(color, -1);
            for (int i = 0; i < N; i++) {
                //각 행을 본다
                if (i == joker) continue;
                int colorTypeCount = 0;
                int lastColorIndex = 0;
                for (int j = 0; j < M; j++) {
                    if (ary[i][j] != 0) {
                        colorTypeCount++;
                        lastColorIndex = j;
                    }
                }
                if (colorTypeCount > 1) {
                    cnt++;
                }
                if (colorTypeCount == 1) {
                    //현재 박스에 한가지 색만 나타났다
                    if (color[lastColorIndex] != -1) cnt++;
                    else {
                        color[lastColorIndex] = i;
                    }
                }
            }

            answer = Math.min(cnt, answer);

        }
        System.out.println(answer);


    }

}