package Lab_1_4;

/*
Трава
Наследуется от пустой ячейки,
Переопределяет конструктор,
Переопределяет метод отвечающий ближайшим ячейкам Return,
переопределяет метод возвращающий состояние в этой ячейке в следуюшем году next
*/
public class Grass extends Square {
	public Grass(int x, int y) {
		super(x, y);
	}

	/*
	  трава возвращает 1
	 */
	public int Return() {
		return GRASS;
	}
	
	/* 
	  Получает остров как параметр
	  ближайшие ячейка рассматриваются
	  Проверяет условия для изменений с травой
	 */
	public Square next(Land land) {
		land.getLand()[y][x].check(land);
		if (someone[GRASS] <= someone[RABBIT])  // кол-во кроликов >= количества травы
			return new Square(x,y); // кролик съел траву - пустая ячейка
		else
		    return this; // иначе без изменений
	}
}
