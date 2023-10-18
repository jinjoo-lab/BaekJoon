import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int k = 0;

    static PriorityQueue<Node> max;
    static PriorityQueue<Node> min;

    static ArrayList<Boolean> d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        k = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for(int i=1;i<=k;i++){
            n = Integer.parseInt(br.readLine());
            max = new PriorityQueue<>((x,y) -> Integer.compare(y.v,x.v));
            min = new PriorityQueue<>((x,y) -> Integer.compare(x.v,y.v));
            d = new ArrayList<>();
            int idx = 0;

            for(int j=0;j<n;j++){
                st = new StringTokenizer(br.readLine(), " ");
                String what = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if(what.equals("I")){
                    max.add(new Node(idx,num));
                    min.add(new Node(idx,num));
                    idx = idx + 1;
                    d.add(false);
                }

                else if(what.equals("D")){
                    if(num == 1){
                        while(!max.isEmpty()){
                            Node tmp = max.poll();

                            if(d.get(tmp.i) == false) {
                                d.set(tmp.i, true);
                                break;
                            }
                        }
                    }

                    else{
                        while(!min.isEmpty()){
                            Node tmp = min.poll();

                            if(d.get(tmp.i) == false) {
                                d.set(tmp.i, true);
                                break;
                            }
                        }
                    }
                }
            }

            sb.append(result());
        }

        System.out.print(sb);
        br.close();
    }

    static String result(){
        int maxR = 0;
        int minR = 0;
        boolean maxF = false;
        boolean minF = false;

        while(!max.isEmpty()){
            Node maxV = max.poll();

            if(d.get(maxV.i) == false){
                maxR = maxV.v;
                maxF = true;
                break;
            }
        }

        while(!min.isEmpty()){
            Node minV = min.poll();

            if(d.get(minV.i) == false){
                minR = minV.v;
                minF = true;
                break;
            }
        }


        if(maxF && minF){
            return maxR+" "+minR+"\n";
        }

        return "EMPTY\n";
    }
}
class Node{
    int i;
    int v;

    Node(int i,int v){
        this.i = i;
        this.v = v;
    }
}
