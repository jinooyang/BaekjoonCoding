import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

       // System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(br.readLine());
            solve(num);
        }

    }

    private static void solve(long num) {
        for (int i = 2; i <= 1000000; i++) {
            if(num%i ==0){
                boolean res = checkPrime(i);
                if(res){
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }

    private static boolean checkPrime(int num) {
        boolean res = true;
        for(int i=2;i<num;i++){
            if(num%i==0)res = false;
        }
        return res;
    }

}