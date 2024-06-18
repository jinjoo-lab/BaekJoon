import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        
        int n = Integer.parseInt(reader.readLine());
        List<Integer> p = new ArrayList<>();
        List<Integer> m = new ArrayList<>();
        
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(tokenizer.nextToken());
            if (t > 0) {
                p.add(t);
            } else {
                m.add(t);
            }
        }
        
        
        p.sort(Collections.reverseOrder());
        m.sort(Comparator.naturalOrder());

        Deque<Integer> pp = new ArrayDeque<>();
        Deque<Integer> mm = new ArrayDeque<>();

        for (int i = 0; i < p.size(); i++) {
            if (i % 2 == 0) {
                pp.addFirst(p.get(i));
            } else {
                pp.addLast(p.get(i));
            }
        }

        for (int i = 0; i < m.size(); i++) {
            if (i % 2 == 0) {
                mm.addFirst(m.get(i));
            } else {
                mm.addLast(m.get(i));
            }
        }

        if (!pp.isEmpty() && pp.peekFirst() > pp.peekLast()) {
            reverseDeque(pp);
        }
        if (!mm.isEmpty() && mm.peekFirst() > mm.peekLast()) {
            reverseDeque(mm);
        }

        for (int num : mm) {
            output.append(num).append(" ");
        }
        for (int num : pp) {
            output.append(num).append(" ");
        }

        System.out.print(output.toString().trim());
    }

    private static void reverseDeque(Deque<Integer> deque) {
        List<Integer> temp = new ArrayList<>(deque);
        Collections.reverse(temp);
        deque.clear();
        deque.addAll(temp);
    }
}
