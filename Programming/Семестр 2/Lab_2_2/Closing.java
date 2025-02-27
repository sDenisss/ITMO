import java.util.Random;

public class Closing implements Runnable {
    private final Stack stack; // ссылка на общий стек
    private final String name; // имя потока
    private final char[] closeBrackets = { ')', '}', ']' }; // массив закрывающих скобок
    private final Random random; // генератор случайных чисел
    public Thread t; // поток, в котором выполняется этот класс

    public Closing(Stack stack, String name) throws InterruptedException {
        this.stack = stack;
        this.name = name;
        this.random = new Random();
        this.start(); // старт потока
        Thread.sleep(1000); // небольшая задержка
    }

    @Override
    public void run() {
        System.out.println(this.name + " started.");
        try {
            while (true) {
                if (!stack.isEmpty()) {
                    char topBracket = stack.top(); // получаем верхний элемент из стека
                    char closeBracket = closeBrackets[random.nextInt(3)]; // случайный выбор закрывающей скобки
                    System.out.println(this.name + " generated " + closeBracket);

                    // Проверяем соответствие открывающей и закрывающей скобки
                    if ((closeBracket == ')' && topBracket == '(') ||
                            (closeBracket == '}' && topBracket == '{') ||
                            (closeBracket == ']' && topBracket == '[')) {

                        stack.pop(); // удаляем соответствующую скобку
                        System.out.println(this.name + " popped " + topBracket);
                        break; // выходим из цикла, так как нашли соответствие
                    } else {
                        System.out.println(this.name + " found mismatch with " + topBracket + ". Trying again...");
                    }
                } else {
                    System.out.println(this.name + " is waiting for the stack to have elements...");
                    stack.emptyStack(); // ожидание появления элементов в стеке
                }
                Thread.sleep(300); // небольшая задержка для симуляции работы потока
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // обработка прерывания потока
        }
        System.out.println("----------");
    }

    public void start() {
        if (t == null) {
            t = new Thread(this, name); // инициализация и запуск потока
            t.start();
        }
    }
}
