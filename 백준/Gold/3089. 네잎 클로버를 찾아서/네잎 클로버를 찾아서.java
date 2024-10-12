import java.util.*;
import java.io.*;

public class Main {

    static int n,m;
    static HashMap<Integer,TreeSet<Integer>> xSet = new HashMap<>();
    static HashMap<Integer,TreeSet<Integer>> ySet = new HashMap<>();

    static int[] move;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= n ; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(!xSet.containsKey(x)) {
                xSet.put(x,new TreeSet<>());
            }

            if(!ySet.containsKey(y)) {
                ySet.put(y,new TreeSet<>());
            }

            xSet.get(x).add(y);
            ySet.get(y).add(x);
        }

        int xPos = 0;
        int yPos = 0;

        char[] arr = br.readLine().toCharArray();
        for(int i = 0 ; i < m ; i++) {

            if(arr[i] == 'U') {
                yPos = xSet.get(xPos).higher(yPos);
            } else if(arr[i] == 'D') {
                yPos = xSet.get(xPos).lower(yPos);
            } else if(arr[i] == 'L') {
                xPos = ySet.get(yPos).lower(xPos);
            } else if(arr[i] == 'R'){
                xPos = ySet.get(yPos).higher(xPos);
            }
        }

        System.out.println(xPos+" "+yPos);


        br.close();
    }
}
