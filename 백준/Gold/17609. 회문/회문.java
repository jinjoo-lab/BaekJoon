import java.io.*;
import java.util.*;

public class Main {

    static int n = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= n; i++) {

            char[] arr = br.readLine().toCharArray();

            sb.append(what(arr) + "\n");
        }
        System.out.println(sb);
        br.close();
    }

    static int what(char[] arr) {
        int l = 0;
        int r = arr.length - 1;

        boolean get = true;
        while (l <= r) {
            if (arr[l] == arr[r]) {
                l = l + 1;
                r = r - 1;
            } else {
                get = false;
                break;
            }
        }

        if (get) {
            return 0;
        }

        if (sim(arr) == 1)
            return 1;

        else
            return 2;
    }

    static int sim(char[] arr) {
        boolean get = true;
        int l = 0;
        int r = arr.length - 1;
        boolean keep = true;
        while (l <= r) {
            if (arr[l] == arr[r]) {
                l = l + 1;
                r = r - 1;
            } else if (keep && l + 1 <= r && arr[l + 1] == arr[r]) {
                keep = false;
                l = l + 2;
                r = r - 1;

            } else {
                get = false;
                break;
            }
        }

        if (get)
            return 1;

        get = true;
        keep = true;

        l = 0;
        r = arr.length - 1;
        while (l <= r) {
            if (arr[l] == arr[r]) {
                l = l + 1;
                r = r - 1;
            } else if (keep && l <= r - 1 && arr[l] == arr[r - 1]) {
                keep = false;
                l = l + 1;
                r = r - 2;

            } else {
                get = false;
                break;
            }
        }

        if (get)
            return 1;

        return 2;
    }
}
