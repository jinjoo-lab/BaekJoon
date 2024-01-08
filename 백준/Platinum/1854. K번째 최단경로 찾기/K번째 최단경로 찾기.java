        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.util.*;
        import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;
        stz = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stz.nextToken());
        int m = Integer.parseInt(stz.nextToken());
        int k = Integer.parseInt(stz.nextToken());
        List<PriorityQueue<Integer>> heap = new ArrayList<>();
        List<List<int[]>> lists = new ArrayList<>();
        for(int i = 0; i<n+1; i++) {
            lists.add(new ArrayList<>());
            heap.add(new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer a, Integer b) {
                    return b-a;
                }
            }));
        }
        for(int i = 0; i<m; i++){
            stz = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stz.nextToken());
            int b = Integer.parseInt(stz.nextToken());
            int c = Integer.parseInt(stz.nextToken());

            lists.get(a).add(new int[]{b, c});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1]-b[1];
            }
        });

        pq.offer(new int[]{1, 0});
        heap.get(1).add(0);

        while(!pq.isEmpty()){
            int[] p = pq.poll();
            int here = p[0];
            int tohere = p[1];


            for(int i = 0; i<lists.get(here).size(); i++){
                int there = lists.get(here).get(i)[0];
                int tothere = lists.get(here).get(i)[1];

                if(heap.get(there).size() < k){
                    heap.get(there).add(tohere+tothere);
                    pq.offer(new int[]{there, tohere+tothere});
                }
                else if(heap.get(there).peek() > tohere+tothere){
                    heap.get(there).poll();
                    heap.get(there).add(tohere+tothere);
                    pq.offer(new int[]{there, tohere+tothere});
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=n; i++){
            if(heap.get(i).size() != k){
                sb.append(-1).append("\n");
            }
            else{
                sb.append(heap.get(i).peek()).append("\n");
            }
        }

        System.out.println(sb);
    }
}