import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Stack<T> {
    private final T[] elements; // массив для хранения элементов стека
    private int top; // индекс верхнего элемента стека
    private final ReentrantLock lock; // блокировка для сериализации доступа
    private final Condition notEmpty; // условие для проверки на пустоту стека
    private final Condition notFull; // условие для проверки на полноту стека

    // Конструктор: инициализирует стек с заданным размером
    public Stack(int size) {
        elements = (T[]) new Object[size];
        top = -1;
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    // Метод PUSH: помещает элемент на вершину стека
    public void push(T element) throws InterruptedException {
        lock.lock();
        try {
            while (isFull()) {
                notFull.await();
            }
            elements[++top] = element;
            notEmpty.signal();
            printStack("PUSH: ", element);
        } finally {
            lock.unlock();
        }
    }

    // Метод POP: удаляет элемент с вершины стека и возвращает его
    public T pop() throws InterruptedException {
        lock.lock();
        try {
            while (isEmpty()) {
                notEmpty.await();
            }
            T element = elements[top--];
            notFull.signal();
            printStack("POP", element);
            return element;
        } finally {
            lock.unlock();
        }
    }

    // Метод TOP: возвращает элемент с вершины стека (без удаления)
    public T top() throws InterruptedException {
        lock.lock();
        try {
            while (isEmpty()) {
                notEmpty.await();
            }
            return elements[top];
        } finally {
            lock.unlock();
        }
    }

    // Метод EMPTY: проверяет, пуст ли стек
    public boolean isEmpty() {
        return top == -1;
    }

    // Метод FULL: проверяет, полон ли стек
    public boolean isFull() {
        return top == elements.length - 1;
    }

    // Вспомогательный метод для печати состояния стека
    private void printStack(String operation, T element) {
        System.out.print(operation + " " + element + "\nStack: ");
        for (int i = top; i >= 0; i--) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
}
