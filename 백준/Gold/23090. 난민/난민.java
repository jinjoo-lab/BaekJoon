import java.util.*;
import java.io.*;

public class Main {

    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            (x,y) -> y - x
    );
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (x,y) -> x - y
    );
    static int n;

    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());

        long result = 0;
        long xCount = 0;

        long maxHeapSum = 0l;
        long minHeapSum = 0l;


        arr = new int[n+1];

        for(int i = 1 ; i <= n ; i++){
            st = new StringTokenizer(br.readLine()," ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            xCount += Math.abs(x);
            arr[i] = y;

            if(maxHeap.size() == minHeap.size()){
                maxHeap.add(y);
                maxHeapSum += y;
            }else{
                minHeap.add(y);
                minHeapSum += y;
            }

            if(maxHeap.size() != 0 && minHeap.size() != 0){
                if(maxHeap.peek() > minHeap.peek()){
                    int maxPeek = maxHeap.poll();
                    int minPeek = minHeap.poll();

                    maxHeapSum -= maxPeek;
                    minHeapSum -= minPeek;

                    maxHeap.add(minPeek);
                    minHeap.add(maxPeek);

                    maxHeapSum += minPeek;
                    minHeapSum += maxPeek;
                }
            }

            int tmpResult = maxHeap.peek();

           sb.append(tmpResult+" ");

           if(i % 2 == 1){
               result = xCount + minHeapSum - maxHeapSum + tmpResult;
           }else{
               result = xCount + minHeapSum - maxHeapSum;
           }



           sb.append(result+"\n");
        }

        System.out.print(sb.toString());
        br.close();
    }

}
