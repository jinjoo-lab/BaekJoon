import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
     * 시간 : 856 ms
     *
     * 공간 : 14196 KB
     * */
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int num = 0;
        int[] prefix = {2, 3, 5, 7};
        for (int i : prefix) {
            num = i;
            go(1, n, num);
        }
    }

    private static void go(int depth, int n, int num) {
        int[] arr = {1, 3, 5, 7, 9};
        if (!isPrime(num)) {
            return;
        }
        if (depth == n) {
            System.out.println(num);
        }
        for (int i = 0; i < 5; i++) {
            int next = num*10 + arr[i];
            go(depth + 1, n, next);
        }

    }

    // 에라토스테네스의 체
    private static boolean isPrime(int x) {
        int end = (int)Math.sqrt(x);
        for (int i = 2; i <= end; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
