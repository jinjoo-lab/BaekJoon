import java.util.*;
import java.io.*;
public class Main {
    static int n = 0;
    static int w = 0;
    static int l = 0;



    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine()," ");
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> truck = new LinkedList<>();

        st = new StringTokenizer(bf.readLine()," ");

        for(int i=1;i<=w;i++){
            q.add(0);
        }

        for(int i=1;i<=n;i++){
            truck.add(Integer.parseInt(st.nextToken()));
        }

        int count = 0;
        int weight = 0;

        while(!q.isEmpty()){

            weight -= q.poll();
            count += 1;

            if(!truck.isEmpty()){
                if(truck.peek() + weight <= l) {
                    weight += truck.peek();
                    q.add(truck.poll());
                }else{
                    q.add(0);
                }
            }
        }



        System.out.println(count);
        bf.close();
    }
}

