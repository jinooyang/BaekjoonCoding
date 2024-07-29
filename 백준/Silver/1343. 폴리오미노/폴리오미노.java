import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        boolean x;
        int cnt;

        public Node(boolean x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        List<Node> list = new ArrayList<>();
        if (s.charAt(0) == 'X') {
            list.add(new Node(true, 1));
        } else {
            list.add(new Node(false, 1));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            char now = s.charAt(i);
            if (now == 'X') { //X일때
                if (list.get(list.size() - 1).x)//이전이 X이면
                    list.get(list.size() - 1).cnt++;
                else//이전이 X가 아니라면
                    list.add(new Node(true, 1));
            } else {//.일때
                if (!list.get(list.size() - 1).x)//이전이 .이면
                    list.get(list.size() - 1).cnt++;
                else//이전이 X면
                    list.add(new Node(false, 1));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).x) {
                int cnt = list.get(i).cnt;
                if (cnt % 2 == 1) {
                    System.out.println(-1);
                    return;
                }
                int four = cnt / 4;
                for (int j = 0; j < four; j++) {
                    sb.append("AAAA");
                }
                cnt = cnt - 4 * four;
                int two = cnt / 2;
                for (int j = 0; j < two; j++) {
                    sb.append("BB");
                }
            } else {
                for (int j = 0; j < list.get(i).cnt; j++) {
                    sb.append(".");
                }
            }
        }
//        System.out.println(list.toString());
        System.out.println(sb);
    }

}