package sdacha;

public abstract class Figura {
	   protected double dim1, dim;
	   public Figura(double d1, double d) {
	       dim1 = d1;
	       dim = d;
	   }
	   public abstract double area(); //метод будет реализован в подклассах
	   public void print(){
	       System.out.println(dim1 + " " + dim);
	   }
}
