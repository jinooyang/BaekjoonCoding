import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int vip[] = new int[5];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            vip[i + 1] = Integer.parseInt(st.nextToken());
        }
        int max[] = new int[5];
        for (int i = 0; i < 5; i++) {
            if (i + 1 < 5)
                max[i] = vip[i + 1] - 1;
            else max[i] = vip[4];
        }
//        System.out.println(Arrays.toString(max));
        String s = br.readLine();
        Map<Character, Integer> map = new HashMap<>();
        map.put('B', max[0]);
        map.put('S', max[1]);
        map.put('G', max[2]);
        map.put('P', max[3]);
        map.put('D', max[4]);
        int answer = 0;
        int before = 0;
        for (int i = 0; i < N; i++) {
            char tier = s.charAt(i);

            int spend = map.get(tier) - before;
            if(tier == 'D')spend = vip[4];
            answer += spend;
//            System.out.println( (i+1) + "월에 사용 예상 금액 : " + spend + " 2monthsum : " +(spend + before)+ " tier : " + tier);
            before = spend;
        }
        System.out.println(answer);

    }

}