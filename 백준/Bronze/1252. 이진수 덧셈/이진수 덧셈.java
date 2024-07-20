import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BigInteger binaryNumber1 = new BigInteger(st.nextToken(), 2);
        BigInteger binaryNumber2 = new BigInteger(st.nextToken(), 2);
        System.out.println(binaryNumber1.add(binaryNumber2).toString(2));
    }
}