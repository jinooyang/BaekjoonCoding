import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
       // System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        //각 수를 보면서 내 왼쪽 오른쪽에 각각 기울기 재본다
        int answer = 0;
        for (int i = 0; i < N; i++) {
//            if(i==11){
//                System.out.println(list.get(i) + "에서");
//            }
            int now = list.get(i);
            int cnt = 0;
            for (int j = 0; j < N; j++) {
                //각 빌딩이 보이는지 확인한다
                if (i == j) continue;
//                if(i==11)
//                    System.out.println(j + "번째 빌딩이 보이는지 확인 : ");
                int dx = i - j;
                int dy = list.get(i) - list.get(j);
                double inc = (double) dy / dx;
                double b = list.get(i) - inc * i;
                boolean canSee = true;
                for (int k = j; j < i ? k < i : k > i; k += j < i ? 1 : -1) {
                    if(k==j)continue;
                    double y = inc * k + b;
//                    if(i==11)
//                        System.out.println("\t최대가능높이 : " + y + "\t빌딩의 높이 : " + list.get(k));
                    if (list.get(k) >= y) {
                        canSee = false;
                        break;
                    }
                }
                if (canSee) cnt++;
//                if(i==11){
//                    System.out.println(canSee);
//                }


            }
            answer = Math.max(answer,cnt);

        }
        System.out.println(answer);


    }

}