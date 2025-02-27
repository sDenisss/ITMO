
// Класс Stack реализует потокобезопасный стек с операциями push, pop и top
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

    // Метод push добавляет элемент на вершину стека
    // Параметры: элемент, который нужно добавить
    // Возвращает: ничего
    public void push(T element) throws InterruptedException {
        lock.lock(); // блокируем доступ к стеку
        try {
            while (isFull()) {
                notFull.await(); // ждем, пока освободится место в стеке
            }
            elements[++top] = element; // добавляем элемент в стек
            notEmpty.signal(); // сигнализируем, что стек не пуст
            printStack("PUSH: ", element); // печатаем текущее состояние стека
        } finally {
            lock.unlock(); // разблокируем доступ к стеку
        }
    }

    // Метод pop удаляет элемент с вершины стека и возвращает его
    // Параметры: отсутствуют
    // Возвращает: удаленный элемент
    public T pop() throws InterruptedException {
        lock.lock(); // блокируем доступ к стеку
        try {
            while (isEmpty()) {
                notEmpty.await(); // ждем, пока в стеке появятся элементы
            }
            T element = elements[top--]; // удаляем элемент из стека
            notFull.signal(); // сигнализируем, что в стеке появилось место
            printStack("POP", element); // печатаем текущее состояние стека
            return element; // возвращаем удаленный элемент
        } finally {
            lock.unlock(); // разблокируем доступ к стеку
        }
    }

    // Метод top возвращает элемент с вершины стека (без удаления)
    // Параметры: отсутствуют
    // Возвращает: элемент на вершине стека
    public T top() throws InterruptedException {
        lock.lock(); // блокируем доступ к стеку
        try {
            while (isEmpty()) {
                notEmpty.await(); // ждем, пока в стеке появятся элементы
            }
            return elements[top]; // возвращаем элемент на вершине стека
        } finally {
            lock.unlock(); // разблокируем доступ к стеку
        }
    }

    // Метод isEmpty проверяет, пуст ли стек
    // Параметры: отсутствуют
    // Возвращает: true, если стек пуст, иначе false
    public boolean isEmpty() {
        return top == -1;
    }

    // Метод isFull проверяет, полон ли стек
    // Параметры: отсутствуют
    // Возвращает: true, если стек полон, иначе false
    public boolean isFull() {
        return top == elements.length - 1;
    }

    // Вспомогательный метод printStack для печати состояния стека
    // Параметры: операция (строка), элемент (тип T)
    // Возвращает: ничего
    private void printStack(String operation, T element) {
        System.out.print(operation + " " + element + "\nStack: ");
        for (int i = top; i >= 0; i--) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
}
