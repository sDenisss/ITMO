public class ListElement implements Comparable<ListElement> {
    String name;
    double x;
    double y;
    double res;

    ListElement(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.res = Math.round(Math.sqrt(x * x + y * y) * 100.0) / 100.0;
    }

    public double getRes() {
        return res;
    }

    @Override
    public int compareTo(ListElement obj) {
        if (res < obj.getRes()) {
            return -1;
        }
        if (res == obj.getRes()) {
            return 0;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "name=" + name + ", x=" + x + ", y=" + y + ", res=" + res;
    }
}