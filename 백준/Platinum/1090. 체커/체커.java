import java.io.*;
import java.util.*;

public class Main {
    static Set<Integer> X = new TreeSet<>();
    static Set<Integer> Y = new TreeSet<>();
    static int ary[][];
    static int N;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        ary = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ary[i][0] = Integer.parseInt(st.nextToken());//x
            X.add(ary[i][0]);
            ary[i][1] = Integer.parseInt(st.nextToken());//y
            Y.add(ary[i][1]);
        }
        int answer[] = new int[N];
        Arrays.fill(answer, Integer.MAX_VALUE);
        for (Integer x : X) {//50
            for (Integer y : Y) {//50
//                System.out.println("(" + x+", " + y + ")");
                //해당 점에서 다른 모든 지점까지의 거리
                int distAry[] = getDistAry(x, y);//50
//                System.out.println("각 점까지의 거리 : " + Arrays.toString(distAry));
                //각 개수씩 모아서 ANSWER최소값에 저장
                for (int i = 0; i < N; i++) {
                    //해당 점으로 N개씩 모았을 때
                    int sum = getDistSum(i+1, distAry);
                    answer[i] = Math.min(sum, answer[i]);
                }
            }
        }
        for(int i=0;i<N;i++){
            System.out.print(answer[i] + " ");
        }


    }

    public static int getDistSum(int n, int distAry[]) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += distAry[i];
        }
        return result;
    }

    public static int[] getDistAry(int x, int y) {
        //점이 주어졌을때, 나머지 모든 점까지의 거리를 구한다
        int result[] = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = Math.abs(ary[i][0] - x) + Math.abs(ary[i][1] - y);
        }
        Arrays.sort(result);
        return result;
    }


}