import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        //안되는 경우 : 
        //  길이가 짝수 일때 : 홀수가 존재하면 안됨
        //  길이가 홀수일때 : 홀수는 한개만 존재해야함
        int len = s.length();
        int alphaCnt[] = new int[26];
        for (int i = 0; i < len; i++) {
            alphaCnt[s.charAt(i) - 'A']++;
        }
        int oddCnt = 0;
        for (int i = 0; i < 26; i++) {
            if (alphaCnt[i] % 2 == 1) oddCnt++;
        }
        if (len % 2 == 0 && oddCnt > 0) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }
        if (len % 2 == 1 && oddCnt > 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }
        Queue<Character> que = new ArrayDeque<>();
        Stack<Character> stk = new Stack<>();
        char mid = '0';

        for (int i = 0; i < 26; i++) {
            char now = (char) ('A' + i);
            int cnt = alphaCnt[i];

            while (cnt > 1) {
                que.add(now);
                stk.add(now);
                cnt -= 2;
            }
            if (cnt == 1) {
                mid = now;
            }

        }

        StringBuilder sb = new StringBuilder();
        while (!que.isEmpty()) sb.append(que.poll());
        if (mid != '0') sb.append(mid);
        while (!stk.isEmpty()) sb.append(stk.pop());
        System.out.println(sb);

    }

}