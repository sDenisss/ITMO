package sdacha;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Price: ");
        int price = in.nextInt();
        int amount = 100;
        int change = amount - price;
        
        System.out.print(price > 100 ? "Error" : price == 100 ? "No change" : "");
        System.out.print(change >= 50 ? "50$ : " + change/50: "");
        change %= 50;
        System.out.print(change >= 25 ? " 25$ : " + change/25: "");
        change %= 25;
        System.out.print(change >= 10 ? " 10$ : " + change/10: "");
        change %= 10;
        System.out.print(change >= 5 ? " 5$ : " + change/5: "");
        change %= 5;
        System.out.print(change >= 1 ? " 1$ : " + change/1: "");
        change %= 1;
	}
}

