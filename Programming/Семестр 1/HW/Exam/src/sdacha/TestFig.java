package sdacha;

public class TestFig {
	   public static void main(String[] args) {
	       Figura f[] = {new Prymougol(1, 1), new Triangl(2,4,5), new Prymougol(3, 4)};
	       int i;
	       for (i = 0; i < f.length; i++){
	           System.out.println(f[i].area());
	       }
	       System.out.println();
	       for (i = 0; i < f.length; i++){
	           f[i].print();
	       }
	   }
}
