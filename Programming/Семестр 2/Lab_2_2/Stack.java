import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Stack {
    private final char[] elements; // массив для хранения элементов стека
    private int top; // индекс верхнего элемента стека
    private final ReentrantLock lock; // блокировка для синхронизации доступа к стеку
    private final Condition notEmpty; // условие для проверки, что стек не пуст
    private final Condition notFull; // условие для проверки, что стек не полон
    private final Condition canMatch; // условие для проверки соответствия скобок

    public Stack(int size) {
        this.elements = new char[size]; // создаем массив для хранения элементов
        this.top = -1; // инициализируем вершину стека как пустую
        this.lock = new ReentrantLock(); // создаем блокировку
        this.notEmpty = lock.newCondition(); // создаем условие для проверки непустого стека
        this.notFull = lock.newCondition(); // создаем условие для проверки на полноту стека
        this.canMatch = lock.newCondition(); // условие для проверки соответствия скобок
    }

    // Метод для добавления элемента в стек
    public void push(char element) throws InterruptedException {
        lock.lock(); // блокируем доступ к стеку
        try {
            while (isFull()) { // если стек полон, ждем
                notFull.await();
            }
            elements[++top] = element; // добавляем элемент в стек
            notEmpty.signal(); // уведомляем, что стек не пуст
            canMatch.signal(); // уведомляем, что можно проверить соответствие скобок
            printStack("PUSH", element); // выводим содержимое стека
        } finally {
            lock.unlock(); // разблокируем доступ к стеку
        }
    }

    // Метод для удаления элемента из стека
    public char pop() throws InterruptedException {
        lock.lock(); // блокируем доступ к стеку
        try {
            while (isEmpty()) { // если стек пуст, ждем
                notEmpty.await();
            }
            char element = elements[top--]; // удаляем верхний элемент из стека
            notFull.signal(); // уведомляем, что в стеке освободилось место
            printStack("POP", element); // выводим содержимое стека
            return element;
        } finally {
            lock.unlock(); // разблокируем доступ к стеку
        }
    }

    // Метод для получения верхнего элемента стека без удаления
    public char top() throws InterruptedException {
        lock.lock(); // блокируем доступ к стеку
        try {
            while (isEmpty()) { // если стек пуст, ждем
                notEmpty.await();
            }
            return elements[top];
        } finally {
            lock.unlock(); // разблокируем доступ к стеку
        }
    }

    // Метод для проверки, пуст ли стек
    public boolean isEmpty() {
        return top == -1;
    }

    // Метод для проверки, полон ли стек
    public boolean isFull() {
        return top == elements.length - 1;
    }

    // Метод для вывода содержимого стека
    private void printStack(String operation, char element) {
        System.out.print(operation + " " + element + "\nStack: ");
        for (int i = top; i >= 0; i--) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    // Метод для уведомления потока о необходимости проверки соответствия скобок
    public void notMatchBracket() throws InterruptedException {
        lock.lock(); // блокируем доступ к стеку
        try {
            canMatch.signal(); // сигнализируем о возможности продолжения работы
        } finally {
            lock.unlock(); // разблокируем доступ к стеку
        }
    }

    // Метод для уведомления о том, что стек не пуст
    public void emptyStack() {
        lock.lock(); // блокируем доступ к стеку
        try {
            notEmpty.signal(); // сигнализируем потоку, что можно продолжить работу
        } finally {
            lock.unlock(); // разблокируем доступ к стеку
        }
    }
}
