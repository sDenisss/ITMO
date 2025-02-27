package Lab_1_4;

/*
Остров включает в себя текущее состояние в форме двумерного массива "curLand". 
Моделированное состояние после прошедшего года хранится в массиве "nextLand". 
Класс имеет конструктор и связанный с ним метод "itSquards"
Методы "getLand" для получения текущего острова, 
"print" для отображения текущего состояния.
*/
public class Land {
	public Square[][] curLand; // Текущий остров
	public Square[][] nextLand; // Следующий остров

	/*
	 * Установка удавов, кроликов и траву
	 * на конкретные ячейки
	 */
	public void kitSquards() {

		// Трава
		curLand[19][19] = new Grass(19, 19);
		curLand[19][18] = new Grass(19, 18);

		// Кролики
		curLand[19][2] = new Rabbit(19, 2);
		curLand[20][1] = new Rabbit(19, 3);

		// Удавы
		curLand[3][17] = new Boa(3, 17);
		curLand[4][16] = new Boa(4, 16);
	}

	/*
	 * конструктор для создания острова 20 на 20
	 * создаёт два массива размером 22 на 22,
	 * в ячейки текущего массива сначала помещаются "Пустые ячейки"
	 * и установка удавов, кроликов и травы
	 */
	public Land() {
		int i, j;
		curLand = new Square[22][22];
		nextLand = new Square[22][22];
		for (i = 0; i < 22; i++)
			for (j = 0; j < 22; j++)
				curLand[i][j] = nextLand[i][j] = new Square(i, j);
		this.kitSquards();
	}

	/*
	 * Возвращает текущий остров
	 */
	public Square[][] getLand() {
		return curLand;
	}

	/*
	 * Каждая ячейка проверяется как должна измениться
	 * потом обновлет текущий остров:
	 * Текущий остров начинает ссылаться на следующий
	 * А следующий на текущий
	 * Обмен производится через временную переменную a,
	 * если мы просто обновим текущее состояние, оставив "next" без изменений,
	 * то "next" и "current" - будут указывать на один и тот же массив.
	 * 
	 * так как мы не используем значений next
	 * ни для чего кроме заполнения массива current
	 * в завершении этого метода,
	 * допустимо чтобы ячейки массива
	 * с одинаковыми индексами ссылались
	 * на одни и те же объекты
	 */
	public void newTransform() {
		int i, j;
		int N = curLand.length - 1; // Ячейки за границой не вызываются
		for (i = 1; i < N; i++) {
			for (j = 1; j < N; j++) {
				nextLand[i][j] = curLand[i][j].next(this);
			}
		}
		Square[][] a = curLand;
		curLand = nextLand;
		nextLand = a;
	}

	/*
	 * Отображает текущий остров
	 */
	public void print() {
		int i, j;
		int N = curLand.length - 1;
		for (i = 1; i < N; i++) {
			for (j = 1; j < N; j++)
				curLand[i][j].print();
			System.out.println();
		}
		System.out.println();
	}
}