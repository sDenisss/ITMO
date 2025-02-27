package Lab_1_4;

/*
  Пустая ячейка, хранит своё местоположение x,y
  someone и константы элементов
  константы соответствующие индексам массива в зависимости от заполнения
  имеет конструктор,
  метод для отображения print,
  метод отвечающий соседям Return,
  метод проверяет ближайшие клетки check,
  метод возвращающий эту ячейку в следуюшем году next
 */
public class Square {
	/*
	 * x - столбецы, y - строки
	 * массив хранящий результаты последнего опроса
	 * в 0 - пустые, 1 - трава, в 2 - кролики, в 3 - удавы
	 */
	public int x, y;

	public static int BOA = 3;
	public static int RABBIT = 2;
	public static int GRASS = 1;
	public static int EMPTYSQUARE = 0;

	public static int[] someone = new int[4];
	public static String[] item = new String[4];
	static {
		item[BOA] = " Y ";
		item[RABBIT] = " K ";
		item[GRASS] = " T ";
		item[EMPTYSQUARE] = " + ";
	}

	/*
	 * конструктор принимает координаты и сохраняет их
	 */
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*
	 * Статический метод для зануления статического массива
	 */
	public void init_someone() {
		int i;
		for (i = 0; i < someone.length; i++)
			someone[i] = 0;
	}

	/*
	 * отображает символ 0 соответсвующий пустой ячейке
	 */
	public void print() {
		System.out.print(item[Return()]);
	}

	/*
	 * пустая ячейка отвечает 0
	 */
	public int Return() {
		return EMPTYSQUARE;
	}

	/*
	 * Принимает остров и получает из него его текущее состояние
	 * проверяет ближайшие клетки и себя, суммируя ответы в массив
	 * 0 - ячейка, количество пустых
	 * 1 - количество травы
	 * 2 - количество кроликов
	 * 3 - количество удавов
	 * Цикл обходит значения 3 на 3 ,
	 * затем своё значение убирается
	 * возвращает полученный массив
	 */
	public void check(Land land) {
		Square[][] current_land = land.getLand();
		int i, j;
		init_someone();
		int end_row = y + 1;
		int end_col = x + 1;
		for (i = y - 1; i <= end_row; i++) {
			for (j = x - 1; j <= end_col; j++) {
				someone[current_land[i][j].Return()]++;
			}
		}
		someone[current_land[x][y].Return()]--;
	}

	/*
	 * Принимает остров
	 * ближайшие клетки проверяются, Проверяет условия для изменения пустой ячейки в
	 * соответствии с заданием
	 * Если какое-то из условий выполнится, вернётся соответствующий объект
	 */
	public Square next(Land land) {
		land.getLand()[y][x].check(land);
		if (someone[BOA] >= 2) // удавов >= 2, рождается новый
			return new Boa(x, y);
		else if (someone[RABBIT] >= 2) // кроликов >= 2, рождается новый
			return new Rabbit(x, y);
		else if (someone[GRASS] >= 1) // есть трава, вырастает новая
			return new Grass(x, y);
		else
			return this; // ничего не изменяется
	}
}
