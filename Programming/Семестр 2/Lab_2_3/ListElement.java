public class ListElement implements Comparable<ListElement> {
    private String name; // имя элемента
    private double x; // координата x
    private double y; // координата y
    private double res; // результат расчёта расстояния от начала координат

    // Конструктор для создания элемента с именем и координатами
    ListElement(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // Возвращает значение res (расстояние)
    public double getRes() {
        return res;
    }

    // Метод сравнения элементов по значению res
    @Override
    public int compareTo(ListElement obj) {
        double r = Math.sqrt(obj.x * obj.x + obj.y * obj.y);
        if ((Math.sqrt(x * x + y * y) - r) < 0) {
            return -1;
        }
        if ((Math.sqrt(x * x + y * y) - r) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    // Переопределённый метод toString для удобного вывода информации об элементе
    @Override
    public String toString() {
        return "name=" + name + ", x=" + x + ", y=" + y;
    }
}
