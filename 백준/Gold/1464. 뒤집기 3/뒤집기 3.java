import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] arr = br.readLine().toCharArray();

        Deque<Character> q = new LinkedList<>();
        q.add(arr[0]);

        for(int i=1;i<arr.length;i++){
            if(arr[i] > q.peekLast())
                q.addFirst(arr[i]);

            else{
                q.addLast(arr[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            sb.append(q.pollLast());
        }

        System.out.println(sb);
        br.close();
    }

}