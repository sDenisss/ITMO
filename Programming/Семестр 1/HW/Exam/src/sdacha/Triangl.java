package sdacha;

public class Triangl extends Figura {
	   protected double dim3;
	   public Triangl(double a, double b, double c) {
	       super(a,b);
	       dim3 = c;
	   }
	   public double area(){ //реализация метода для треугольника
	       double p = (dim1 + dim + dim3)/2;
	       return Math.sqrt(p * (p - dim1) * (p- dim) * (p - dim3));
	   }


	   public void print(){
	       System.out.println(dim1 + " " + dim + " " + dim3);
	   }
}
