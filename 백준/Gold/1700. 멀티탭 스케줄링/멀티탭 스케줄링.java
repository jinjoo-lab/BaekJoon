import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int m = 0;

    static int[] board;

    static PriorityQueue<Integer>[] pq = new PriorityQueue[101];

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[m+1];

        for(int i=1;i<=m;i++){
            pq[i] = new PriorityQueue<>((x,y)-> x - y);
        }

        st = new StringTokenizer(bf.readLine(), " ");
        for(int i=1;i<=m;i++){
            int cur = Integer.parseInt(st.nextToken());

            board[i] = cur;
            pq[cur].add(i);
        }

        ArrayList<Integer> concent = new ArrayList<>();
        int count = 0;

        for(int i=1;i<=m;i++){

            if(concent.size() < n){
                if(!isIt(concent,board[i])){
                    concent.add(board[i]);
                }
            }else if(concent.size() == n){
                if(!isIt(concent,board[i])){
                    int idx = find(concent);

                    concent.remove(idx);
                    concent.add(board[i]);
                    count += 1;
                }

            }

            pq[board[i]].poll();
        }

        System.out.println(count);
        bf.close();
    }

    static int find(ArrayList<Integer> concent){
        int maxIdx = 0;


        for(int i = 0 ;i < concent.size() ; i++) {

            if(pq[concent.get(i)].isEmpty()){
                return i;
            }


            if(pq[concent.get(maxIdx)].peek() < pq[concent.get(i)].peek()){
                maxIdx = i;
            }
        }

        return maxIdx;
    }

    static boolean isIt(ArrayList<Integer> concent,int target){
        for(int next : concent){
            if(next == target){return true;}
        }

        return false;
    }


}





