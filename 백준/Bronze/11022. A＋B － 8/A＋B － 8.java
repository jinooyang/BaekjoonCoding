import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 테스트 케이스의 수

        for (int i = 1; i <= n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            int sum = a + b;

            System.out.println("Case #" + i + ": " + a + " + " + b + " = " + sum);
        }

        scanner.close();
    }
}