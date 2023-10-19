import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int m = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        Integer[] crain = new Integer[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            crain[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(crain, (x, y) -> y - x);

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        ArrayList<Integer> rock = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            rock.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(rock,(x,y) -> y - x);


        if(rock.get(0) > crain[0]){
            System.out.println(-1);
            return;
        }

        int turn = 0;

        while(!rock.isEmpty()){
            int idx = 0;

            for(int i=0; i < n ;){
                if(idx == rock.size()){
                    break;
                }

                if(rock.get(idx) <= crain[i]) {
                    i = i + 1;
                    rock.remove(idx);
                }else{
                    idx = idx + 1;
                }
            }

            turn = turn  + 1;
        }
        System.out.println(turn);
        br.close();
    }
}

