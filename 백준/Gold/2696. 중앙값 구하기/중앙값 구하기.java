import java.util.*;
import java.io.*;

public class Main {

    static int t,n;

    static PriorityQueue<Integer> minHeap , maxHeap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int a = 1 ; a <= t ; a++){
            st = new StringTokenizer(br.readLine()," ");
            n = Integer.parseInt(st.nextToken());

            minHeap = new PriorityQueue<>(
                    (x,y) -> x - y
            );

            maxHeap = new PriorityQueue<>(
                    (x,y) -> y - x
            );

            int count = 0;
            StringBuilder tmpSb = new StringBuilder();
            st = new StringTokenizer(br.readLine()," ");

            int inputCount = 0;
            int resultNum = 0;
            for(int i = 1 ; i <= n ; i++){

                if(inputCount >= 10){
                    inputCount = 0;
                    st = new StringTokenizer(br.readLine()," ");
                }

                if(i % 2 == 1){
                    count++;
                }

                int curNum = Integer.parseInt(st.nextToken());
                inputCount++;

                int minSize = minHeap.size();
                int maxSize = maxHeap.size();

                if(minSize == maxSize){
                    maxHeap.add(curNum);
                }else{
                    minHeap.add(curNum);
                }

                if(minSize != 0 && maxSize != 0) {
                    if (minHeap.peek() < maxHeap.peek()) {
                        int minHeapPoll = minHeap.poll();
                        int maxHeapPoll = maxHeap.poll();

                        maxHeap.add(minHeapPoll);
                        minHeap.add(maxHeapPoll);
                    }
                }

                if(i % 2== 1) {

                    if(resultNum >= 10){
                        tmpSb.append("\n");
                        resultNum = 0;
                    }

                    tmpSb.append(maxHeap.peek() + " ");
                    resultNum++;
                }
            }
            tmpSb.append("\n");

            sb.append(count+"\n");
            sb.append(tmpSb.toString());
        }
        System.out.println(sb.toString());
        
        
        
        
        
        
        
        
        
        br.close();
    }
}
