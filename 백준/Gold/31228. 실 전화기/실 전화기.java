import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //최초 선분을 긋는다
        //그 사이에 꼭지점이 몇개(Y)인지에 따라 그 선문에는 꼭기점(Y) * 2 개수만큼의 교차점이 생긴다
        //선분 개수 == 꼭지점 개수(X)
        //선분 개수만큼 곱하면 2번씩 중복되기 때문에 나누기 2(두 선분이 교차하므로 중복발생)
        //(Y*2 *x)/2
        //결론 : X*Y -> 꼭지점 개수 * (한 선분 사이의 꼭지점 개수)


        //System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        long tempK = N-K;
        K = Math.min(tempK , K);
        long gcd = getGCD(N, K);
        //공배수에 도달했을 때 원점으로 돌아옴
        long lcm = (N * K) / gcd;
        //최소 공배수에 도달할때까지 -> K만큼 증가하면서 가면 공배수까지 도달하는것을 계산
        long X = lcm / K; // -> 꼭지점의 개수

        //하나의 선분 사이에 존재하는 꼭지점의 개수는?
        long Y = (lcm / N) - 1;
//        System.out.println("최소공약수 : " + gcd);
//        System.out.println("최소공배수 : " + lcm);
//        System.out.println("X 꼭지점 개수 : " + X);
//        System.out.println("선분사이 꼭지점 개수 : " + Y);
//        System.out.print("정답 : ");
        System.out.println(X*Y);

    }

    public static long getGCD(long num1, long num2) {
        if (num1 % num2 == 0) return num2;
        return getGCD(num2, num1 % num2);
    }

}