import java.lang.reflect.Array;
import java.util.Arrays;

public class List<T extends Comparable<T>> {
    protected T[] elements;
    private Positions positions;
    protected int size;
    private int capacity;
    private int space = 0;
    private int start = -1;

    public List(int capacity, Class<T> clazz) {
        this.capacity = capacity;
        this.elements = (T[]) Array.newInstance(clazz, capacity);
        this.positions = new Positions(capacity);
        this.size = 0;
    }

    public void sort() {
        Arrays.sort(elements, 0, size);
    }

    public int end(List<T> L) {
        return L.size;
    }

    public void insert(T x, int p, List<T> L) {
        if (p < 0 || p > L.size) {
            throw new MyException("Invalid position");
        }

        L.sort();

        for (int i = size; i > p; i--) {
            elements[i] = elements[i - 1];
            // positions.setNext(i, positions.getNext(i - 1));
        }
        elements[p] = x;
        if (L.size == 0) {
            positions.setNext(p, -1);
        }
        if (elements[p].compareTo(elements[0]) == -1) {
            positions.setNext(p, -1);
        }
        // positions.setNext(p, p + 1);
        if (p == L.size) {
            // positions.setNext(p, -1); // Указывает на конец списка
        }

        for (int i = 0; i < L.capacity; i++) {
            if (elements[i] == null) {
                space = i;
                break;
            }
        }

        L.size++;
        printlist(L);
    }

    public int locate(T x, List<T> L) {
        for (int i = 0; i < L.size; i++) {
            if (elements[i].compareTo(x) == 0) {
                return i;
            }
        }
        return size; // Если элемент не найден, вернуть позицию End
    }

    public T retrieve(int p, List<T> L) {
        if (p < 0 || p >= size) {
            throw new MyException("Position out of bounds");
        }
        System.out.println(elements[p]);
        return elements[p];
    }

    public void delete(int p, List<T> L) {
        if (p < 0 || p >= size) {
            throw new MyException("Position out of bounds");
        }

        for (int i = p; i < capacity - 1; i++) {
            elements[i] = elements[i + 1];
            positions.pos[i] = positions.pos[i + 1];
        }

        space = p;
        elements[capacity - 1] = null;
        L.size--;
    }

    public int next(int p, List<T> L) {
        if (p < 0 || p >= size) {
            throw new MyException("No next position");
        }
        if (p == L.size - 1) {
            return end(L);
        } else {
            return p + 1;
        }
    }

    public int previous(int p, List<T> L) {
        if (p <= 0 || p > size) {
            throw new MyException("No previous position");
        }
        return p - 1;
    }

    public void makenull(List<T> L) {
        for (int i = 0; i < L.size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public int first(List<T> L) {
        return (L.size == 0) ? end(L) : 0;
    }

    public void printlist(List<T> L) {
        for (int i = 0; i < L.capacity; i++) {
            System.out.println(i + " " + elements[i] + " | next = " + positions.pos[i]);
        }
        System.out.println("space: " + space);
        System.out.println("start: " + start);
        // for (int i = 0; i < capacity; i++) {
        // System.out.println(i + " " + positions.toString());
        // }
        System.out.println("---");
    }
}