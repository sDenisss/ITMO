import java.util.Random;

public class Opening implements Runnable {
    private final Stack stack; // ссылка на общий стек
    private final String name; // имя потока (для удобства вывода в консоль)
    private final char[] opBrackets = { '(', '{', '[' }; // массив открывающих скобок
    private final Random random; // генератор случайных чисел для выбора скобок
    public Thread t; // поток, в котором выполняется этот класс

    public Opening(Stack stack, String name) throws InterruptedException {
        this.stack = stack;
        this.name = name;
        this.random = new Random();
        this.start(); // старт потока
        Thread.sleep(1000); // небольшая задержка, чтобы дать время на запуск других потоков
    }

    @Override
    public void run() {
        System.out.println(this.name + " started.");
        try {
            int numBrackets = random.nextInt(3); // случайное количество скобок от 0 до 2

            for (int i = 0; i <= numBrackets; i++) {
                int randBracket = random.nextInt(3); // случайный выбор одной из трех скобок
                char bracket = opBrackets[randBracket]; // выбираем случайную открывающую скобку
                stack.push(bracket); // добавляем скобку в стек
                System.out.println(this.name + " pushed " + bracket);
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
