package sdacha;

public class Mersenne {

	public static void main(String[] args) {
		int num = 2;
        int count = 0;
        while (count < 7) {
            boolean Prime = true;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    Prime = false;
                    break;
                }
            }
            if (Prime == true) {
                int mers = (int) (Math.pow(2, num) - 1);
                boolean MersPrime = true;
                for (int i = 2; i <= Math.sqrt(mers); i++) {
                    if (mers % i == 0) {
                        MersPrime = false;
                        break;
                    }
                }
                if (MersPrime) {
                    System.out.println(mers);
                    count++;
                }
            }
            num++;
        }
		

	}

}
