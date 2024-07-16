import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
      //  System.setIn(new FileInputStream("test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int len = s.length();
        if (len % 3 == 1) {
            s = "00" + s;
            len += 2;
        } else if (len % 3 == 2) {
            s = "0" + s;
            len += 1;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len / 3; i ++) {
            String sub = s.substring(i*3, i*3 + 3);
//            System.out.println(sub);
            int ten = Integer.parseInt(sub, 2);
            sb.append(Integer.toOctalString(ten));

        }
        System.out.println(sb);


    }
}