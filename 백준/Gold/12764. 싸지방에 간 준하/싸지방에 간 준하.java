import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        arr = new int[n][2];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr[i][0] = s;
            arr[i][1] = e;
        }

        Arrays.sort(arr,(x,y) -> {
            if(x[0] == y[0]){
                return x[1] - y[1];
            }
            return x[0] - y[0];
        });


        int count = 1;
        int[] roomCount = new int[n+1];
        int[] roomEnd = new int[n+1];
        roomCount[1] = 1;
        roomEnd[1] = arr[0][1];

        for(int i=1;i<n;i++){
            boolean find = false;
            int tmpS = arr[i][0];
            int tmpE = arr[i][1];

            for(int j=1;j<=count;j++){
                if(roomEnd[j] <= tmpS){
                    find = true;
                    roomEnd[j] = tmpE;
                    roomCount[j] += 1;
                    break;
                }
            }

            if(!find){
                count += 1;
                roomEnd[count] = tmpE;
                roomCount[count] += 1;
            }

        }

        StringBuilder sb = new StringBuilder();
        sb.append(count+"\n");
        for(int i=1;i<=count;i++){
            sb.append(roomCount[i]+" ");
        }

        System.out.println(sb);
        br.close();
    }
}
class Node{
    int num;
    int t;

    Node(int num,int t){
        this.num = num;
        this.t = t;
    }
}
