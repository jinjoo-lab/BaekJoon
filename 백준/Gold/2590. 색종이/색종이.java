import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int num[] = new int[7];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=1;i<=6;i++){
            num[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;

        for(int i=6;i>=1;i--){
            if(i == 6){
                count += num[6];
                num[6] = 0;
            }

            else if(i == 5 && num[5] >= 1){
                count += num[5];

                int oneSize = 11 * num[5];

                num[1] -= oneSize;

                if(num[1] <= 0){num[1] = 0;}

                num[5] = 0;
            }

            else if(i == 4 && num[4] >= 1){
                while(num[4] != 0){
                    count += 1;
                    num[4] -= 1;

                    if(num[2] >= 1 && num[2] <= 4){
                        int oneSize = (5 - num[2]) * 4;
                        num[2] = 0;
                        num[1] -= oneSize;
                    }else if(num[2] >= 5){
                        num[2] -= 5;
                    }else if(num[2] <= 0 && num[1] >= 1){
                        num[1] -= 20;
                    }

                    if(num[2] < 0){
                        num[2] = 0;
                    }

                    if(num[1] <= 0){
                        num[1] = 0;
                    }
                }
            }

            else if(i == 3 && num[3] >= 1){
                while(num[3] > 0){
                    if(num[3] >= 4) {
                        count += 1;
                        num[3] -= 4;
                    }else if(num[3] == 1){
                        // 5 ,7
                        count += 1;
                        num[3] = 0;

                        if(num[2] >= 5){
                            num[2] -= 5;
                            num[1] -= 7;
                        }else{
                            int oneSize = (5 - num[2]) * 4;
                            num[2] = 0;
                            num[1] -= oneSize + 7;
                        }
                    }else if(num[3] == 2){
                        // 3, 6
                        count += 1;
                        num[3] = 0;

                        if(num[2] >= 3){
                            num[2] -= 3;
                            num[1] -= 6;
                        }else{
                            int oneSize = (3 - num[2]) * 4;
                            num[2] = 0;
                            num[1] -= oneSize + 6;
                        }
                    }else if(num[3] == 3){
                        // 1 , 5
                        count += 1;
                        num[3] = 0;

                        if(num[2] >= 1){
                            num[2] -= 1;
                            num[1] -= 5;
                        }else{
                            num[2] = 0;
                            num[1] -= 9;
                        }
                    }
                }

                if(num[2] < 0){
                    num[2] = 0;
                }if(num[1] < 0){
                    num[1] = 0;
                }
            }else if(i == 2 && num[2] >= 1){
                while(num[2] > 0){
                    if(num[2] >= 9){
                        count += 1;
                        num[2] -= 9;
                    }else if(num[2] >= 1){
                        count += 1;
                        int oneSize = (9 - num[2]) * 4;
                        num[2] =0;
                        num[1] -= oneSize;
                    }
                }

                if(num[1] < 0){
                    num[1] = 0;
                }
            }else if(i == 1 && num[1] >= 1){
                while(num[1] > 0){
                    count += 1;
                    num[1] -= 36;
                }
            }
        }

        System.out.println(count);
        br.close();
    }
}

