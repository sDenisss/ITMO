package sdacha;
import java.util.Scanner;

public class Sdachaifelse {

    public static void main(String[] args) {
    	
    	
        Scanner in = new Scanner(System.in);

        System.out.print("Price: ");
        int price = in.nextInt();
        int amount = 100;
        int change = amount - price;

        if (price > 100) {
            System.out.print("Error!");
        } else if (price == 100) {
            System.out.print("No change!");
        } else {
        	if (change >= 50)
        	{
        		System.out.print("50$ - " + change / 50 + ", ");
        		change %= 50;
        	}
        	if (change != 0) 
        	{
        		if (change >= 25) {
        			System.out.print("25$ - " + change / 25 + ", ");
        			change %= 25;
        		}
            if (change != 0) 
            {
            	if (change >= 10) {
            		System.out.print("10$ - " + change / 10 + ", ");
            		change %= 10;
            	}
            if (change != 0) 
            {
            	if (change >= 5) {
            		System.out.print("5$ - " + change / 5 + ", ");
            		change %= 5;
            	}
            if (change != 0)
            {
            	if (change >= 1) {
            		System.out.print("1$ - " + change);
            	}
            }
            }
            }
            }
        }
    }
}