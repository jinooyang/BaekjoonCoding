import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        char ary[][] = new char[N][8];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            Arrays.fill(ary[i], '0');
            for (int j = 0; j < s.length(); j++) {
                ary[i][8 - s.length() + j] = s.charAt(j);
            }
        }
//        for(int i=0;i<N;i++)
//                System.out.println(Arrays.toString(ary[i]));
        //각 알파벳의 영향력을 고려한다
        int alpha[][] = new int[26][2];
        for (int i = 0; i < 26; i++) {
            alpha[i][1] = i;
        }
        for (int i = 0; i < N; i++) {
            char currentString[] = ary[i];
            for (int j = 0; j < 8; j++) {
                if (currentString[j] == '0') continue;
                alpha[currentString[j] - 'A'][0] += (int) Math.pow(10, 7 - j);
            }
        }
//        System.out.println(Arrays.toString(alpha));
        Arrays.sort(alpha, (o1, o2) -> {
            if (o1[0] == o2[0])
                return Integer.compare(o1[1], o2[1]);
            return Integer.compare(o2[0], o1[0]);
        });
//        for (int i = 0; i < 26; i++) {
//            System.out.println(Arrays.toString(alpha[i]));
//        }
        //가중치를 구했으니...정렬도 했으니....값을 준다
        int num = 9;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            if (alpha[i][0] == 0) break;
            map.put((char) ('A' + alpha[i][1]), num--);
        }
//        System.out.println(map.toString());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 8; j++) {
                if(ary[i][j] == '0')continue;
                ary[i][j] = (char)(48 + map.get(ary[i][j]));
            }
        }

        int answer =0;
        for(int i=0;i<N;i++){
            String s = new String(ary[i]);
            answer += Integer.parseInt(s);
        }
        System.out.println(answer);

    }

}