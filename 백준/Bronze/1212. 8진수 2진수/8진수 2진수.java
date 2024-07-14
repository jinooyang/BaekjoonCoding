import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String bi[] = {"000", "001", "010", "011", "100", "101", "110", "111"};

        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(bi[s.charAt(i) - '0']);
        }
        String ans = sb.toString().replaceFirst("^0+", "");
        if (ans.isEmpty()) System.out.println(0);
        else
            System.out.println(ans);

    }

}
