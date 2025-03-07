package Lab_1_3;

/* Класс кость
Bone() - конструктор класса
Класс "Кость" представляет собой объект, который сохраняет информацию, связанную с определенной костью в последовательности. 
В каждой кости хранятся определенные значения, а также указатель, указывающий на следующую кость в этой последовательности. 
Этот класс содержит конструктор для создания кости, метод, который устанавливает следующую кость в последовательности,
и метод для отображения информации о кости. Важные характеристики каждой кости включают в себя уникальный идентификатор (id) и указатель (next),
который указывает на следующий элемент в последовательности
*/
public class Bone {

  private final int id; // переменная, хранящая значения написанные на кости
  /*
   * Конструктор принимает идентификатор как параметр создаваемой кости
   */
  private int next; // используется для хранения индекса следующей кости в последовательности. Когда
                    // последовательность костей формируется,
                    // каждая кость в последовательности связывается со следующей костью путем
                    // установки значения next на индекс следующей кости.

  /*
   * Bone() - Конструктор
   * принимает одну переменную val
   * служит для задания переменной в классе
   * Принимает индекс как параметр в наборе Кости следующей за данной
   * и записывает его в соответствующее поле
   */
  public Bone(int value) {
    this.id = value;
    this.next = -1;
  }

  // Возвращает идентификатор
  public int getId() {
    return id;
  }

  // Метод, возвращающий значение переменной next, указатель
  public int getNext() {
    return next;
  }

  // Метод, устанавливающий значение переменной next равным переданному параметру
  public void setNext(int next) {
    this.next = next;
  }

  /*
   * Метод, который возвращает строковое представление объекта.
   * В данном случае, он используется для вывода значений кости в формате
   * "s1-s2 ", где s1 и s2 вычисляются на основе значения id.
   */
  public void print() {
    int f = id % 7; //
    int s = (id + (id / 7)) % 7;
    int s1 = ((s < f) ? s : f);
    int s2 = ((s < f) ? f : s);

    System.out.print(s1 + "-" + s2 + " ");
  }
}
