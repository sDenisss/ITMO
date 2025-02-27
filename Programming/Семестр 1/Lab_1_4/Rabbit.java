package Lab_1_4;

/*
Кролик
Наследуется от пустой ячейки,
Возраст
Переопределяет конструктор,
Изначальный возраст равен 1
Переопределяет метод отвечающий ближайшим ячейкам Return,
переопределяет метод возвращающий состояние в этой ячейке в следуюшем году next
*/
public class Rabbit extends Square {
	public int age;
	public Rabbit(int x, int y) {
		super(x, y);
		age = 1;
	}

	/*
	  кролик возвращает 2
	 */
	public int Return() {
		return RABBIT;
	}	
	
	/* 
 	  Получает остров как параметр
	  ближайшие ячейка рассматриваются
	  Проверяет условия для изменений с кроликом
	 */
	public Square next(Land land) {
		land.getLand()[y][x].check(land);
		age++; // становится старше
		
		if ((age > 3) | (someone[BOA] >= someone[RABBIT])) //количество удавов >= количества кроликов или кролик слишком стар 
          return new Square(x,y); // умирает кролик - ячейка становится пустой // создаёт новый объект 
		else
          return this; // иначе без изменений
	}
}
