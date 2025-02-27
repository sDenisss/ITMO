package Lab_1_4;

import java.util.Scanner;

/*
Остров размером 20x20 квадратов заселен разными взаимодействующими формами жизни.
Удавы поедают кроликов, а сами кролики питаются травой.
Имеется по несколько представителей каждого вида
(количество и положение представителей каждого вида задается в конструкторе острова).
Большинство квадратов имеет восемь соседних (кроме квадратов на границах острова),
как показано на рисунке:
X	X	X
X	Y	X
X	X	X
Здесь квадрат Y граничит с восемью соседними квадратами X,
а вся структура представляет собой уменьшенную модель шахматной доски.
Предусмотрены следующие правила определения того,
кто продолжит существование по прошествии единицы времени
в зависимости от состава соседних квадратов:
•	квадрат с удавом становится пустым,
		если количество удавов в соседних квадратах больше 5 (погибает от перенаселения)
		или его возраст становится больше 6 единиц времени (умирает от старости),
		иначе квадрат не изменяется;
•	квадрат с кроликом становится пустым,
		если количество удавов в соседних квадратах больше или равно количеству кроликов (кролика съедают)
		или его возраст становится больше 3 единиц времени (умирает от старости),
		иначе квадрат не изменяется;
•	квадрат с травой становится пустым,
		если вокруг квадратов с травой меньше, чем квадратов с кроликами (трава съедается кроликами),
		иначе квадрат не изменяется;
•	пустой квадрат заполняется удавом, если вокруг не менее 2 удавов,
		заполняется кроликом, если вокруг не менее 2 кроликов,
		заполняется травой, если она есть в каком-либо соседнем квадрате,
		иначе квадрат не изменяется.
Промоделируйте N шагов.
В ходе моделирования необходимо выдавать на экран текущее состояние острова в каждую единицу времени,
различая пустые квадраты, квадраты, заполненные травой, удавами и кроликами.
*/
public class Main {
	
	 /*
	Создает остров размером 20 на 20, выводит его на экран и запускает симуляцию. 
	После этого в цикле обновляет состояние острова каждый год и отображает его.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter N:");
		int N = scanner.nextInt();
		
		System.out.println("Years: " + N);
		System.out.println("");
		
		Land land = new Land(); 
		land.print();
		int i; 	 // i - Номер года
		
		for(i = 1; i <= N; i++) {
          System.out.println("Past Years: " + i);
          
        land.newTransform();
        land.print();
      }
	}
}
