import java.util.*;
// 9시부터 n회 t분 간격
class Solution {
    
    static int[] time;
    static int N,T,M;
    
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int reTime = 0;
        N = n;
        T = t;
        M = m;
        
        time = new int[timetable.length];
        
        for(int i = 0, size = timetable.length ; i < size ; i++) {
            int tmpTime = change(timetable[i]);
            time[i] = tmpTime;
        }
    
        Arrays.sort(time);
        
        reTime = change("09:00") + (n - 1) * t;
        
        reTime = Math.min(reTime , go());
        
        answer = change2(reTime);
        
        return answer;
    }
    
    static int go() {
        int curT = change("09:00");
        int count = 0;
        int reTime = -1;
        int idx = 1;
        int curBus = 1;
        
        for(int i = 0 , size = time.length ; i < size ; i++) {
            int tmpT = time[i];
            
            if(curT >= tmpT) {
                count++;
                if(count == M) {
                    reTime = Math.max(reTime,tmpT - 1);
                    count = 0;
                    curT += T;
                    curBus+= 1;
                }
            }else {
                reTime = Math.max(reTime, curT);
                curT += T;
                count = 0;
                curBus++;
                i--;
            }
            
            if(curBus > N) {
                break;
            }
        }
        
        if(curBus == N && count < M) {
            reTime = Math.max(reTime, change("09:00") + (N - 1) * T);
        }
        
    
        if(reTime == -1)
            reTime = 1000000;
        
        return reTime;
    }
    
    
    static String change2(int cur) {
        int t;
        int m;
        
        t = cur / 60;
        m = cur % 60;
        
        String tt = t < 10 ? "0"+t : t+"";
        String mm = m < 10 ? "0"+m : m+"";
        
        return tt+":"+mm;
    }
    
    static int change(String cur) {
        StringTokenizer st = new StringTokenizer(cur,":");
        int time = 0;
        
        time += (60 * Integer.parseInt(st.nextToken()));
        time += Integer.parseInt(st.nextToken());
        
        return time;
    }
}