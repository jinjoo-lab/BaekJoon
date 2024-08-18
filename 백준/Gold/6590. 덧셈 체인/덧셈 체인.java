import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] table = new ArrayList[101];
    static int[] check = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Queue<ArrayList<Integer>> dq = new ArrayDeque<>();
        ArrayList<Integer> d = new ArrayList<>();
        d.add(1);
        d.add(2);

        check[1] = 1;
        check[2] = 2;

        table[1] = new ArrayList<>();
        table[1].add(1);

        table[2] = new ArrayList<>();
        table[2].add(1);
        table[2].add(2);

        dq.add(d);

        while (!dq.isEmpty()) {
            List<Integer> temp = new ArrayList<>(dq.poll());
            int s = temp.size();

            for (int i = s - 1; i >= 0; i--) {
                for (int j = s - 1; j >= i; j--) {
                    int nexts = temp.get(i) + temp.get(j);

                    if (nexts > 100) continue;
                    if (nexts <= temp.get(s - 1)) break;

                    if (check[nexts] == 0) {
                        table[nexts] = new ArrayList<>(temp);
                        check[nexts] = s + 1;
                        table[nexts].add(nexts);
                    } else if (s >= check[nexts]) {
                        continue;
                    }

                    temp.add(nexts);
                    dq.add(new ArrayList<>(temp));
                    temp.remove(temp.size() - 1);
                }
            }
        }

        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) {
                break;
            }

            for (int t : table[n]) {
                sb.append(t + " ");
            }sb.append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }
}
