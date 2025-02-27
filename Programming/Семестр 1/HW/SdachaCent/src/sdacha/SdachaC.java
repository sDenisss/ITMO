package sdacha;
import java.util.Scanner;

public class SdachaC {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Price: ");
        int price = in.nextInt();
        int amount = 100;
        int change = amount - price;
        
        int c_50 = 0;
        int c_25 = 0;
        int c_10 = 0;
        int c_5 = 0;
        int c_1 = 0;
        
        c_50 = c_50 + (change / 50);
        change = change - 50*c_50;
        c_25 = c_25 + (change / 25);
        change = change - 25*c_25;
        c_10 = c_10 + (change / 10);
        change = change - 10*c_10;
        c_5 = c_5 + (change / 5);
        change = change - 5*c_5;
        c_1 = c_1 + change;
        
        System.out.print(price > 100 ? "Error" : price == 100 ? "No change" : "");
        System.out.print(c_50 > 0 ? "50c: " + c_50 + "; " : "");
        System.out.print(c_25 > 0 ? "25c: " + c_25 + "; " : "");
        System.out.print(c_10 > 0 ? "10c: " + c_10 + "; " : "");
        System.out.print(c_5 > 0 ? "5c: " + c_5 + "; " : "");
        System.out.print(c_1 > 0 ? "1c: " + c_1 : "");
	}
}

