public class List<T extends Comparable<T>> {
    protected T[] elements; // массив элементов
    protected Positions[] pos; // массив объектов Positions
    protected int size; // текущий размер списка
    private int capacity; // максимальная вместимость списка
    protected int space; // индекс первого свободного места
    protected int start = -1; // индекс первого элемента в списке

    // Конструктор для инициализации списка с заданной ёмкостью и массивом элементов
    public List(int capacity, T[] elements) {
        this.capacity = capacity;
        this.elements = (T[]) elements; // приведение типов
        this.pos = new Positions[capacity]; // создаём массив объектов Positions
        for (int i = 0; i < pos.length; i++) {
            this.pos[i] = new Positions(); // создаём новый объект Positions для каждого элемента
            this.pos[i].setP(i + 1); // каждой позиции присваиваем индекс следующего элемента
        }
        this.pos[capacity - 1].setP(-1); // последнему элементу присваиваем -1, что означает конец списка
        this.size = 0; // начальный размер списка
    }

    // Возвращает позицию конца списка
    public int end(List<T> L) {
        return space; // возвращает текущее свободное место
    }

    // Вставка элемента x на позицию p в список L
    public void insert(T x, int p, List<T> L) {
        if (p < 0 || p > L.size) {
            throw new MyException("Неверная позиция");
        }

        if (p == first(L)) {
            if (size != 0) {
                elements[space] = elements[p]; // перемещаем текущий элемент в свободное место
                elements[p] = x; // вставляем новый элемент

                if (elements[p].compareTo(elements[space]) > 0) {
                    pos[start].setP(-1); // если новый элемент больше, обновляем следующий элемент
                    pos[space].setP(0);
                }
                if (elements[start].compareTo(elements[space]) < 0) {
                    pos[start].setP(space); // обновляем индекс следующего элемента
                }
                if (elements[start].compareTo(elements[space]) > 0) {
                    start = space; // если новый элемент меньше, обновляем start
                }
            } else if (size == 0) {
                start = space; // если это первый элемент, он становится самым маленьким
                elements[p] = x; // вставляем новый элемент
                pos[p].setP(-1); // конец списка
            }
        } else if (p == end(L)) {
            elements[p] = x; // вставляем элемент в конец
            if (elements[start].compareTo(x) > 0) {
                start = p; // обновляем start, если новый элемент меньше текущего
            }
            if (previous(p, L) != -1 && elements[p].compareTo(elements[previous(p, L)]) > 0) {
                pos[p].setP(-1); // последний элемент указывает на -1
                pos[previous(p, L)].setP(p); // обновляем предыдущий элемент
            }
        }

        // Используем locate для поиска места для нового элемента
        space = locate(null, L); // находим следующее свободное место

        L.size++; // увеличиваем размер списка
        printlist(L); // выводим список
    }

    // Поиск позиции элемента x в списке L
    public int locate(T x, List<T> L) {
        if (x == null) {
            // Если элемент не передан, находим первую свободную позицию
            for (int i = 0; i < L.capacity; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            // Если элемент передан, ищем его позицию
            for (int i = 0; i < L.size; i++) {
                if (elements[i].compareTo(x) == 0) {
                    return i;
                }
            }
        }
        return end(L); // если элемент не найден, возвращаем конец списка
    }

    // Получение элемента на позиции p в списке L
    public T retrieve(int p, List<T> L) {
        if (p < 0 || p >= size) {
            throw new MyException("Позиция вне допустимых границ");
        }
        return elements[p];
    }

    // Удаление элемента с позиции p в списке L
    public void delete(int p, List<T> L) {
        if (p < 0 || p >= size) {
            throw new MyException("Позиция вне допустимых границ");
        }

        elements[p] = null; // удаляем элемент

        // Обновляем позиции элементов после удаления
        for (int i = 0; i < p; i++) {
            int a = pos[i].getP();
            pos[i].setP(a + 1);
        }

        space = locate(null, L); // находим новую свободную позицию
        L.size--; // уменьшаем размер списка
    }

    // Возвращает позицию следующего элемента
    public int next(int p, List<T> L) {
        if (p < 0 || p >= size) {
            throw new MyException("Нет следующей позиции");
        }
        return (p == size - 1) ? end(L) : pos[p].getP(); // возвращаем позицию следующего элемента
    }

    // Возвращает позицию предыдущего элемента
    public int previous(int p, List<T> L) {
        if (p < 0 || p > size) {
            throw new MyException("Нет предыдущей позиции");
        }
        return p - 1; // возвращаем позицию предыдущего элемента
    }

    // Возвращает позицию первого элемента списка
    public int first(List<T> L) {
        return (size == 0) ? end(L) : 0; // возвращаем первый элемент или конец списка
    }

    // Очищает список
    public void makenull(List<T> L) {
        for (int i = 0; i < size; i++) {
            elements[i] = null; // обнуляем все элементы
        }
        for (int i = 0; i < pos.length; i++) {
            this.pos[i].setP(i + 1); // восстанавливаем позиции
        }
        this.pos[capacity - 1].setP(-1); // конец списка
        space = 0; // обновляем первое свободное место
        start = -1;
        size = 0; // обнуляем размер списка
    }

    // Выводит список на экран
    public void printlist(List<T> L) {
        for (int i = 0; i < capacity; i++) {
            System.out.println(i + " " + elements[i] + " | next = " + pos[i].getP());
        }
        System.out.println("space: " + space);
        System.out.println("start: " + start);
        System.out.println("---");
    }
}
