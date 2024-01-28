import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int count = n - 1;
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=count; j++) {
				System.out.print(" ");
			}
			for (int j=1; j<=i; j++) {
				System.out.print('*' + " ");
			}
			System.out.println();
			count --;
		}
	}
}
